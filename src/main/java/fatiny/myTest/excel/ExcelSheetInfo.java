package fatiny.myTest.excel;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.usermodel.Cell;

public class ExcelSheetInfo {

	/** 读取数据开始的行与列 */
	public static final int FIRST_COL = 0;
	/** 分页名 ::: 类名 */
	private String name;
	/** 输出文件夹名 */
	private String folderName;
	/** 当前页 */
	private HSSFSheet sheet;
	/** 最大行 */
	private int maxRow;

	private List<FieldElement> fieldElements = new LinkedList<FieldElement>();
	private List<List<EGrid>> valueCollect = new LinkedList<List<EGrid>>();

	public ExcelSheetInfo(HSSFSheet sheet, File file) {
		this.sheet = sheet;
		this.name = sheet.getSheetName();
		// 最大行
		this.maxRow = sheet.getPhysicalNumberOfRows();

		// 第一行(字段名和描述)
		HSSFRow fieldTypeRow = sheet.getRow(0);
		
		// 把后缀去掉
//		this.folderName = this.name.replace("Config", "");
		this.folderName = this.name;

		for (int col = FIRST_COL; col < fieldTypeRow.getPhysicalNumberOfCells(); col++) {
			HSSFCell cell = fieldTypeRow.getCell(col);
			if (cell == null) {
				continue;
			}
			String contents = cell.getStringCellValue();
			if (StringUtils.isBlank(contents)) {
				continue;
			}
			
			if (check(contents)) {
				fieldElements.add(new FieldElement(contents, "", contents));
				continue;
			}
			
			String[] fieldNameArr = contents.split("\\|");
			if (fieldNameArr.length <= 1) {
				fieldElements.add(new FieldElement("", "", ""));
				continue;
			}
			
			String fieldName = fieldNameArr[1].trim(); // 字段名
			String fieldType = getFieldType(fieldName); // 字段类型
			if (!fieldType.equals("int")) {
				fieldName = fieldName.substring(0, fieldName.length() - 1);
			}
			
			FieldElement create = FieldElement.create(fieldNameArr[0].trim(), fieldType, fieldName);
			if (create != null) {
				fieldElements.add(create);
			}
		}

	}
	
	
	public ExcelSheetInfo(String[] items, String configName) {
		this.name = configName;
		this.folderName = this.name;

		for (int col = FIRST_COL; col < items.length; col++) {
			String contents = items[col];
			if (StringUtils.isBlank(contents))
				break;
			
			if (check(contents)) {
				fieldElements.add(new FieldElement(contents, "", contents));
				continue;
			}
			
			String[] fieldNameArr = contents.split("\\|");
			if (fieldNameArr.length <= 1) {
				fieldElements.add(new FieldElement("", "String", ""));
				continue;
			}
			
			String fieldName = fieldNameArr[1].trim(); // 字段名
			String fieldType = getFieldType(fieldName); // 字段类型
			if (!fieldType.equals("int")) {
				fieldName = fieldName.substring(0, fieldName.length() - 1);
			}
			
			FieldElement create = FieldElement.create(fieldNameArr[0].trim(), fieldType, fieldName);
			if (create != null) {
				fieldElements.add(create);
			}
		}

	}
	
	// 检查名字
	private boolean check(String name) {
		if (name == null) {
			return false;
		}
		
		Pattern pattern = Pattern.compile("[a-zA-Z]");
		char[] ch = name.toCharArray();
		for (int i = 0; i < ch.length; i++) {
			boolean valid = pattern.matcher(String.valueOf(ch[i])).matches();
			if (!valid) {
				return false;
			}
		}
		return true;
	}
	
	
	private String getFieldType(String fieldName) {
		if (fieldName.endsWith("%")) {
			return "float";
		} 
		if (fieldName.endsWith("*")) {
			return "String";
		} 
		return "int";
	}
	
	
	int findRowIndexByContent(String name) {
		for (int i = 0; i < maxRow; i++) {
			HSSFRow row = sheet.getRow(i);
			if (row != null) {
				HSSFCell cell = row.getCell(0);
				if (cell != null && cell.getStringCellValue().toLowerCase().contains(name)) {
					return row.getRowNum();
				}
			}
		}
		return -1;
	}

	public String getName() {
		return name;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}


	public int getMaxRow() {
		return maxRow;
	}


	public File toFolder() {
		return new File(ExcelConfig.RESOURCE_PATH + "/" + folderName.toLowerCase() + "/");
	}

	public File toFile() {
		return new File(ExcelConfig.RESOURCE_PATH + "/" + folderName.toLowerCase() + "/" + name + ".json");
	}
	
	// 默认从第二行开始读取
	public void read() {
		for (int row = 1; row < maxRow; row++) {
			HSSFRow hssfRow = sheet.getRow(row);
			if (hssfRow == null) {
				continue;	// 行数据为null, 跳过
			}
			String checkVal0 = getValueByCell(FIRST_COL, row);
			String checkVal1 = getValueByCell(FIRST_COL + 1, row);
			if (StringUtils.isBlank(checkVal0) 
					&& StringUtils.isBlank(checkVal1)) {
				continue;
			}
			
			List<EGrid> grids = new ArrayList<EGrid>();
			for (int col = FIRST_COL; col < fieldElements.size(); col++) {
				FieldElement element = fieldElements.get(col);
				EGrid gird = getGird(col, row, element);
				if (gird != null) {
					grids.add(gird);
				}
			}
			valueCollect.add(grids);
		}
	}

	
	public void read(String[] items) {
		if (items == null) {
			return;
		}
		List<EGrid> grids = new ArrayList<EGrid>();
		for (int col = FIRST_COL; col < fieldElements.size(); col++) {
			FieldElement element = fieldElements.get(col);
			String chineseName = element.getChineseName();
			String fieldName = element.getFieldName();
			String fieldType = element.getClazz().getSimpleName();
			String value = "";
			if (col < items.length) {
				value = items[col];
			}
			EGrid grid = new EGrid(chineseName, fieldType, fieldName, value);
			grids.add(grid);
			maxRow ++;
		}
		valueCollect.add(grids);
	}
	
	
	private String getValueByCell(int col, int row) {
		HSSFRow hssfRow = sheet.getRow(row);
		if (hssfRow != null) {
			Cell cell = hssfRow.getCell(col);
			if (cell != null) {
				return cell.toString();
			}
		}
		return "";
	}

	public ExcelContext getExcelContext() {
		return new ExcelContext().sheetName(name).configName(name).data(valueCollect).note(this.getNote())
				.folderName(folderName).modelElements(fieldElements).create();
	}

	public String getNote() {
		StringBuilder note = new StringBuilder();
		note.append("\n").append("--★★★★★★★猥琐的分割线|表格(").append(name).append(")★★★★★★★").append("\n");
		for (int col = FIRST_COL; col < fieldElements.size(); col++) {
			FieldElement element = fieldElements.get(col);
			String fieldKey = element.getFieldName();
			if (StringUtils.isNotBlank(fieldKey)) {
				note.append("--\t").append(fieldKey).append("->").append(element.getChineseName()).append("\n");
			}
		}
		return note.toString();
	}

	public String getFloderName() {
		return folderName;
	}

	public EGrid getGird(int col, int row, FieldElement element) {
		try {
			String chineseName = element.getChineseName();
			String fieldName = element.getFieldName();
			String fieldType = element.getClazz().getSimpleName();
			String value = getValueByCell(col, row);
			if (StringUtils.isBlank(fieldName)) {
				return null;
			}
			EGrid grid = new EGrid(chineseName, fieldType, fieldName, value);
			return grid;
		} catch (Exception e) {
			e.printStackTrace() ;
			return null;
		}
	}

	public String getConfigName() {
		return this.name.trim().toLowerCase();
	}
}