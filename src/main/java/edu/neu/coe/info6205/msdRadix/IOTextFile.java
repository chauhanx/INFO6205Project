package edu.neu.coe.info6205.msdRadix;

import com.google.common.io.Resources;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class IOTextFile {

    private boolean isChinese = true;

    public ArrayList<String> readStream(boolean isChinese) throws IOException {
        try{
            this.isChinese = isChinese;
            String fileName = getInputFileName();
            URL url = Resources.getResource(fileName);
            ArrayList<String> lines = new ArrayList<>(Resources.readLines(url, StandardCharsets.UTF_8));
            return lines;
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
            return null;
        }
    }


    public void writeStream(String[] arr) throws IOException {
        try {
            File file = new File(getOutputFileName()); //filepath is being passes through //ioc         //and filename through a method

            if (file.exists()) {
                file.delete(); //you might want to check if delete was successfull
            }
            FileWriter writer = new FileWriter(getOutputFileName(), true);
            for (int i = 0; i < arr.length; i++) {
                writer.write(arr[i]);
                writer.write("\n");   // write new line
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private String getOutputFileName(){
        return isChinese ? "chineseOutput.txt":"hindiOutput.txt";
    }

    private String getInputFileName(){
        return isChinese ? "chinese.txt":"hindi.txt";
    }
}