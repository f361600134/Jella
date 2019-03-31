package fatiny.myTest.apache.collections;

public class MapTest {

	// /**
	// * 通用方法, 可以按照指定的格式组装成指定的字符串
	// *
	// * @param sourceStr
	// * @param typeOfT
	// * @return
	// * @return Map
	// * @date 2018年12月11日下午2:57:46
	// */
	// public static <T> Map strToMap(String sourceStr, java.lang.reflect.Type
	// typeOfT) {
	// if (StringUtils.isBlank(sourceStr))
	// return null;
	// try {
	// TypeToken<T> typeToken = (TypeToken<T>) TypeToken.get(typeOfT);
	// Class<? super Map> mapClass = (Class<? super Map>)
	// typeToken.getRawType();
	// Map map = (Map) mapClass.newInstance();
	// String[] strArr = sourceStr.split(",");
	// for (String str : strArr) {
	// String[] split1 = str.split("=");
	// map.put(split1[0], split1[1]);
	// }
	// return map;
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }

	// private void write2Value(Object value, IoBuffer buff, Object... objs) {
	// if ((value.getClass() == Integer.TYPE) || (value.getClass() ==
	// Integer.class)) {
	// buff.putInt(Integer.valueOf(value.toString()));
	// } else if ((value.getClass() == Long.TYPE) || (value.getClass() ==
	// Long.class)) {
	// buff.putLong(Long.valueOf(value.toString()));
	// } else if ((value.getClass() == Byte.TYPE) || (value.getClass() ==
	// Byte.class)) {
	// buff.put(Byte.valueOf(value.toString()));
	// } else if ((value.getClass() == Double.TYPE) || (value.getClass() ==
	// Double.class)) {
	// buff.putDouble(Double.valueOf(value.toString()));
	// } else if ((value.getClass() == Short.TYPE) || (value.getClass() ==
	// Short.class)) {
	// buff.putShort(Short.valueOf(value.toString()));
	// } else if ((value.getClass() == Float.TYPE) || (value.getClass() ==
	// Float.class)) {
	// buff.putFloat(Float.valueOf(value.toString()));
	// } else if (value.getClass() == String.class) {
	// // putString(buff, value.toString());
	// } else if (value instanceof Write2Buff) {
	// ((Write2Buff) value).write2Buff(buff);
	// } else if (value instanceof Write2Buff2) {
	// ((Write2Buff2) value).write2Buff(buff, objs);
	// }
	// }

}
