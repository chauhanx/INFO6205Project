package edu.neu.coe.info6205.msdRadix;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class TimSortChinese {

	private static int RUN = 32;
	static boolean isChinese = true;

	private int min(int a, int b){
	    return a < b ? a : b;
	}

	private void insertionSort(String a[], int beg, int end){
	    int i, j;
	    String temp;  
	    for (i = beg + 1; i <= end; i++){
	        temp = a[i];  
	        j = i - 1;  
	  
	        while(j >= beg && less(temp,a[j])){
	            a[j+1] = a[j];     
	            j = j-1;    
	        }    
	        a[j+1] = temp;    
	    }  
	} 
	
	
	public void sort(String a[]) {
		int n = a.length;
	    for (int i = 0; i < n; i+=RUN){
			insertionSort(a, i, min((i+RUN-1), (n-1)));
		}
	    for (int size = RUN; size < n; size = 2*size){
	        for (int beg = 0; beg < n; beg += 2*size){
	            int mid = beg + size - 1;
	            int end = min((beg + 2*size - 1),(n-1));  
	            if(mid < end) merge(a, beg, mid, end);
	        }  
	    }
	}
	
	private void merge(String a[], int beg, int mid, int end) {
	    int i, j, k;  
	    int n1 = mid - beg + 1;    
	    int n2 = end - mid;     

	    String[] LeftArray = new String [n1];
	    String[] RightArray = new String [n2];       

	    for (i = 0; i < n1; i++){
			LeftArray[i] = a[beg + i];
		}
	    for (j = 0; j < n2; j++){
			RightArray[j] = a[mid + 1 + j];
		}

	    i = 0;   
	    j = 0;   
	    k = beg;      
	    while (i < n1 && j < n2) {
	        if(less(LeftArray[i],RightArray[j])) a[k++] = LeftArray[i++];
	        else a[k++] = RightArray[j++];
	    }
	    while (i<n1){
	        a[k++] = LeftArray[i++];
	    }
	    while (j<n2) {
	        a[k++] = RightArray[j++];
	    }
	}   

	private static boolean less(String v, String w) {
		return helper.compare(v,w) <= 0;
	}
	
	public static void main(String args[]) throws IOException {
		try{
		    IOTextFile io = new IOTextFile();
			String[] a = io.readFileStreamByLength(isChinese,2000);
			TimSortChinese t1 = new TimSortChinese();
			t1.sort(a);

			System.out.println(Arrays.toString(a));
	      }
		catch(FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		}
	} 
}

