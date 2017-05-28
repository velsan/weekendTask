package com.linguamatics;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.LinkedList;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

public class ContextualSummaryServiceTest {

    private ContextualSummaryService contextualSummaryService;

    @Before
    public void init(){
        this.contextualSummaryService = new ContextualSummaryService();
    }

    @Test
    public void should_write_context(){
        final ByteArrayOutputStream myOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(myOut));
        final String sourceTextPath = ClassLoader.getSystemResource("csv/sourceText.csv").getPath();
        final String wordPositionsPath = ClassLoader.getSystemResource("csv/wordPositions.csv").getPath();
        final LinkedList<PhrasePosition> positions = new LinkedList<>();
        positions.add(new PhrasePosition(6L, 1));
        positions.add(new PhrasePosition(8L, 1));
        positions.add(new PhrasePosition(23L, 2));
        contextualSummaryService.processContextSummary(wordPositionsPath, sourceTextPath);
        assertThat(myOut.toString(),startsWith("went to town and saw <b>Jim</b> and <b>Bob</b> and Uncle Tom Cobbley\n" +
                "and all ... who had been released from <b>Wormwood Scrubs</b> the day\n" +
                "before I think ..."));
    }

}