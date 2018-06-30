package com.mobydick.service;

import java.util.Arrays;
import java.util.List;

public class StopWordsService {

    public List<String> loadListFromFile(){
        try {
            return Arrays.asList("about", "above", "across", "the", "on", "of", "off");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
