package edu.neu.coe.info6205.benchmark;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import edu.neu.coe.info6205.msdRadix.IOTextFile;
import edu.neu.coe.info6205.msdRadix.MSDRadixHindi;
import edu.neu.coe.info6205.msdRadix.QuickDualPivot;
import edu.neu.coe.info6205.msdRadix.TimSortHindi;
//import INFO6205.src.test.java.edu.neu.coe.info6205.util.ConfigTest;
//import edu.neu.coe.info6205.util.PrivateMethodTester;
import edu.neu.coe.info6205.util.Timer;

public class BenchmarkHindi {
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
	
	public void timSortHindi() {
		
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

	    	 TimSortHindi sorter = new TimSortHindi();
	         Timer timer = new Timer();
	         final String[] temp =a;
	          double mean = timer.repeat(10, () -> temp, t -> {
	        	 sorter.timSort(temp,temp.length);
	             return null;
	         });
	        System.out.println("Time taken for Hindi Tim Sorted Array: "+mean);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BenchmarkHindi r=new BenchmarkHindi();
		r.msdHindi();
		r.quickDualHindi();
		r.timSortHindi();
	}

}
