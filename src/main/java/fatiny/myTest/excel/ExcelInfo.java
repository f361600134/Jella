package fatiny.myTest.excel;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelInfo {

	private final int sheetSize;
	private final HSSFWorkbook workbook;
	private final List<ExcelSheetInfo> excelSheetInfos = new LinkedList<ExcelSheetInfo>();

	/**
	 * 获取excel分页 ExcelInfo
	 * 
	 * @param workbook
	 * @param file
	 */
	public ExcelInfo(HSSFWorkbook workbook, File file) {
		this.workbook = workbook;
		this.sheetSize = workbook.getNumberOfSheets();
		for (int i = 0; i < this.sheetSize; i++) {
			HSSFSheet sheetAt = workbook.getSheetAt(i);
			String sheetName = sheetAt.getSheetName();
			if (!check(sheetName)) {
				System.out.println("未读取的Excel分页:" + file.getPath() + "->" + sheetName);
				continue;
			}
			ExcelSheetInfo sheetInfo = new ExcelSheetInfo(sheetAt, file);
			excelSheetInfos.add(sheetInfo);
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

	public int getSheetSize() {
		return sheetSize;
	}

	public HSSFWorkbook getWorkbook() {
		return workbook;
	}

	public List<ExcelSheetInfo> getExcelSheetInfos() {
		return excelSheetInfos;
	}

	public void read() {
		for (ExcelSheetInfo excelSheetInfo : excelSheetInfos) {
			excelSheetInfo.read();
		}
	}
}