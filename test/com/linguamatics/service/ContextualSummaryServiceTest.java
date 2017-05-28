package com.linguamatics.service;

import com.linguamatics.service.ContextualSummaryService;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ContextualSummaryServiceTest {

    private ContextualSummaryService contextualSummaryService;

    @Before
    public void init(){
        this.contextualSummaryService = new ContextualSummaryService();
    }

    @Test
    public void should_process_one_sentence(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final String sourceTextPath = ClassLoader.getSystemResource("csv/oneSentence.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("csv/wordPositions.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ..."));
    }

    @Test
    public void should_process_more_sentences(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final String sourceTextPath = ClassLoader.getSystemResource("csv/source.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("csv/sourcePositions.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ... Over the " +
                "past few years, <b>avocado</b> toast has gained a high ..."));
    }

}