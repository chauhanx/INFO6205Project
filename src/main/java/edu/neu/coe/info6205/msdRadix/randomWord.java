package edu.neu.coe.info6205.msdRadix;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class randomWord {
	String generateRandomWord(int wordLength) throws IOException {
	    Random r = new Random(); // Intialize a Random Number Generator with SysTime as the seed
	    StringBuilder sb = null;
	    int count=0;
	    FileWriter fw = new FileWriter("C:\\git\\PSA\\PSA_Project3\\INFO6205Project\\src\\main\\resources\\hindiWords.txt",false);
	    for(int i = 2309; i < 2330; i++)
	     { // For each letter in the word
	    	for(int j=i+1;j<=2330;j++)
	    	{
	    		for(int k=j+1;k<=2362;k++)
	    		{
	    			for(int l=k+1;l<=2362;l++)
	    			{
//	    				for(int l=k+1;l<=2362;l++)
//		    			{
	    				
	    					
	    					
	    					sb = new StringBuilder(wordLength);
				    		 char a = (char) i;
				    		 char b = (char) j;
				    		 char c = (char) k;
				    		 char d = (char) l;
				    		
				    		 
				    		count++;
				    		 String tmp= sb.append(a).append(b).append(c).append(d).toString();
				    		 
				    		 try {
								
								System.out.println(tmp);
								  fw.write(tmp);
								  fw.write("\n");
								 
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	    				}
	    				
	    					
				    		
	    				
	    			
	    			
	    		}
	    		
	    		
	    	}
	      
	       
	       // sb.append(tmp); // Add it to the String
	    }
	    System.out.println(count);
	    fw.close();
	    return sb.toString();
	   
	   
	}
	public static void main(String args[])
	{
		randomWord r=new randomWord();
		try {
			System.out.println(r.generateRandomWord(3));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
