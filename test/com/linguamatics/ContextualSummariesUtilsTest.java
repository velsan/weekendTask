package com.linguamatics;

import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.AbstractMap;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by vojtech on 26/05/17.
 */
public class ContextualSummariesUtilsTest {

//    private ContextualSummariesUtils contextualSummariesUtils;
//
//    @Before
//    public void init(){
//        this.contextualSummariesUtils = new ContextualSummariesUtils();
//    }
//
//    @Test(expected = RuntimeException.class)
//    public void should_throw_error_for_non_existing_file() throws Exception {
//        contextualSummariesUtils.getWordPositions("non-existing-file");
//    }

//    @Test
//    public void should_handle_empty_file() throws URISyntaxException {
//        final String path = ClassLoader.getSystemResource("csv/emptyFile.csv").getPath();
//        final LinkedList<AbstractMap.SimpleEntry<Integer, Integer>> wordPositions = contextualSummariesUtils.parsePhrasePositionsFromCSV(path);
//        assertThat(wordPositions, notNullValue());
//        assertThat(wordPositions.size(), is(0));
//    }

//    @Test
//    public void should_process_valid_file(){
//        final String path = ClassLoader.getSystemResource("csv/wordPositions.csv").getPath();
//        final LinkedList<AbstractMap.SimpleEntry<Integer, Integer>> wordPositions = contextualSummariesUtils.parsePhrasePositionsFromCSV(path);
//        assertThat(wordPositions, notNullValue());
//        assertThat(wordPositions.size(), is(3));
//        assertThat(wordPositions.getFirst().getKey(), is(6));
//        assertThat(wordPositions.getFirst().getValue(), is(1));
//    }
}