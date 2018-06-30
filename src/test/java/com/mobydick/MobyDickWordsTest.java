package com.mobydick;

import com.mobydick.service.MobyDickService;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;

public class MobyDickWordsTest {

    MobyDickService mobyDickService =new MobyDickService();

    private List<String> initial = Arrays.asList("Two enormous wooden pots painted black, and suspended by asses' ears,",
        "swung from the cross - trees of an old top - mast, planted in front of an",
        "old doorway. The horns of the cross-trees were sawed off on the other");

    private List<String> expected = Arrays.asList("Two", "enormous", "wooden", "pots", "painted", "black", "and", "suspended",
        "by", "asses'", "ears", "swun", "from", "the", "cross - trees", "of", "an", "old", "top - mast", "planted", "in", "front",
        "of", "an", "old", "doorway.", "The", "horns", "of", "the", "cross - trees", "were", "sawed", "off", "on", "the", "other");

    @Test
    public void whenMobyDickFileLoadMobyDickWordsList(){

        assertEquals(expected, mobyDickService.loadListFromFile(anyString()));
    }

}
