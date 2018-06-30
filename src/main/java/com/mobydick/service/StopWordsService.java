package com.mobydick.service;

import java.util.List;

public class StopWordsService {

    private FileService fileService = new FileService();

    public List<String> loadListFromFile(String fileName){
        try {
            return fileService.loadStringListFromFile(fileName);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
