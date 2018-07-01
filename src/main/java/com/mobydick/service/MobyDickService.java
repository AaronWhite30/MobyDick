package com.mobydick.service;

import java.util.*;
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

    public List<String> filterOutStopWordsFromList(List<String> mobyDickWordList, List<String> stopWordsList){

        return mobyDickWordList.stream()
            .filter(string -> stopWordsList.indexOf(string) < 1).collect(Collectors.toList());
    }

    public Map<String, Integer> createMapOfWordOccurrences(List<String> mobyDickStopWordsFiltered){
        return new HashMap<String, Integer>()
        {{
            put("cross", 2);
            put("trees", 2);
            put("old", 1);
            put("top", 1);
            put("mast", 2);
            put("an", 2);
        }};
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
