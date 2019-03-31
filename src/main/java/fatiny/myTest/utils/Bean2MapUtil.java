package fatiny.myTest.utils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class Bean2MapUtil {

	/** 判断一个对象是否是基本类型或基本类型的封装类型 */
	private boolean isPrimitive(Object obj) {
		try {
			return ((Class<?>) obj.getClass().getField("TYPE").get(null)).isPrimitive();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Map转实体类共通方法 (Map2Bean)
	 * 
	 * @param type
	 * @param map
	 * @return
	 * @throws Exception
	 * @return Object
	 * @date 2019年1月9日上午10:23:03
	 */
	public static <T> T convertMap(Class<T> type, Map map) throws Exception {
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		Object obj = type.newInstance();
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			String propertyName = descriptor.getName();
			if (map.containsKey(propertyName)) {
				Object value = map.get(propertyName);
				descriptor.getWriteMethod().invoke(obj, value);
			}
		}
		return (T) obj;
	}

	// /**
	// * Map转实体类共通方法 (Map2Bean)
	// *
	// * @param type
	// * @param map
	// * @return
	// * @throws Exception
	// * @return Object
	// * @date 2019年1月9日上午10:23:03
	// */
	// public static Object convertMap1(Class type, Map map) throws Exception {
	// BeanInfo beanInfo = Introspector.getBeanInfo(type);
	// Object obj = type.newInstance();
	// PropertyDescriptor[] propertyDescriptors =
	// beanInfo.getPropertyDescriptors();
	// for (PropertyDescriptor descriptor : propertyDescriptors) {
	// String propertyName = descriptor.getName();
	// if (map.containsKey(propertyName)) {
	// Object value = map.get(propertyName);
	// descriptor.getWriteMethod().invoke(obj, value);
	// }
	// }
	// return obj;
	// }

	/**
	 * 实体类转Map共通方法 (Bean2Map)
	 */
	public static Map convertBean(Object bean) throws Exception {
		Class type = bean.getClass();
		Map returnMap = new HashMap();
		BeanInfo beanInfo = Introspector.getBeanInfo(type);
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (PropertyDescriptor descriptor : propertyDescriptors) {
			String propertyName = descriptor.getName();
			if (!propertyName.equals("class")) {
				Method readMethod = descriptor.getReadMethod();
				Object result = readMethod.invoke(bean);
				if (result != null) {
					returnMap.put(propertyName, result);
				} else {
					returnMap.put(propertyName, "");
				}
			}
		}
		return returnMap;
	}
}