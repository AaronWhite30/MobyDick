package com.mobydick.service;

import java.util.List;
import java.util.stream.Collectors;

public class StopWordsService {

    private FileService fileService = new FileService();

    public List<String> loadListFromFile(String fileName){
        try {
            return fileService.loadStringListFromFile(fileName)
                .stream()
                .filter(string -> !string.startsWith("#"))
                .filter(string -> string.length() > 1)
                .collect(Collectors.toList());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
