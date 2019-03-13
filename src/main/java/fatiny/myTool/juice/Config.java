package fatiny.myTool.juice;

import java.util.List;
import java.util.Map;

import fatiny.myTool.juice.core.ParamMapping;

@ParamMapping(filePath = "config/config.properties", prefix = "combine")
public class Config {

	public static String outputPath;

	public static List<Integer> lista;

	public static Map<Integer, Integer> mapa;

	// ===========================================
	public static List<Stu> stulist;
	public static Map<Integer, Stu> stumap;

	public String getOutputPath() {
		return outputPath;
	}

	public void setOutputPath(String outputPath) {
		Config.outputPath = outputPath;
	}

	public List<Integer> getLista() {
		return lista;
	}

	public void setLista(List<Integer> lista) {
		Config.lista = lista;
	}

	public Map<Integer, Integer> getMapa() {
		return mapa;
	}

	public void setMapa(Map<Integer, Integer> mapa) {
		Config.mapa = mapa;
	}

	public static void print() {
		System.out.println("====outputPath====:" + outputPath);
		System.out.println("====lista====:" + lista);
		System.out.println("====mapa====:" + mapa);
		System.out.println("====stulist====:" + stulist);
		System.out.println("====stumap====:" + stumap);
		System.out.println(stumap.get(0).getGrade() == 50);
	}

	public static void main(String[] args) {
		String aa = "";
		boolean bb = String.class.isAssignableFrom(aa.getClass());
		System.out.println(bb);

		bb = Object.class.isAssignableFrom(aa.getClass());
		System.out.println(bb);

		Stu stu = new Stu();
		bb = Object.class.isAssignableFrom(stu.getClass());
		System.out.println(bb);

		bb = String.class.isAssignableFrom(stu.getClass());
		System.out.println(bb);
	}

}
