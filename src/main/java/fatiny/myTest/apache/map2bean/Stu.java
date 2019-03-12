package fatiny.myTest.apache.map2bean;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;

import com.alibaba.fastjson.JSONObject;

import fatiny.myTest.utils.Bean2MapUtil;

public class Stu {

	private int id;
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Stu() {
	}

	public Stu(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Override
	public String toString() {
		return "Stu [id=" + id + ", name=" + name + "]";
	}

	public static void main(String[] args) {
		Stu stu = new Stu(1, "a");
		Map<String, String> map = null;
		try {
			// 使用apache工具类转换
			map = BeanUtils.describe(stu);
			System.out.println("use BeanUtils:" + map);

			// 使用自定义函数转换
			Map<String, Object> map2 = Bean2MapUtil.convertBean(stu);
			System.out.println("use define method:" + map2);

			Stu stu2 = Bean2MapUtil.convertMap(Stu.class, map2);
			System.out.println("use define method:" + stu2);

			// 使用fastjson转换
			JSONObject jsonBoj = new JSONObject(map2);
			stu = jsonBoj.toJavaObject(Stu.class);
			System.out.println("use fastjson:" + stu);

		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
