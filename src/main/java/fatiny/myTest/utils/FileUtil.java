package fatiny.myTest.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.stream.FileImageInputStream;

public class FileUtil {

	public static String readFile(File file) {
		StringBuilder builder = new StringBuilder();
		InputStreamReader inputStreamReader = null;
		BufferedReader reader = null;
		try {
			FileInputStream fileInputStream = new FileInputStream(file);
			inputStreamReader = new InputStreamReader(fileInputStream, "utf-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				// 显示行号
				builder.append(tempString).append("\n");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return builder.toString();
	}
	

	public static void saveFile(File file, String contents) {
		Writer writer = null;
		try {
			FileOutputStream fileStream = new FileOutputStream(file);
			writer = new OutputStreamWriter(fileStream, "UTF-8");
			writer.write(contents);
			writer.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (writer != null) {
					writer.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	public static byte[] readFileToBytes(File file) {
		byte[] bs = null;
		FileImageInputStream inputStream = null;
		ByteArrayOutputStream byteArrayOutputStream = null;
		try {
			inputStream = new FileImageInputStream(file);
			byteArrayOutputStream = new ByteArrayOutputStream();
			int b = -1;
			byte[] buf = new byte[2048];
			while ((b = inputStream.read(buf)) != -1) {
				byteArrayOutputStream.write(buf, 0, b);
			}
			bs = byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (byteArrayOutputStream != null) {
					byteArrayOutputStream.close();
				}
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return bs;
	}
	

	public static List<File> getAllFiles(File folder) {
		List<File> files = new ArrayList<File>();
		File[] inFiles = folder.listFiles();
		for (File file : inFiles) {
			if (file.isDirectory()) {
				files.addAll(getAllFiles(file));
			} else {
				files.add(file);
			}
		}
		return files;
	}

	
	public static void saveFile(byte[] fileByte, String path) {
		FileOutputStream fileOutputStream = null;
		try {
			fileOutputStream = new FileOutputStream(new File(path));
			fileOutputStream.write(fileByte);
			fileOutputStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fileOutputStream != null) {
					fileOutputStream.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	

}
