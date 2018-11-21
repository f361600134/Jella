package fatiny.myTest.other.mavencon.remoteContect;

import java.io.IOException;
import java.io.InputStream;

public final class ExecLocakCommand {

    public static final String processUseBasic(String cmd) {
        Process p = null;
        StringBuilder sb = new StringBuilder();
        try {
            String os = System.getProperty("os.name").toLowerCase();
            if (os.startsWith("win")) {
                String commands = "cmd /c " + cmd;
                p = Runtime.getRuntime().exec(commands);
            } else if (os.startsWith("linux")) {
                String[] commands = new String[] { "/bin/sh", "-c", cmd };
                p = Runtime.getRuntime().exec(commands);
            }

            String error = read(p.getErrorStream());
            String outInfo = read(p.getInputStream());

            String resultCode = "0";// 脚本中输出0表示命令执行成功
            if (error.length() != 0) { // 如果错误流中有内容，表明脚本执行有问题
                resultCode = "1";
            }

            sb.append(resultCode).append("\n");
            sb.append(error).append("\n");
            sb.append(outInfo);

            p.waitFor();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                p.getErrorStream().close();
                p.getInputStream().close();
                p.getOutputStream().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    public static final String read(InputStream in) throws IOException {
        StringBuilder sb = new StringBuilder();
        int ch;
        while (-1 != (ch = in.read()))
            sb.append((char) ch);
        return sb.toString();
    }

    public static void main(String[] args) {
         String comands = "dir";
        //String comands = "ls ";
        String ret = ExecLocakCommand.processUseBasic(comands);
        System.out.println(ret);
    }
}