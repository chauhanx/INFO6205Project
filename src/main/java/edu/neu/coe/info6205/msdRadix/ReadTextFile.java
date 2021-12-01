package edu.neu.coe.info6205.msdRadix;

import com.google.common.io.Resources;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ReadTextFile {


    public ArrayList<String> readStream() throws IOException {
        try{
            String fileName = "hindi.txt";
            URL url = Resources.getResource(fileName);
            ArrayList<String> lines = new ArrayList<>(Resources.readLines(url, StandardCharsets.UTF_8));
            return lines;
        }catch(FileNotFoundException ex){
            System.out.println(ex.getMessage());
            return null;
        }

    }
}
