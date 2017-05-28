package com.linguamatics.service;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

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
        final String sourceTextPath = ClassLoader.getSystemResource("internalTests/sourceTextFiles/oneSentence.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("internalTests/phrasePositionsFiles/wordPositionsTest.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ..."));
    }

    @Test
    public void should_process_more_sentences(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final String sourceTextPath = ClassLoader.getSystemResource("internalTests/sourceTextFiles/source.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("internalTests/phrasePositionsFiles/sourcePositions.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ... Over the " +
                "past few years, <b>avocado</b> toast has gained a high ..."));
    }

    @Test
    public void should_process_final_test(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final String sourceTextPath = ClassLoader.getSystemResource("csv/test.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("csv/phrasePositions.txt").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(), containsString("<b>Sir Humphrey:</b> Prime Minister, I must protest ... the function " +
                "of government within <b>Her Majesty's</b> United Kingdom of Great Britain ... " +
                "<b>Hacker:</b> You mean you've lost your ..."));
    }

}