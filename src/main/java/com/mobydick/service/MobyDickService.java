package com.mobydick.service;

import java.util.Arrays;
import java.util.List;

public class MobyDickService {

    public List<String> loadListFromFile(String fileName){
        try {
            return Arrays.asList("Two", "enormous", "wooden", "pots", "painted", "black", "and", "suspended",
                "by", "asses'", "ears", "swun", "from", "the", "cross - trees", "of", "an", "old", "top - mast", "planted", "in", "front",
                "of", "an", "old", "doorway.", "The", "horns", "of", "the", "cross - trees", "were", "sawed", "off", "on", "the", "other");
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
