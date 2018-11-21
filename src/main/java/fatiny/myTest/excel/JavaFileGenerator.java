package fatiny.myTest.excel;

import java.io.File;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import fatiny.myTest.utils.FileUtil;
import fatiny.myTest.utils.StringHelper;

public class JavaFileGenerator {

	private ExcelContext excelContext;

	public static JavaFileGenerator create() {
		return new JavaFileGenerator();
	}

	public JavaFileGenerator excelData(ExcelContext context) {
		this.excelContext = context;
		return this;
	}

	public JavaFileGenerator build() {
		return this;
	}

	public JavaFileGenerator createFiles() {
		HashMap<String, FieldElement> modelElements = excelContext.getFieldElements();

		String className = StringHelper.upFirst(excelContext.getSheetName());
		if (!className.endsWith("Config") && !className.endsWith("Base")) {
			className += "Base";
		}

		Set<Entry<String, FieldElement>> entrySet = modelElements.entrySet();
		StringBuilder builder = new StringBuilder();
		builder.append("package com.good.module;\n\n");
		builder.append("import java.util.*;\n");
		builder.append("import com.good.base.IBaseData;\n");
		builder.append("import org.apache.commons.collections.MapUtils;\n\n");
		builder.append("public class ").append(className);
		builder.append(" implements IBaseData {\n\n");

		for (Entry<String, FieldElement> entry : entrySet) {
			if (StringUtils.isBlank(entry.getKey())) {
				continue;
			}
			FieldElement modelElement = entry.getValue();
			builder.append("\t/** ").append(modelElement.getChineseName()).append("*/\n");
			builder.append("\tprivate ").append(classToTypeName(modelElement.getClazz())).append(" ").append(modelElement.getFieldName()).append(";\n");
		}
		builder.append("\n");

		builder.append("\tpublic static Map<Integer, ").append(className).append("> DATAS").append(" = ").append("new HashMap<>();").append("\n\n");

		builder.append("\tpublic static ").append(className).append(" create(Map<String, Object> mapValue) {\n");
		builder.append("\t\t").append(className).append(" config = new ").append(className).append("();\n");
		builder.append("\t\tconfig.setMapValue(mapValue);\n");
		builder.append("\t\treturn config;\n").append("\t}\n\n");

		builder.append("\tpublic static ").append(className).append(" get(Integer id) {\n").append("\t\t").append(className).append(" baseData = DATAS.get(id);\n");
		builder.append("\t\treturn baseData;\n").append("\t}\n\n");

		builder.append("\tpublic void setMapValue(Map<String, Object> mapValue) {\n");
		for (Entry<String, FieldElement> entry : entrySet) {
			if (StringUtils.isBlank(entry.getKey())) {
				continue;
			}
			FieldElement modelElement = entry.getValue();
			builder.append("\t\t//").append(modelElement.getChineseName()).append("\n").append("\t\tthis.").append(modelElement.getFieldName()).append(" = ").append("MapUtils.").append(methodGetterName(modelElement.getClazz())).append("(mapValue, ").append("\"").append(modelElement.getFieldName()).append("\");\n");
		}
		builder.append("\t}\n\n");

		builder.append("\t@Override").append("\n");
		builder.append("\tpublic void load(List<Map<String, Object>> dataList) throws Exception {\n");
		builder.append("\t\tMap<Integer, ").append(className).append("> dataMap").append(" = ").append("new HashMap<>();");
		builder.append("\n\n");
		builder.append("\t\tfor(Map<String, Object> mapValue : dataList) {\n");
		builder.append("\t\t\t// create IBaseData Object and save it to the Map cache\n");
		builder.append("\t\t\t").append(className).append(" basedata = null;\n");
		builder.append("\t\t\ttry {\n");
		builder.append("\t\t\t\tbasedata = create(mapValue);\n");
		builder.append("\t\t\t\tbasedata.init();\n");
		builder.append("\t\t\t\t// TODO save basedata to Map, do by yourself !!!\n");
		builder.append("\t\t\t} catch (Exception e) {\n");
		builder.append("\t\t\t\t// TODO record error log, example: GameLog.error(\"读取数据出错, ?id:{}\", ?);\n");
		builder.append("\t\t\t\tthrow e;\n");
		builder.append("\t\t\t}\n");
		builder.append("\t\t}\n\n");
		builder.append("\t\tDATAS = dataMap;\n");
		builder.append("\t}\n\n");

		builder.append("\t@Override").append("\n");
		builder.append("\tpublic void loadFinish() {\n");
		builder.append("\n\t}");

		builder.append("\n\n");

		builder.append("\tprivate void init() {\n");
		builder.append("\n\t}");

		builder.append("\n\n}");

		FileUtil.saveFile(new File("excel/java/" + className + ".java"), builder.toString());
		return this;
	}

	private String methodGetterName(Class<?> clazz) {
		if (clazz == Integer.class) {
			return "getIntValue";
		} else if (clazz == Float.class) {
			return "getFloatValue";
		} else if (clazz == Boolean.class) {
			return "getBooleanValue";
		} else if (clazz == Long.class) {
			return "getLongValue";
		} else if (clazz == Double.class) {
			return "getDoubleValue";
		}
		return "getString";
	}

	private String classToTypeName(Class<?> clazz) {
		if (clazz == Integer.class) {
			return "int";
		} else if (clazz == Float.class) {
			return "float";
		} else if (clazz == Boolean.class) {
			return "boolean";
		} else if (clazz == Long.class) {
			return "long";
		} else if (clazz == Double.class) {
			return "double";
		} else if (clazz == String.class) {
			return "String";
		}
		return clazz.getSimpleName();
	}
}
