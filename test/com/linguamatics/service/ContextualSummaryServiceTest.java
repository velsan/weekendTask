package com.linguamatics.service;

import org.hamcrest.CoreMatchers;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class ContextualSummaryServiceTest {

    private ContextualSummaryService contextualSummaryService;

    private ByteArrayOutputStream out;

    @Before
    public void init(){
        this.contextualSummaryService = new ContextualSummaryService();
        this.out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    @Test
    public void should_process_one_sentence(){
        final String sourceTextPath = ClassLoader.getSystemResource("internalTests/sourceTextFiles/oneSentence.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("internalTests/phrasePositionsFiles/wordPositionsTest.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(out.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ..."));
    }

    @Test
    public void should_process_more_sentences(){
        final String sourceTextPath = ClassLoader.getSystemResource("internalTests/sourceTextFiles/source.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("internalTests/phrasePositionsFiles/sourcePositions.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(out.toString(), containsString("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley" +
                " and ... who had been released from <b>Wormwood Scrubs</b> the day before I think ... Over the " +
                "past few years, <b>avocado</b> toast has gained a high ..."));
    }

    @Test
    public void should_process_more_sentences_2(){
        final String sourceTextPath = ClassLoader.getSystemResource("text1.txt").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("phrasePositions1.txt").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(out.toString(), containsString("<b>Sir Humphrey:</b> Prime Minister, I must protest ... the function " +
                "of government within <b>Her Majesty's</b> United Kingdom of Great Britain ... " +
                "<b>Hacker:</b> You mean you've lost your ..."));
    }

    @Test
    public void should_process_more_sentences_3(){
        final String sourceTextPath = ClassLoader.getSystemResource("text2.txt").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("phrasePositions2.txt").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(out.toString(), containsString("<b>Episode One: Open Government</b> <b>Hacker:</b> Who else is in " +
                "this ... <b>Sir Humphrey:</b> Well briefly, sir, I am ... <b>Hacker:</b> Can they all type? . " +
                "<b>Sir Humphrey:</b> None of us can type . <b>Minister:</b> Pity, we could have opened ... " +
                "<b>Sir Humphrey:</b> Very droll, Minister . <b>Hacker:</b> I suppose they all say ... " +
                "<b>Sir Humphrey:</b> Certainly not, Minister . <b>Episode One: The Grand Design</b> . " +
                "<b>Sir Humphrey:</b> Open government, Prime Minister, Freedom ... " +
                "<b>Episode One: The Grand Design</b> . <b>Sir Humphrey:</b> With Trident we could obliterate ... " +
                "<b>Hacker:</b> I don't want to obliterate ... <b>Sir Humphrey:</b> But it's a deterrent . <b>Hacker:</b> It's" +
                " a bluff . <b>Sir Humphrey:</b> Yes, but they don't know ... <b>Hacker:</b> They probably do . " +
                "<b>Sir Humphrey:</b> Yes, they probably know that ... <b>Hacker:</b> They probably certainly know that ... " +
                "<b>Sir Humphrey:</b> Yes, but even though they ... <b>Jim Hacker:</b> When you give your evidence ... " +
                "<b>Sir Humphrey:</b> Well Minister, if you ask ..."));
    }

    @Test
    public void should_process_empty_file(){
        final String sourceTextPath = ClassLoader.getSystemResource("internalTests/sourceTextFiles/emptyFile.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("internalTests/phrasePositionsFiles/wordPositionsTest.csv").getPath();
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(out.toString().trim().isEmpty(), is(true));
    }

}