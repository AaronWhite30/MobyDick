package com.mobydick.service;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class FileService {

    public List<String> loadStringListFromFile(String fileName){
        try {
            return Files.lines(Paths.get(
                System.getProperty("user.dir")).resolve(fileName))
                .collect(Collectors.toList());
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }
}
