package fatiny.myTest.guava;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Charsets;
import com.google.common.base.Function;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Sets;
import com.google.common.collect.Sets.SetView;
import com.google.common.hash.Hashing;
import com.google.common.net.InternetDomainName;

import fatiny.myTest.pojo.Student;

public class geavaTest {

	/**
	 * 字符串去整或者字符串取串
	 */
	@Test
	public void testChar() {
		String txt = "some text 89983 and more";
		String string = CharMatcher.digit().retainFrom(txt);
		System.out.println("从字符串中得到所有的数字:" + string);

		string = CharMatcher.digit().removeFrom("some text 89983 and more");
		System.out.println("把字符串中的数据都去掉:" + string);

	}

	/**
	 * 字符串去整或者字符串取串
	 */
	@Test
	public void testCollection() {

		// 预备数据
		Student stu1 = new Student(1, "Albee");
		Student stu2 = new Student(2, "Brain");
		Student stu3 = new Student(3, "Catherine");

		// 把list转化成以id为key的map, 添加的只是引用
		List<Student> list = Lists.newArrayList(stu1, stu2, stu3);
		Map<Integer, Student> listTopMap = Maps.uniqueIndex(list.iterator(), new Function<Student, Integer>() {
			@Override
			public Integer apply(Student arg0) {
				return arg0.getId();
			}
		});

		System.out.println("list转化成一个以id为key的Map:" + listTopMap);

		// 把set转成map, 相当于把Maps.uniqueIndex key value 反过来
		Set<Student> set = Sets.newHashSet(stu1, stu2, stu3);
		Map<Student, Integer> listTopMap2 = Maps.asMap(set, new Function<Student, Integer>() {
			@Override
			public Integer apply(Student arg0) {
				return arg0.getId();
			}
		});
		System.out.println("set转化成一个以id为key的Map:" + listTopMap2);

		// 处理Map中的value值
		Map<Integer, String> listTopMap3 = Maps.transformValues(listTopMap, new Function<Student, String>() {
			@Override
			public String apply(Student arg0) {
				return arg0.getName();
			}
		});
		System.out.println("提取对象关键信息构成map:" + listTopMap3);

	}

	/**
	 * LOWER_CAMEL, eg. ClassName LOWER_HYPHEN, eg. lower-hyphen
	 * LOWER_UNDERSCORE, eg. lower_underscore UPPER_CAMEL, eg. UpperCamel
	 * UPPER_UNDERSCORE, eg. UPPER_UNDERSCORE
	 */
	@Test
	public void testCaseFormat() {
		String name = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "ClassName");
		System.out.println("一种命名格式转成另一种格式:" + name);
	}

	@Test
	public void testSet() {
		// 尝试使用multimap
		Multiset<String> multiset = HashMultiset.create();
		multiset.add("a");
		multiset.add("b", 5);
		System.out.println("Multiset" + multiset);

		// multiMap中还提供了Set进行集合合并,求差等
		// 构建一个不能改变的其实数据内容的set
		/*
		 * union(Set, Set) intersection(Set, Set) difference(Set, Set)
		 * symmetricDifference(Set, Set)
		 */
		Set<String> set1 = ImmutableSet.of("one", "two", "three");
		Set<String> set2 = ImmutableSet.of("three", "four");

		SetView<String> union = Sets.union(set1, set2);
		System.out.println("并集Set " + union);
		SetView<String> intersection = Sets.intersection(set1, set2);
		System.out.println("交集Set " + intersection);
		SetView<String> difference = Sets.difference(set1, set2);
		System.out.println("A差集Set " + difference);
		SetView<String> symmetricDifference = Sets.symmetricDifference(set1, set2);
		System.out.println("等值差集Set " + symmetricDifference);

	}

	/**
	 * 用Guava编写hash算法
	 */
	@Test
	public void testmd5() {
		String str = "123456";
		String md5 = Hashing.md5().newHasher().putString(str, Charsets.UTF_8).hash().toString();
		System.out.println(md5);
	}

	/**
	 * guava提供检测域名工具
	 */
	public void testDomainName() {
		InternetDomainName owner = InternetDomainName.from("www.fatiny.com").topPrivateDomain(); // returns
																									// takipi.com
		System.out.println(owner);
		System.out.println(InternetDomainName.isValid("takipi.monsters"));// returns
																			// false
	}

	public static void main(String[] args) {
		Set<Short> set = Sets.newHashSet();
		for (short i = 0; i < 100; i++) {
			set.add(i);
			set.remove(i - 1);
		}
		System.out.println(set.size());
		// int notReallyInt = UnsignedInts.parseUnsignedInt("4294967295"); //
		// Max unsigned int
		// System.out.println(notReallyInt);
		// String maxUnsigned = UnsignedInts.toString(notReallyInt); // We’re
		// legit!
		// System.out.println(maxUnsigned);

	}

}
