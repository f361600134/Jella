package fatiny.myTest.bitwise;

import org.junit.Test;

public class BitwiseTest {
	
	
	private void gege(String value){
		System.out.println("=================="+value+"==================");
	}
	
	/**
	 * @note and
	 * a,b为true, 结果为true. 其他情况结果都为false
	 */
	@Test
	public void testAnd(){
		gege("testAnd");
		boolean a = true;
		Boolean b = true;
		a &= b;
		System.out.println("a and b are true, the result is "+a);
		
		a = false;
		b = false;
		a &= b;
		System.out.println("a and b are false, the result is "+a);
		
		a = true;
		b = false;
		a &= b;
		System.out.println("a is true, b is false, the result is "+a);
		
		a = false;
		b = true;
		a &= b;
		System.out.println("a is false, b is true, the result is "+a);
	}
	
	/**
	 * @note or
	 * a,b 中存在true, 结果就为true.
	 */
	@Test
	public void testOr(){
		gege("testOr");
		
		boolean a = true,b = true;
		a|=b;
		System.out.println("a and b are true, the result is "+a);
		
		a = false;
		b = false;
		a|=b;
		System.out.println("a and b are false, the result is "+a);
		
		a = true;
		b = false;
		a|=b;
		System.out.println("a is true, b is false, the result is "+a);
		
		a = false;
		b = true;
		a|=b;
		System.out.println("a is false, b is true, the result is "+a);
	}
	
	/**
	 * @note ^ 或者 xor
	 * a,b 相同结果为false, 不同结果为true
	 */
	@Test
	public void testXor(){
		gege("testXor");

		boolean a = true,b = true;
		a ^= b;
		System.out.println("a and b are true, the result is "+a);
		
		a = false;
		b = false;
		a ^= b;
		System.out.println("a and b are false, the result is "+a);
		
		a = true;
		b = false;
		a ^= b;
		System.out.println("a is true, b is false, the result is "+a);
		
		a = false;
		b = true;
		a ^= b;
		System.out.println("a is false, b is true, the result is "+a);
	}
	
	/**
	 * @note ~ or not
	 * a,b 相同结果为false, 不同结果为true
	 */
	@Test
	public void testNot(){
		gege("testNot");

		int a = 123;
		System.out.println("a "+(~a));
	}
	
	/*
	 * | 60 00111100, 13 00001101, -> 61 00111101 //转成二进制后从高位开始比较,两个数任意一个数为1则1,不同为0
	 * & 60 00111100, 13 00001101, -> 12 00001100 // 转成二进制后从高位开始比较, 两个数都为1则为1, 不同为0
	 * ^ 60 00111100, 13 00001101, -> 49 00110001 // 转成二进制后从高位开始比较, 两个数相同则为0, 不同为1
	 * ~ 60 00111100, -> 00111100, -> -61 11000011前面全部补1
	 * <<60 2 00111100, -> 240 11110000, //在二进制右位补两个0
	 * >>60 2 00111100, -> 15 00001111, //在二进制左位补两个0
	 */
	public static void main(String[] args) {
		int a = 60; /* 60 = 0011 1100 */
		int b = 13; /* 13 = 0000 1101 */

		System.out.println(Integer.toBinaryString(a));// 00111100
		System.out.println(Integer.toBinaryString(b));// 00001101
		System.out.println(-0b11000010);
		System.out.println(Integer.toBinaryString(-61));

		int c = 0;
		c = a & b; /* 12 = 0000 1100 */
		System.out.println("a & b = " + c);

		c = a | b; /* 61 = 0011 1101 */
		System.out.println("a | b = " + c);

		c = a ^ b; /* 49 = 0011 0001 */
		System.out.println("a ^ b = " + c);

		c = ~a; /*-61 = 1100 0011 */
		System.out.println("~a = " + c);

		c = a << 2; /* 240 = 1111 0000 */
		System.out.println("a << 2 = " + c);

		c = a >> 2; /* 15 = 1111 */
		System.out.println("a >> 2  = " + c);

		c = a >>> 2; /* 15 = 0000 1111 */
		System.out.println("a >>> 2 = " + c);

		// for (int i = 0; i < 20; i++) {
		// System.out.println(Integer.toBinaryString(i));
		// }
	}

}
