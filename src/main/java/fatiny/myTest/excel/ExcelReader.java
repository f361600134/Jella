package fatiny.myTest.excel;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import fatiny.myTest.utils.StringHelper;

public class ExcelReader {

	/** <XXXConfig, 数据> */
	private static Map<String, ExcelContext> excelContexts = new HashMap<String, ExcelContext>();

	public static void main(String[] args) throws Exception {
		loadProps();
		File folder = new File(ExcelConfig.EXCEL_PATH);
		readExcel(folder);

		System.out.println("\n生成文件:");
		int num = 0;
		for (ExcelContext context : excelContexts.values()) {
			num++;
			System.out.println(context.getNote());
			context.toJsonFile();
			createJavaFile(context);
		}
		System.out.print("共" + num + "个文件");
	}

	private static void loadProps() {
		try {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream("path.properties");
				Properties p = new Properties();
				p.load(fis);
				String importVal = p.getProperty("import.path");
				if (!StringUtils.isBlank(importVal)) {
					ExcelConfig.EXCEL_PATH = importVal;
				}
				String exportVal = p.getProperty("export.path");
				if (!StringUtils.isBlank(importVal)) {
					ExcelConfig.RESOURCE_PATH = exportVal;
				}
			} finally {
				fis.close();
			}
		} catch (Exception e) {
			System.out.println("找不到Excel配置路径, 使用默认路径加载");
		}
		System.out.println(ExcelConfig.EXCEL_PATH);
	}

	private static void createJavaFile(ExcelContext context) {
		if (!new File(ExcelConfig.JAVA_FILE_PATH).exists()) {
			new File(ExcelConfig.JAVA_FILE_PATH).mkdirs();
		}
		JavaFileGenerator.create().excelData(context).build().createFiles();
	}

	public static void readExcel(File folder) throws Exception {
		File[] files = folder.listFiles();
		for (File file : files) {
			try {
				if (file.isDirectory()) {
					readExcel(file);
				} else if (file.getName().endsWith(".xls")) {
					InputStream inputStream = new FileInputStream(file);
					POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
					HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
					ExcelInfo excelInfo = new ExcelInfo(wb, file);
					excelInfo.read();

					List<ExcelSheetInfo> excelSheetInfos = excelInfo.getExcelSheetInfos();
					// 循环excel页面
					for (ExcelSheetInfo excelSheetInfo : excelSheetInfos) {
						// excel名字
						String configName = excelSheetInfo.getConfigName();
						// 有无保存此页面信息
						ExcelContext excelContext = excelContexts.get(configName);
						if (excelContext == null) {
							excelContext = excelSheetInfo.getExcelContext();
							excelContexts.put(configName, excelContext);
						} else {
							// excelContext.merge(excelSheetInfo.getExcelContext());
							System.err.println("Excel页标签名有冲突, xls->" + file.getName());
						}

					}
					inputStream.close();
				} else if (file.getName().endsWith(".xlsx")) {
					InputStream inputStream = new FileInputStream(file);
					POIFSFileSystem poifsFileSystem = new POIFSFileSystem(inputStream);
					HSSFWorkbook wb = new HSSFWorkbook(poifsFileSystem);
					ExcelInfo excelInfo = new ExcelInfo(wb, file);
					excelInfo.read();

					List<ExcelSheetInfo> excelSheetInfos = excelInfo.getExcelSheetInfos();
					for (ExcelSheetInfo excelSheetInfo : excelSheetInfos) {
						String configName = excelSheetInfo.getConfigName();
						ExcelContext excelContext = excelContexts.get(configName);
						if (excelContext == null) {
							excelContext = excelSheetInfo.getExcelContext();
							excelContexts.put(configName, excelContext);
						} else {
							// excelContext.merge(excelSheetInfo.getExcelContext());
							System.err.println("Excel页标签名有冲突, xls->" + file.getName());
						}

					}
					inputStream.close();
				} else if (file.getName().endsWith(".csv")) {
					String configName = file.getName().split(".csv")[0];
					if (configName.endsWith("new")) {
						continue;
					}

					configName = StringHelper.underlineToUpperCamal(configName);
					FileInputStream fis = new FileInputStream(file);
					InputStreamReader isr = new InputStreamReader(fis, "gbk"); // gbk?
					BufferedReader reader = new BufferedReader(isr);

					String firstLine = reader.readLine(); // 第一行信息，为标题信息
					String firstItems[] = firstLine.split(",");// CSV格式文件为逗号分隔符文件，这里根据逗号切分
					ExcelSheetInfo sheetInfo = new ExcelSheetInfo(firstItems, configName);

					String line = null;
					while ((line = reader.readLine()) != null) {
						String items[] = line.split(","); // CSV格式文件为逗号分隔符文件，这里根据逗号切分
						sheetInfo.read(items);
						excelContexts.put(configName, sheetInfo.getExcelContext());
					}
					reader.close();
				}
			} catch (Exception e) {
				System.err.println("读取文件" + file.getName() + "异常");
				e.printStackTrace();
			}
		}

	}

}
