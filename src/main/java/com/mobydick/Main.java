package com.mobydick;

import com.mobydick.service.FileService;
import com.mobydick.service.MobyDickService;
import com.mobydick.service.StopWordsService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) throws Exception{

        ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");

        StopWordsService stopWordsService = (StopWordsService) context.getBean("stopWordsService");
        MobyDickService mobyDickService = (MobyDickService) context.getBean("mobyDickService");
        FileService fileService = (FileService) context.getBean("fileService");

        stopWordsService.setFileService(fileService);
        mobyDickService.setFileService(fileService);

        List<String> stopWordsList = stopWordsService.loadListFromFile("assets/stop-words.txt");

        List<String> mobyDickFileLines = mobyDickService.loadLinesListFromFile("assets/mobydick.txt");

        List<String> mobyDickSplittedWordList = mobyDickService.splitLinesIntoWordsListThenFilterSpecialCharacters(mobyDickFileLines);

        List<String> mobyDickStopWordsFiltered = mobyDickService.filterOutStopWordsFromList(mobyDickSplittedWordList, stopWordsList);

        Map<String, Integer> mobyDickWordOccurrencesMap = mobyDickService.createMapOfWordOccurrences(mobyDickStopWordsFiltered);

        List<Map.Entry<String, Integer>> sortedWordOccurrencesMap = mobyDickService.sortWordOccurrencesMapDescendingLimitTo100(mobyDickWordOccurrencesMap);

        sortedWordOccurrencesMap.forEach(map -> {
            System.out.println("Word: "+map.getKey()+" occurs "+map.getValue()+" times");
        });
    }

}
