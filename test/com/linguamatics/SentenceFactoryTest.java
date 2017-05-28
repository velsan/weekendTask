package com.linguamatics;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vojtech on 28/05/17.
 */
public class SentenceFactoryTest {

    @Test
    public void should_find_if_line_contains_phrase() throws Exception {
        final SentenceFactory hightlightSearch = new SentenceFactory();
        Assert.assertTrue(hightlightSearch.containsPhrasePosition(5, 4, 6, 100));
        Assert.assertFalse(hightlightSearch.containsPhrasePosition(34, 5, 44, 1));
    }

    @Test
    public void should_find_sentences_near_matches() throws Exception {
        final SentenceFactory hightlightSearch = new SentenceFactory();
        final File input = new File(ClassLoader.getSystemResource("csv/simple.csv").getPath());
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(6l, 1));
        phrasePositions.add(new PhrasePosition(8l, 1));

        final List<Sentence> sentences = hightlightSearch.parseFileToSentences(input, phrasePositions);

        Assert.assertNotNull(sentences);
        Assert.assertEquals(1, sentences.size());
    }

    @Test
    public void should_find_multiple_sentences_near_matches() throws Exception {
        final SentenceFactory hightlightSearch = new SentenceFactory();
        final File input = new File(ClassLoader.getSystemResource("csv/source.csv").getPath());
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(6l, 1));
        phrasePositions.add(new PhrasePosition(8l, 1));
        phrasePositions.add(new PhrasePosition(23l, 2));
        phrasePositions.add(new PhrasePosition(38l, 1));

        final List<Sentence> sentences = hightlightSearch.parseFileToSentences(input, phrasePositions);

        Assert.assertNotNull(sentences);
        Assert.assertEquals(2, sentences.size());
        Assert.assertEquals(3, sentences.get(0).getPhrasePositions().size());
        Assert.assertEquals(1, sentences.get(1).getPhrasePositions().size());
    }

    @Test
    public void should_skip_irrelevant_and_find_sentences_near_matches() throws Exception {
        final SentenceFactory hightlightSearch = new SentenceFactory();
        final File input = new File(ClassLoader.getSystemResource("csv/source-longer.csv").getPath());
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(6l, 1));
        phrasePositions.add(new PhrasePosition(8l, 1));
        phrasePositions.add(new PhrasePosition(23l, 2));
        phrasePositions.add(new PhrasePosition(44l, 1));

        final List<Sentence> sentences = hightlightSearch.parseFileToSentences(input, phrasePositions);

        Assert.assertNotNull(sentences);
        Assert.assertEquals(2, sentences.size());
        Assert.assertEquals(3, sentences.get(0).getPhrasePositions().size());
        Assert.assertEquals(1, sentences.get(1).getPhrasePositions().size());
    }

    @Test
    public void should_have_correct_sentence_starts(){
        final SentenceFactory hightlightSearch = new SentenceFactory();
        final File input = new File(ClassLoader.getSystemResource("csv/twoWords.csv").getPath());
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(0L, 1));
        phrasePositions.add(new PhrasePosition(2L, 1));
        final List<Sentence> sentences = hightlightSearch.parseFileToSentences(input, phrasePositions);

        Assert.assertNotNull(sentences);
        Assert.assertEquals(2, sentences.size());
        Assert.assertEquals(1, sentences.get(0).getPhrasePositions().size());
        Assert.assertEquals(0L, sentences.get(0).getSentenceStart().longValue());
        Assert.assertEquals(2L, sentences.get(1).getSentenceStart().longValue());
    }
}
