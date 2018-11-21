package fatiny.myTool.scp.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 日志工具类
 */
public class ScpLog {

	private final static Logger stdLog = LoggerFactory.getLogger(ScpLog.class);
	private static boolean needLog4jLog = true;
	
	public static void info(String infoMsg, Object... params) {
		if (needLog4jLog) {
			stdLog.info(infoMsg, params);
		}
	}

	public static void debug(String debugMsg, Object... params) {
		if (needLog4jLog) {
			stdLog.debug(debugMsg, params);
		}
	}

	public static void error(String errMsg, Object... params) {
		if (needLog4jLog) {
			stdLog.error(errMsg, params);
		}
	}

	public static void error(String errMsg, Throwable cause) {
		if (needLog4jLog) {
			stdLog.error(errMsg, cause);
		}
	}

//	public static void init() throws Exception {
//		String selectorName = "org.apache.logging.log4j.core.async.AsyncLoggerContextSelector";
//		System.setProperty(Constants.LOG4J_CONTEXT_SELECTOR, selectorName);
//		
//		File file = new File("config/log4j2.xml");
//		FileInputStream log4j2XmlStream = new FileInputStream(file);
//		ConfigurationSource source = new ConfigurationSource(log4j2XmlStream);
//		XmlConfiguration configuration = new XmlConfiguration(source);
//
//		Configurator.initialize(configuration);
//
//		stdLog = LogManager.getLogger("standardLog");
//		errLog = LogManager.getLogger("errorLog");
//		fightLog = LogManager.getLogger("fightLog"); 
//		needLog4jLog = true;
//	}
	
}
