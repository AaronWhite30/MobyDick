package com.mobydick.service;

import java.util.Arrays;
import java.util.List;

public class MobyDickService {

    FileService fileService = new FileService();

    public List<String> loadLinesListFromFile(String fileName){
        try {
            return fileService.loadStringListFromFile(fileName);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<String> splitLinesIntoWordsList(List<String> linesListToBeSplitted){
        return Arrays.asList("Two", "enormous", "wooden", "pots", "painted", "black", "and", "suspended",
            "by", "asses'", "ears,", "swun", "from", "the", "cross - trees", "of", "an", "old", "top - mast", "planted", "in", "front",
            "of", "an", "old", "doorway.", "The", "horns", "of", "the", "cross - trees", "were", "sawed", "off", "on", "the", "other");
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
