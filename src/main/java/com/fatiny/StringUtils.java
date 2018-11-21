package com.fatiny;

import java.lang.reflect.Field;

public class StringUtils {

	public static <T> T format(String str, Class<T> clazz) throws Exception {
		String strArr[] = str.split("_");
		if (strArr.length != clazz.getDeclaredFields().length) {
			throw new Exception("字段与对象不匹配");
		}
		Object instance = clazz.newInstance();
		int i = 0;
		for (Field field : clazz.getDeclaredFields()) {
			setValue(instance, field, strArr[i]);
			i++;
		}
		return (T) instance;
	}

	public static void setValue(Object instance, Field field, String value) throws Exception {
		Class<?> type = field.getType();
		if ((type == Integer.TYPE) || (type == Integer.class)) {
			field.setInt(instance, Integer.parseInt(value));
		} else if ((type == Long.TYPE) || (type == Long.class)) {
			field.setLong(instance, Long.parseLong(value));
		} else if (type == Boolean.TYPE || type == Boolean.class) {
			field.set(instance, Boolean.parseBoolean(value));
		} else if (type == Byte.TYPE || type == Byte.class) {
			field.set(instance, Byte.parseByte(value));
		} else if (type == Double.TYPE || type == Double.class) {
			field.set(instance, Double.parseDouble(value));
		} else if (type == Short.TYPE || type == Short.class) {
			field.set(instance, Short.parseShort(value));
		} else if (type == Float.TYPE || type == Float.class) {
			field.set(instance, Float.parseFloat(value));
		} else if (type == String.class) {
			field.set(instance, value);
		} else {
			throw new Exception(field.getName() + "非基本类型，请指定解析方法.");
		}
	}

	public static void main(String[] args) {
		try {
			Student stu = format("1_b", Student.class);
			// Student stu = new Student(1, "a");
			// Map<String, String> map = BeanRefUtil.getFieldValueMap(stu);
			// System.out.println(map);
			System.out.println(stu);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
