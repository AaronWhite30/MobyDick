package com.mobydick;

import com.mobydick.service.FileService;
import com.mobydick.service.StopWordsService;
import junit.framework.TestCase;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class StopWordsTest {

    private StopWordsService stopWordsService = new StopWordsService();

    private List<String> initial = Arrays.asList(
        "# Freely available stopword list.  This stopword",
        "# list provides a nice balance between coverage",
        "about",
        " ",
        "above",
        " ",
        "across",
        " ",
        "the",
        " ",
        "on",
        " ",
        "of",
        " ",
        "off");

    private List<String> expected = Arrays.asList("about", "above", "across", "the", "on", "of", "off");

    @Test
    public void whenStopWordFileLoadStopWordsList(){

        FileService fileService = mock(FileService.class);
        when(fileService.loadStringListFromFile(anyString())).thenReturn(initial);

        stopWordsService.setFileService(fileService);

        assertEquals(expected, stopWordsService.loadListFromFile(anyString()));
    }
}
