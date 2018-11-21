package fatiny.myTest.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class EGrid {

	/** 字段中文名称 */
	private String chineseName;
	/** 类型(num, String) */
	private String type;
	/** 字段名 */
	private String field;
	/** 值 */
	private Object value;

	public EGrid(String chineseName, String type, String field, String value) {
		this.chineseName = chineseName.trim();
		this.type = type.trim();
		this.field = field.trim();
		this.value = convert(value.trim(), this.type);
	}

	private Object convert(String value, String typeName) {
		if (NumberUtils.isNumber(value)) {
			value = value.replace("L", "D");
			Double parseDouble = Double.parseDouble(value);
			if (typeName.equalsIgnoreCase("string")) {
				return parseDouble.intValue() + "";
			} else if (typeName.equalsIgnoreCase("int") || typeName.equalsIgnoreCase("integer")) {
				return parseDouble.intValue();
			} else if (typeName.equalsIgnoreCase("float") || typeName.equalsIgnoreCase("double")) {
				return parseDouble.floatValue();
			} else if (typeName.equalsIgnoreCase("long")) {
				long longValue = parseDouble.longValue();
				return longValue;
			}
		}
		if (typeName.equalsIgnoreCase("boolean")) {
			if (StringUtils.isNotBlank(value)) {
				return Boolean.parseBoolean(value.toLowerCase().trim());
			}
			return false;
		}
		if (!typeName.equalsIgnoreCase("string")) {
			return 0;
		}
		return value;
	}

	public String getChineseName() {
		return chineseName;
	}

	public String getType() {
		return type;
	}

	public String getField() {
		return field;
	}

	public <T> T getValue() {
		return (T) value;
	}
	
	public boolean isEmpty() {
		return value == null || value.toString().length() == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("EGrid [chineseName=");
		builder.append(chineseName);
		builder.append(", type=");
		builder.append(type);
		builder.append(", field=");
		builder.append(field);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}

}
