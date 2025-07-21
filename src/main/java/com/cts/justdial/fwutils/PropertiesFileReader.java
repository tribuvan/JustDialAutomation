package com.cts.justdial.fwutils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileReader {
    public static String getPropertyValue(String filename, String key) throws FileNotFoundException {
        String filepath = "testdata/"+filename+".properties";
        try(FileInputStream fis = new FileInputStream(filepath)){
            Properties p = new Properties();
            p.load(fis);
            return p.getProperty(key);
        }catch(IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
