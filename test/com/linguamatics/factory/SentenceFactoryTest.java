package com.linguamatics.factory;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class SentenceFactoryTest {

    private SentenceFactory sentenceFactory;

    @Before
    public void init(){
       sentenceFactory = new SentenceFactory() ;
    }

    @Test
    public void should_find_if_line_contains_phrase() throws Exception {
        assertThat(sentenceFactory.containsPhrasePosition(5, 4, new PhrasePosition(6l, 100)), is(true));
        assertThat(sentenceFactory.containsPhrasePosition(34, 5, new PhrasePosition(44l, 1)), is(false));
    }

    @Test
    public void should_find_sentences_near_matches() throws Exception {
        final File input = new File(ClassLoader.getSystemResource("internalTests/sourceTextFiles/simple.csv").getPath());
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(6L, 1), new PhrasePosition(8L, 1));
        final List<Sentence> sentences = sentenceFactory.parseFileToSentences(input, phrasePositions);

        assertThat(sentences, is(notNullValue()));
        assertThat(sentences.size(), is(1));
    }

    @Test
    public void should_find_multiple_sentences_near_matches() throws Exception {
        final File input = new File(ClassLoader.getSystemResource("internalTests/sourceTextFiles/source.csv").getPath());
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(6L, 1), new PhrasePosition(8L, 1), new PhrasePosition(23L, 2), new PhrasePosition(38L, 1));
        final List<Sentence> sentences = sentenceFactory.parseFileToSentences(input, phrasePositions);

        assertThat(sentences, is(notNullValue()));
        assertThat(sentences.size(), is(2));
        assertThat(sentences.get(0).getPhrasePositions().size(), is(3));
        assertThat(sentences.get(1).getPhrasePositions().size(), is(1));
    }

    @Test
    public void should_skip_irrelevant_and_find_sentences_near_matches() throws Exception {
        final File input = new File(ClassLoader.getSystemResource("internalTests/sourceTextFiles/source-longer.csv").getPath());
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(6L, 1), new PhrasePosition(8L, 1), new PhrasePosition(23L, 2), new PhrasePosition(44L, 1));
        final List<Sentence> sentences = sentenceFactory.parseFileToSentences(input, phrasePositions);

        assertThat(sentences, is(notNullValue()));
        assertThat(sentences.size(), is(2));
        assertThat(sentences.get(0).getPhrasePositions().size(), is(3));
        assertThat(sentences.get(1).getPhrasePositions().size(), is(1));
    }

    @Test
    public void should_have_correct_sentence_starts(){
        final File input = new File(ClassLoader.getSystemResource("internalTests/sourceTextFiles/twoWords.csv").getPath());
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(0L, 1), new PhrasePosition(2L, 1));
        final List<Sentence> sentences = sentenceFactory.parseFileToSentences(input, phrasePositions);

        assertThat(sentences, is(notNullValue()));
        assertThat(sentences.size(), is(2));
        assertThat(sentences.get(0).getPhrasePositions().size(), is(1));
        assertThat(sentences.get(0).getSentenceStart(), is(0L));
        assertThat(sentences.get(0).getSentenceEnd(), is(0L));
        assertThat(sentences.get(1).getSentenceStart(), is(2L));
        assertThat(sentences.get(1).getSentenceEnd(), is(2L));
    }
}
