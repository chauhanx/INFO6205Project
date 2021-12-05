package edu.neu.coe.info6205.sort.counting;


import edu.neu.coe.info6205.msdRadix.TimSortHindi;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

public class TimSortTest {
	String[] input = "आके कान्हा फिर से बंशी बजा दे कलयुगी गोपियों को फिर से नचा दे आके कान्हा तू फिर से बंशी बजा दे".split(" ");
    String[] expected = "आके आके कलयुगी कान्हा कान्हा को गोपियों तू दे दे दे नचा फिर फिर फिर बंशी बंशी बजा बजा से से से".split(" ");
    
    @Test
    public void sort() {
        System.out.println("Test case 1:");
        System.out.println("Before sorting: " + Arrays.toString(input));
        TimSortHindi.timSort(input,input.length);
        System.out.println("After sorting: " + Arrays.toString(input));
        assertArrayEquals(expected, input);
        System.out.println();
    }
    
    @Test
    public void sort1() {
        System.out.println("Test case 1:");
        System.out.println("Before sorting: " + Arrays.toString(input));
        TimSortHindi.insertionSort(input, 0, 21);
        System.out.println("After sorting: " + Arrays.toString(input));
        assertArrayEquals(expected, input);
        System.out.println();
    }
    
    
}
