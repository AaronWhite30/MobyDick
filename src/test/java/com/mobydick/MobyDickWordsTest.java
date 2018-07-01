package com.mobydick;

import com.mobydick.service.FileService;
import com.mobydick.service.MobyDickService;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MobyDickWordsTest {

    MobyDickService mobyDickService =new MobyDickService();

    private List<String> initial = Arrays.asList("Two enormous wooden pots painted black, and suspended by asses' ears,",
        "swung from the cross - trees of an old top - mast, planted in front of an",
        "old doorway. The horns of the cross - trees were sawed off on the other");

    private List<String> expectedWordsList = Arrays.asList("two", "enormous", "wooden", "pots", "painted", "black", "and", "suspended",
        "by", "asses", "ears", "swung", "from", "the", "cross", "trees", "of", "an", "old", "top", "mast", "planted", "in", "front",
        "of", "an", "old", "doorway", "the", "horns", "of", "the", "cross", "trees", "were", "sawed", "off", "on", "the", "other");

    private List<String> stopWordsList = Arrays.asList("about", "above", "across", "the", "on", "of", "off");

    private List<String> expectedStopWordsFilteredList = Arrays.asList("two", "enormous", "wooden", "pots", "painted", "black", "and",
        "suspended", "by", "asses", "ears", "swung", "from", "cross", "trees", "an", "old", "top", "mast",
        "planted", "in", "front", "an", "old", "doorway", "horns", "cross", "trees", "were", "sawed", "other");

    List<String> stopFilteredListForMap = Arrays.asList("cross", "trees", "an", "old", "top", "mast", "an", "cross", "trees");

    Map<String,Integer> expectedListForMap = new HashMap<String, Integer>()
    {{
        put("cross", 2);
        put("trees", 2);
        put("old", 1);
        put("top", 1);
        put("mast", 1);
        put("an", 2);
    }};

    @Test
    public void whenMobyDickFileLoadMobyDickLinesList(){

        FileService fileService = mock(FileService.class);
        when(fileService.loadStringListFromFile(anyString())).thenReturn(initial);

        mobyDickService.setFileService(fileService);

        assertEquals(initial, mobyDickService.loadLinesListFromFile(anyString()));
    }

    @Test
    public void whenMobyDickLinesListSplitIntoWordsListWithoutSpecialCharacters(){

        assertEquals(expectedWordsList, mobyDickService.splitLinesIntoWordsListThenFilterSpecialCharacters(initial));
    }

    @Test
    public void whenMobyDickWordsListFilterOutStopWords(){

        assertEquals(expectedStopWordsFilteredList, mobyDickService.filterOutStopWordsFromList(expectedWordsList, stopWordsList));
    }

    @Test
    public void whenMobyDickWordListCreateMapOfWordOccurrences(){

        assertEquals(expectedListForMap, mobyDickService.createMapOfWordOccurrences(stopFilteredListForMap));
    }
}
