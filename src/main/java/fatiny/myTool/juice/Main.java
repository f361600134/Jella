package fatiny.myTool.juice;

import fatiny.myTool.juice.core.ParamAnalysis;

public class Main {

	public static void main(String[] args) {
		try {
			ParamAnalysis.analysis();
			// System.out.println(Config.outputPath);
			Config.print();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
