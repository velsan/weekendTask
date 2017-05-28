package com.linguamatics;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FiveWordsContextConfineServiceTest {

    private ContextConfineService contextConfineService;

    @Before
    public void init(){
        contextConfineService = new FiveWordsContextConfineService();
    }

    @Test
    public void should_confine_one_sentence(){
        final List<PhrasePosition> phrasePositions = Arrays.asList(new PhrasePosition(7L, 1));
        final Sentence sentence = new Sentence("We went to the town and saw Jim and Bob in the bar drinking beer", 0L, phrasePositions);
        String contextForSentence = contextConfineService.confineContextForSentence(sentence);
        contextForSentence = contextConfineService.processContextForOutputBeginning(contextForSentence);
        assertThat(contextForSentence, is("to the town and saw Jim and Bob in the bar ..."));
    }

    @Test
    public void should_confine_two_sentences(){
        final List<PhrasePosition> phrasePositions = Arrays.asList(new PhrasePosition(7L, 1),new PhrasePosition(10L, 1));
        final Sentence sentence = new Sentence("We went to the town and saw Jim . And Bob in the bar drinking beer and juice", 0L,phrasePositions);

        String contextForSentence = contextConfineService.confineContextForSentence(sentence);
        contextForSentence = contextConfineService.processContextForOutputBeginning(contextForSentence);
        assertThat(contextForSentence, is("to the town and saw Jim . And Bob in the bar drinking beer ..."));
    }
}
