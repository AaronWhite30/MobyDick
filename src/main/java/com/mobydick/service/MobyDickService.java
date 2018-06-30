package com.mobydick.service;

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

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
