package fatiny.myTest.other.mavencon.remoteContect;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import com.google.common.base.Splitter;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;

public class RemoteExecutionApi {
    private int port = 22;
    private String username;
    private String password;

    public RemoteExecutionApi(int port, String username, String password) {
        super();
        this.port = port;
        this.username = username;
        this.password = password;
    }

    public RemoteExecutionApi(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    // 下载文件，目前只能下载单个文件
    public void getFile(String remoteFile, String localTargetDirectory, String ips) {
        Iterable<String> result = Splitter.on(',').trimResults().omitEmptyStrings().split(ips);

        for (String ip : result) {
            Connection conn = new Connection(ip, port);
            try {
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false) {
                    System.err.println("authentication failed");
                }
                SCPClient client = new SCPClient(conn);
                client.get(remoteFile, localTargetDirectory);
                conn.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                // Logger operator
                System.exit(2);
            }
        }
    }

    //上传文件或者文件夹
    public void putFile(String localFile, String remoteTargetDirectory, String ips) {
        Iterable<String> result = Splitter.on(',').trimResults().omitEmptyStrings().split(ips);
        for (String ip : result) {
            Connection conn = new Connection(ip, port);
            try {
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false) {
                    System.err.println("authentication failed");
                }
                // folder
                if (new File(localFile).isDirectory()) {
                    // 先创建根目录
                    String dirName = new File(localFile).getName();
                    remoteTargetDirectory = remoteTargetDirectory + "/" + dirName;
                    Session sess1 = conn.openSession();
                    sess1.execCommand("mkdir -p " + remoteTargetDirectory);
                    sess1.waitForCondition(ChannelCondition.EOF, 0);
                    sess1.close();
                    putDir(conn, localFile, remoteTargetDirectory);

                } else if (new File(localFile).isFile()) {// file
                    SCPClient client = new SCPClient(conn);
                    client.put(localFile, remoteTargetDirectory);
                }
                conn.close();

            } catch (IOException ex) {
                ex.printStackTrace();
                // Logger operator
                System.exit(2);
            }
        }
    }

    private void putDir(Connection conn, String localDirectory, String remoteTargetDirectory) throws IOException {
        String[] fileList = new File(localDirectory).list();
        for (String file : fileList) {
            String fullFileName = localDirectory + new File(localDirectory).separator + file;

            if (new File(fullFileName).isDirectory()) {
                final String subDir = remoteTargetDirectory + "/" + file;
                Session sess = conn.openSession();
                sess.execCommand("mkdir " + subDir);
                sess.waitForCondition(ChannelCondition.EOF, 0);
                sess.close();
                putDir(conn, fullFileName, subDir);
            } else {
                SCPClient client = new SCPClient(conn);
                client.put(fullFileName, remoteTargetDirectory);
            }
        }
    }

    /**
     * 执行简单命令, 简单作为测试, 一般来说不建议使用这个方法.
     * 官网API介绍, execCommand 有可能无法获取全局的环境变量
     * 当使用exeCommand出现环境变量问题的时候, 使用startShell替代
     * @param command
     * @param ips
     * @return
     */
    public String runCommand(String command, String ips) {
        StringBuilder sb = new StringBuilder();
        Iterable<String> result = Splitter.on(',').trimResults().omitEmptyStrings().split(ips);
        for (String ip : result) {
            Connection conn = new Connection(ip, port);
            try {
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false) {
                    System.err.println("authentication failed");
                }

                Session sess = conn.openSession();
                sess.execCommand(command);

                InputStream stdout = new StreamGobbler(sess.getStdout());
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                while (true) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    sb.append(line).append("\n");
                }

                System.out.println("ExitCode: " + sess.getExitStatus());
                br.close();
                sess.close();
                conn.close();

            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                // Logger operator
                System.exit(2);
            }
        }
        return sb.toString();
    }
    
    /**
     * 执行高级命令
     * 存在问题, 输出流数据读不出来. 有空解决
     * @param command
     * @param ips
     * @return
     */
    public String runHigherCommand(String command, String ips) {
        StringBuilder sb = new StringBuilder();
        Iterable<String> result = Splitter.on(',').trimResults().omitEmptyStrings().split(ips);
        for (String ip : result) {
            Connection conn = new Connection(ip, port);
            try {
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false) {
                    System.err.println("authentication failed");
                }
                
                Session sess = conn.openSession();
                //sess.requestPTY("bash");
                sess.requestDumbPTY();
                sess.startShell();
                //PrintWriter out = new PrintWriter(sess.getStdin());
                //out.println(command);
                //out.println("exit");
                OutputStream out = sess.getStdin();
                out.write(command.getBytes());
                try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
                InputStream stdout = new StreamGobbler(sess.getStdout());
                BufferedReader br = new BufferedReader(new InputStreamReader(stdout));
                while (true) {
                    String line = br.readLine();
                    if (line == null)break;
                    sb.append(line).append("\n");
                }

                System.out.println("ExitCode: " + sess.getExitStatus());
//                out.close();
                br.close();
                sess.close();
                conn.close();

            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                // Logger operator
                System.exit(2);
            }
        }
        return sb.toString();
    }

    // 删除临时文件
    public void delTempDir(String remotePath, String ips) {
        runCommand("rm -rf " + remotePath, ips);
    }

    // 修改配置文件
    public void modfiyPropertyFile(String remoteFileName, String key, String value, String ips) {
        String tempDir = "tempDir";
        File folder = new File(tempDir);
        folder.mkdirs();

        Iterable<String> result = Splitter.on(',').trimResults().omitEmptyStrings().split(ips);
        for (String ip : result) {
            Connection conn = new Connection(ip, port);
            try {
                conn.connect();
                boolean isAuthenticated = conn.authenticateWithPassword(username, password);
                if (isAuthenticated == false) {
                    System.err.println("authentication failed");
                }

                SCPClient client = new SCPClient(conn);
                client.get(remoteFileName, tempDir);

                String tmpFileName = tempDir + File.separator
                        + remoteFileName.substring(remoteFileName.lastIndexOf("/"));

                propertyUtil.setProper(tmpFileName, key, value);

                client.put(tmpFileName, remoteFileName.substring(0, remoteFileName.lastIndexOf('/')));

                conn.close();

            } catch (IOException ex) {
                ex.printStackTrace(System.err);
                // Logger operator
                System.exit(2);
            }
        }

        clearDir(folder);
    }

    private void clearDir(File file) {
        if (file.isDirectory()) {
            for (File f : file.listFiles()) {
                clearDir(f);
                f.delete();
            }
        }
        file.delete();
    }

    // 在配置文件后添加新行
    public void propertyFileAddNewline(String remoteFileName, String newline, String ips) {
        runCommand("echo " + newline + " >> " + remoteFileName, ips);
    }

    // 重启机器
    public void reboot(String ips) {
        runCommand("reboot", ips);
    }

    // 执行本地命令
    public String runLoaclCommand(String command) {
        return ExecLocakCommand.processUseBasic(command);
    }

    public static void main(String[] args) {
        RemoteExecutionApi client = new RemoteExecutionApi(20089, "root", "wzyd508");
//       client.getFile("/home/Jeremy/a.txt","E:", "192.168.6.139");
        //client.putFile("D:\\test", "/root", "192.168.238.129");
        //String ret = client.runCommand("cd /home/Jeremy", "192.168.6.139");
        String ret = client.runHigherCommand("ls", "192.168.6.139");
        System.out.println(ret);
//        ret = client.runCommand("ls", "192.168.6.139");
//        System.out.println(ret);
        // client.putDir("D:\\test", "/root", "192.168.238.129");
        // client.modfiyPropertyFile("/root/test.proprety", "key", "yyy",
        // "192.168.238.129");
        // client.propertyFileAddNewline("/root/xx.txt", "yyyyy=xxxxx",
        // "192.168.238.129");
//        String ret = client.runLoaclCommand("dir");
//        System.out.println(ret);
//        System.out.println("----");
    }

}