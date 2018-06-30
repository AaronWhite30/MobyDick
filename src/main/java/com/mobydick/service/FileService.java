package com.mobydick.service;

import java.util.Arrays;
import java.util.List;

public class FileService {

    public List<String> loadStringListFromFile(String fileName){
        try {
            return Arrays.asList("about", "above", "across", "the", "on", "of", "off");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
