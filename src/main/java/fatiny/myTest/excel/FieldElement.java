package fatiny.myTest.excel;

import org.apache.commons.lang3.StringUtils;

public class FieldElement {

	private String fieldName;
	private Class<?> clazz;
	private String chineseName;

	public FieldElement(String chineseName, String type, String fieldName) {
		this.chineseName = chineseName;
		this.fieldName = fieldName;
		String clazzName = type.trim().toLowerCase();
		if (clazzName.equalsIgnoreCase("int") || clazzName.equalsIgnoreCase("integer")) {
			clazz = Integer.class;
		} else if (clazzName.equalsIgnoreCase("long")) {
			clazz = Long.class;
		} else if (clazzName.equalsIgnoreCase("double")) {
			clazz = Double.class;
		} else if (clazzName.equalsIgnoreCase("float")) {
			clazz = Float.class;
		} else if (clazzName.equalsIgnoreCase("boolean")) {
			clazz = Boolean.class;
		} else {
			clazz = String.class;
		}
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getFieldNameFirstUpperCase() {
		return fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());
	}

	public Class<?> getClazz() {
		return clazz;
	}

	public String getChineseName() {
		return chineseName;
	}

	public static FieldElement create(String chineseName, String type, String fieldName) {
		if (StringUtils.isBlank(fieldName) || StringUtils.isBlank(type)) {
			return null;
		}
		return new FieldElement(chineseName, type, fieldName);
	}

}
