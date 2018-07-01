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
        Map<String, Integer> wordOccurrenceMap = new HashMap<>();
        mobyDickStopWordsFiltered.stream()
            .forEach(word -> {
                if(wordOccurrenceMap.containsKey(word)){
                    wordOccurrenceMap.put(word, wordOccurrenceMap.get(word) + 1);
                }else{
                    wordOccurrenceMap.put(word, 1);
                }
            });
        return wordOccurrenceMap;
    }

    public List<Map.Entry<String, Integer>> sortWordOccurrencesMapDescendingLimitTo100(Map<String,Integer> unsortedMap){
        return unsortedMap
            .entrySet()
            .stream()
            .sorted(
                Map.Entry.comparingByValue(
                    (Integer i1, Integer i2) -> i2 - i1))
            .limit(100)
            .collect(Collectors.toList());
    }

    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
}
