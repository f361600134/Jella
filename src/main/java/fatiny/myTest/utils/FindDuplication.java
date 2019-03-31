package fatiny.myTest.utils;

import java.util.Arrays;

/**
 * @Description 找出数组中重复的数字
 *
 * @author yongh
 * @date 2018年7月16日 上午10:24:05
 */

/*
 * 题目：在一个长度为n的数组里的所有数字都在0到n-1的范围内。数组中某些数字是重复的，但不知道有几个数字重复了，
 * 也不知道每个数字重复了几次。请找出数组中任意一个重复的数字。例如，如果输入长度为7的数组{2, 3, 1, 0, 2, 5, 3}，
 * 那么对应的输出是重复的数字2或者3。
 */
public class FindDuplication {

	/**
	 * 数值标记为下标, 循环数值是否等于对应下标对应的值, 如果相等, 则表示重复,如果不相等, 则标记到指定下标. 只适用于有序的数组
	 * 
	 * @param num
	 * @return void
	 * @date 2018年12月20日上午11:54:20
	 */
	public static void getRepeateNum(int[] num) {
		System.out.println("初始数组:" + Arrays.toString(num));
		int NumChange;
		for (int index = 0; index < num.length; index++) {
			while (num[index] != index) {
				if (num[index] == num[num[index]]) {
					System.out.print(num[index] + " ");
					break;
				} else {
					NumChange = num[num[index]];
					num[num[index]] = num[index];
					num[index] = NumChange;
				}
				System.out.println("排序数组:" + Arrays.toString(num));
			}
		}
	}

	// Parameters:
	// numbers: an array of integers
	// length: the length of array numbers
	// duplication: (Output) the duplicated number in the array number,length of
	// duplication array is 1,so using duplication[0] = ? in implementation;
	// Here duplication like pointor in C/C++, duplication[0] equal *duplication
	// in C/C++
	// 这里要特别注意~返回任意重复的一个，赋值duplication[0]
	// Return value: true if the input is valid, and there are some duplications
	// in the array number
	// otherwise false
	public static boolean duplicate(int numbers[], int length, int[] duplication) {
		if (numbers == null || length <= 0)
			return false;
		for (int a : numbers) {
			if (a < 0 || a >= length)
				return false;
		}

		int temp;
		for (int i = 0; i < length; i++) {
			while (numbers[i] != i) {
				if (numbers[numbers[i]] == numbers[i]) {
					duplication[0] = numbers[i];
					return true;
				}
				temp = numbers[i];
				numbers[i] = numbers[temp];
				numbers[temp] = temp;
			}
		}
		return false;
	}

	/**
	 * 找到数组中一个重复的数字 返回-1代表无重复数字或者输入无效
	 */
	public int getDuplicate(int[] arr) {
		if (arr == null || arr.length <= 0) {
			System.out.println("数组输入无效！");
			return -1;
		}
		for (int a : arr) {
			if (a < 0 || a > arr.length - 1) {
				System.out.println("数字大小超出范围！");
				return -1;
			}
		}
		for (int i = 0; i < arr.length; i++) {
			int temp;
			while (arr[i] != i) {
				if (arr[arr[i]] == arr[i])
					return arr[i];
				// 交换arr[arr[i]]和arr[i]
				temp = arr[i];
				arr[i] = arr[temp];
				arr[temp] = temp;
			}
		}
		System.out.println("数组中无重复数字！");
		return -1;
	}

	// ==================================测试代码==================================
	/**
	 * 数组为null
	 */
	public void test1() {
		System.out.print("test1：");
		int[] a = null;
		int dup = getDuplicate(a);
		if (dup >= 0)
			System.out.println("重复数字为：" + dup);
	}

	/**
	 * 数组无重复数字
	 */
	public void test2() {
		System.out.print("test2：");
		int[] a = { 0, 1, 2, 3 };
		int dup = getDuplicate(a);
		if (dup >= 0)
			System.out.println("重复数字为：" + dup);
	}

	/**
	 * 数组数字越界
	 */
	public void test3() {
		System.out.print("test3：");
		int[] a = { 1, 2, 3, 4 };
		int dup = getDuplicate(a);
		if (dup >= 0)
			System.out.println("重复数字为：" + dup);
	}

	/**
	 * 数组带重复数字
	 */
	public void test4() {
		System.out.print("test4：");
		int[] a = { 1, 2, 3, 2, 4, 4 };
		int dup = getDuplicate(a);
		if (dup >= 0)
			System.out.println("重复数字为：" + dup);
	}

	public static void main(String[] args) {
		FindDuplication f = new FindDuplication();
		// f.test1();
		// f.test2();
		// f.test3();
		// f.test4();
		// =====================
		int[] intArr = new int[] { 1, 15, 20, 47, 15 };
		int[] duplication = new int[] { -1 };
		boolean bool = duplicate(intArr, intArr.length, duplication);
		System.out.println(bool + ", " + Arrays.toString(duplication));

		// getRepeateNum(intArr);
	}
}