package com.fatiny;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    public static void bubbleSort() {
		int[] arr = { 12, 23, 34, 56, 56, 56, 78 };
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {// -1为了防止溢出
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];

					arr[j] = arr[j + 1];

					arr[j + 1] = temp;
				}
			}
		}
	}

}
