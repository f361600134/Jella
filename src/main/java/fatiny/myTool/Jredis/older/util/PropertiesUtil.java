package fatiny.myTool.Jredis.older.util;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtil {

	private static Logger logger = LoggerFactory
			.getLogger(PropertiesUtil.class);

	public static Properties getProperties(String fileName) {
		Properties properties = new Properties();
		String file = PropertiesUtil.class.getClassLoader().getResource("")
				.getPath()
				+ fileName;
		FileReader reader = null;
		try {
			reader = new FileReader(file);
			properties.load(reader);
		} catch (Exception e) {
			logger.error("加载配置文件出错[{}]", file, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					logger.error("加载配置文件关闭出错[{}]", file, e);
				}
			}
		}
		return properties;
	}
}
