package fatiny.myTest.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import fatiny.myTest.utils.FileUtil;

public class ExcelContext {

	private String sheetName;
	private String configName;
	private String note;
	private String folderName;
	private final HashMap<String, FieldElement> fieldElements = new HashMap<String, FieldElement>();
	private final List<List<EGrid>> gridss = new ArrayList<List<EGrid>>();

	public ExcelContext sheetName(String sheetName) {
		this.sheetName = sheetName;
		return this;
	}

	public ExcelContext configName(String name) {
		int endIndex = name.indexOf("Base");
		if (endIndex == 0) {
			this.configName = name.replace("Base", "");
			return this;
		}
		endIndex = endIndex == -1 ? name.length() : endIndex;
		// if (endIndex > 0) {
		this.configName = name.substring(0, 1).toLowerCase().concat(name.substring(1, endIndex)); // 首字母小写
		// return this;
		// }
		// this.configName = name;
		return this;
	}

	public ExcelContext data(List<List<EGrid>> valueCollect) {
		gridss.addAll(valueCollect);
		return this;
	}

	public ExcelContext note(String note) {
		this.note = note;
		return this;
	}

	public ExcelContext create() {
		return this;
	}

	public void merge(ExcelContext excelContext) {
		if (excelContext.configName.equalsIgnoreCase(this.configName)) {
			this.note += excelContext.note + "\n";
			this.gridss.addAll(excelContext.gridss);
			this.modelElements(excelContext.getFieldElements().values());
		} else {
			throw new RuntimeException("类型不符，不能合并数据");
		}
	}

	public File getTargetFile(String rootPath) {
		return new File(rootPath + "/" + configName + ".json");
	}

	public File getTargetFolder(String rootPath) {
		return new File(rootPath + "/" + folderName.toLowerCase() + "/");
	}

	public File getTargetFolder() {
		return new File(ExcelConfig.RESOURCE_PATH + "/" + folderName.toLowerCase() + "/");
	}

	public ExcelContext folderName(String folderName) {
		this.folderName = folderName;
		return this;
	}

	public void toJsonFile() {
		File folder = new File(ExcelConfig.RESOURCE_PATH);
		if (!folder.exists()) {
			folder.mkdirs();
		}

		List<Map<String, Object>> maps = new ArrayList<Map<String, Object>>();
		for (List<EGrid> grids : gridss) {
			Map<String, Object> rowMap = new HashMap<String, Object>();
			for (EGrid grid : grids) {
				if (grid == null)
					continue;
				rowMap.put(grid.getField(), grid.getValue());
			}
			if (!rowMap.isEmpty()) {
				maps.add(rowMap);
			}
		}
		if (maps.isEmpty()) {
			return;
		}
		FileUtil.saveFile(getTargetFile(ExcelConfig.RESOURCE_PATH), JSON.toJSONString(maps, true));
	}

	public String getSheetName() {
		return this.sheetName;
	}

	public List<List<EGrid>> getGridss() {
		return gridss;
	}

	public ExcelContext modelElements(Collection<FieldElement> mes) {
		for (FieldElement element : mes) {
			fieldElements.put(element.getFieldName(), element);
		}
		return this;
	}

	public String getConfigName() {
		return configName;
	}

	public String getNote() {
		return note;
	}

	public HashMap<String, FieldElement> getFieldElements() {
		return new HashMap<String, FieldElement>(fieldElements);
	}

}
