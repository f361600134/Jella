package fatiny.myTool.Jredis.older.util;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 类名称：CommonUtils 类描述：常用工具类 创建人：yxh 创建时间：2012-7-16 上午07:43:40 修改备注：
 * 
 * @version 1.0.0
 * 
 */
public class CommonUtils {

	private static final Logger logger = LoggerFactory
			.getLogger(CommonUtils.class);

	/**
	 * 是否整形类型
	 * 
	 * @param data
	 * @return
	 */
	public static boolean isInteger(Object data) {
		if (data == null || "".equals(data))
			return false;
		String reg = "[\\d]+";
		Pattern p = Pattern.compile(reg);
		Matcher m = p.matcher(data.toString());
		return Integer.MAX_VALUE >= Double.parseDouble(data.toString())
				&& m.matches();
	}

	/**
	 * 判断一个集合是否为空或没有元素
	 * 
	 */
	public static <T> boolean isCollectionEmpty(Collection<T> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 字符串是否为空
	 * 
	 * @param string
	 * @return
	 */
	public static boolean isNull(String str) {
		return str == null || str.length() == 0 || str.equals(" ")
				|| str.equals("null") || str.trim().length() == 0;
	}

	/**
	 * 功能：判断字符串是否为数字
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isNumeric(String str) {
		Pattern pattern = Pattern.compile("[0-9]*");
		Matcher isNum = pattern.matcher(str);
		if (isNum.matches()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * @param obj
	 * @return
	 */
	public static int parseInt(String obj) {
		if (obj == null || obj.equals("")) {
			return 0;
		}
		try {
			if (!obj.toString().contains(".")) {
				return Integer.parseInt(obj.toString());
			}
			return (int) Double.parseDouble((obj.toString()));
		} catch (Exception e) {
			logger.error("字符串{}转换int出错", obj, e);
		}
		return 0;
	}

	/**
	 * 
	 * @param obj
	 * @return
	 */
	public static float parseFloat(Object obj) {
		if (obj == null || obj.equals("")) {
			return 0;
		}
		try {
			return Float.parseFloat(obj.toString());
		} catch (Exception e) {
			logger.error("字符串{}转换float出错", obj, e);
		}
		return 0;
	}

	/**
	 * @param obj
	 * @return
	 */
	public static long parseLong(Object obj) {
		if (obj == null || obj.equals("")) {
			return 0;
		}
		try {
			if (!obj.toString().contains(".")) {
				return Long.parseLong(obj.toString());
			}
			return (long) Double.parseDouble((obj.toString()));
		} catch (Exception e) {
			logger.error("字符串{}转换double出错", obj, e);
		}
		return 0;
	}

	private static Pattern contentPattern = Pattern
			.compile("[\u4e00-\u9fa50-9a-zA-Z,，。.?？!！@()（）\\[\\]\\-_]+");

	/**
	 * 内容是否合法
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isContentIll(String content) {
		Matcher matcher = contentPattern.matcher(content);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		String ill = sb.toString();
		if (!ill.equals(content))
			return false; // 有特殊字符
		return true;
	}

	private static Pattern namePattern = Pattern
			.compile("[\u4e00-\u9fa50-9a-zA-Z]+");

	/**
	 * 
	 * @param name
	 * @return
	 */
	public static boolean isNameIll(String name) {
		Matcher matcher = namePattern.matcher(name);
		StringBuilder sb = new StringBuilder();
		while (matcher.find()) {
			sb.append(matcher.group());
		}
		String ill = sb.toString();
		if (!ill.equals(name))
			return false; // 有特殊字符
		return true;
	}

	/**
	 * 获取随机值
	 * 
	 * @param min
	 * @param max
	 * @return
	 */
	public static int getRandom(int minNumber, int maxNumber) {
		if (minNumber > maxNumber) {
			int temp = minNumber;
			minNumber = maxNumber;
			maxNumber = temp;
		}
		return (int) (minNumber + Math.random() * (maxNumber - minNumber + 1));
	}

	/**
	 * 按顺序加入元素(插入排序)
	 * 
	 * @param list
	 * @param o
	 *            (实现Comparable接口)
	 * @param true-降序 false-升序
	 */
	@SuppressWarnings("unchecked")
	public static <T> void addOrderList(List<T> list, T o, boolean isDesc) {
		for (int i = 0; i < list.size(); i++) {
			if (isDesc) {
				if (((Comparable<T>) o).compareTo(list.get(i)) == 1) {
					list.add(i, o);
					return;
				}
			} else {
				if (((Comparable<T>) o).compareTo(list.get(i)) == -1) {
					list.add(i, o);
					return;
				}
			}
		}
		list.add(o);
	}

	/**
	 * 返回字符串长度
	 * 
	 * @param value
	 * @return
	 */
	public static int stringLength(String value) {
		int valueLength = 0;
		String chinese = "[\u0391-\uFFE5]";
		/* 获取字段值的长度，如果含中文字符，则每个中文字符长度为2，否则为1 */
		for (int i = 0; i < value.length(); i++) {
			/* 获取一个字符 */
			String temp = value.substring(i, i + 1);
			/* 判断是否为中文字符 */
			if (temp.matches(chinese)) {
				/* 中文字符长度为2 */
				valueLength += 2;
			} else {
				/* 其他字符长度为1 */
				valueLength += 1;
			}
		}
		return valueLength;
	}

	/**
	 * 判断一个map是否为空或没有元素
	 * 
	 * @param <K>
	 * @param <V>
	 * @param map
	 * @return
	 */
	public static <K, V> boolean isMapEmpty(Map<K, V> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 转换字节
	 * 
	 * @param fileS
	 * @return
	 */
	public static String formetFileSize(long fileS) {
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "B";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "K";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "M";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "G";
		}
		return fileSizeString;
	}

	/**
	 * @param dayInfo
	 * @return
	 */
	public static List<Integer> transforDayList(String dayInfo) {
		List<Integer> dayList = new ArrayList<Integer>();
		if (CommonUtils.isNull(dayInfo) || dayInfo.contains("0")) {
			for (int i = 1; i <= 7; i++) {
				dayList.add(i);
			}
		} else {
			Integer[] array = StringUtils.stringToArray(dayInfo, ",",
					Integer.class);
			for (Integer value : array) {
				value = (value + 1) % 7;
				if (value == 0) {
					value = 7;
				}
				dayList.add(value);
			}
		}
		return dayList;
	}

}
