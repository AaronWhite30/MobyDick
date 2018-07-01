package com.mobydick;

import com.mobydick.service.FileService;
import com.mobydick.service.MobyDickService;
import org.junit.Test;

import java.util.*;

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

    Map<String,Integer> unsortedMap = new HashMap<String, Integer>()
    {{
        put("cross", 2);
        put("trees", 2);
        put("old", 1);
        put("top", 1);
        put("mast", 1);
        put("an", 2);
    }};

    Map<String,Integer> expectedSortedListForMap = new LinkedHashMap<String, Integer>()
    {{
        put("cross", 2);
        put("trees", 2);
        put("an", 2);
        put("top", 1);
        put("old", 1);
        put("mast", 1);
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

    @Test
    public void whenMapOfMobyDickWordOccurrenesSortMapDescendingLimitTo100(){

        List<Map.Entry<String, Integer>> actual = mobyDickService.sortWordOccurrencesMapDescendingLimitTo100(unsortedMap);
        Object[] sortedString = expectedSortedListForMap.keySet().toArray();
        assertEquals(sortedString[0], actual.get(0).getKey());
        assertEquals(sortedString[1], actual.get(1).getKey());
        assertEquals(sortedString[2], actual.get(2).getKey());
        assertEquals(sortedString[3], actual.get(3).getKey());
        assertEquals(sortedString[4], actual.get(4).getKey());
        assertEquals(sortedString[5], actual.get(5).getKey());
    }
}
