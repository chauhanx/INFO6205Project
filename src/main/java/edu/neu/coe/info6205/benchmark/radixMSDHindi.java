package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Resources;

import edu.neu.coe.info6205.msdRadix.IOTextFile;
import edu.neu.coe.info6205.msdRadix.MSDRadixHindi;
import edu.neu.coe.info6205.msdRadix.QuickDualPivot;
import edu.neu.coe.info6205.sort.Helper;
import edu.neu.coe.info6205.sort.HelperFactory;
import edu.neu.coe.info6205.util.Config;
//import INFO6205.src.test.java.edu.neu.coe.info6205.util.ConfigTest;
//import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.Timer;

public class radixMSDHindi {
	   static boolean isChinese = false;
	
	public void msdHindi() {
		
	    	   String[] a = null;

	    	 try{
	    		 IOTextFile io = new IOTextFile();
	             List<String> list = io.readStream(isChinese);
	              a = list.toArray(new String[0]);
	             String[] aux = new String[a.length];

	         }catch(FileNotFoundException ex){
	             System.out.println(ex.getMessage());
	         } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   

	         MSDRadixHindi sorter = new MSDRadixHindi();
	         Timer timer = new Timer();
	         final String[] temp =a;
	          double mean = timer.repeat(10, () -> temp, t -> {
	        	 sorter.sort(temp);
	             return null;
	         });
	        System.out.println("Time taken for MSD Radix Hindi Sorted Array: "+mean);
	    
	}
	
	public void quickDualHindi() {
		
		  String[] a = null;

	    	 try{
	    		 IOTextFile io = new IOTextFile();
	             List<String> list = io.readStream(isChinese);
	              a = list.toArray(new String[0]);
	             String[] aux = new String[a.length];

	         }catch(FileNotFoundException ex){
	             System.out.println(ex.getMessage());
	         } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	         QuickDualPivot sorter = new QuickDualPivot();
	         Timer timer = new Timer();
	         final String[] temp =a;
	          double mean = timer.repeat(10, () -> temp, t -> {
	        	 sorter.sort(temp);
	             return null;
	         });
	        System.out.println("Time taken for Hindi Quick Dual Sorted Array: "+mean);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		radixMSDHindi r=new radixMSDHindi();
		r.msdHindi();
		r.quickDualHindi();
	}

}
