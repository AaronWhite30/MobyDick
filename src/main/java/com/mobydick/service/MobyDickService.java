package com.mobydick.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MobyDickService {

    FileService fileService = new FileService();

    public List<String> loadLinesListFromFile(String fileName){
        try {
            return fileService.loadStringListFromFile(fileName);
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    public List<String> splitLinesIntoWordsListThenFilterSpecialCharacters(List<String> linesListToBeSplitted){
        List<String> mobyDickSplitted = new ArrayList<>();

        linesListToBeSplitted
            .stream()
            .forEach(line -> {
                List<String> splitLine = Arrays.asList(line.split(" "));
                splitLine.stream()
                    .filter(string -> string.length() > 1)
                    .map(str -> str.toLowerCase().replaceAll("[\\W]",""))
                    .forEach(s -> mobyDickSplitted.add(s));
            });

        return mobyDickSplitted;
    }

    public List<String> filterOutStopWordsFromList(List<String> stopWordsList){
        return Arrays.asList("two", "enormous", "wooden", "pots", "painted", "black", "and",
            "suspended", "by", "asses", "ears", "swung", "from", "cross", "trees", "an", "old", "topmast",
            "planted", "in", "front", "an", "old", "doorway", "horns", "cross", "trees", "were", "sawed", "other");
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
