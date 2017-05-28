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
    public void should_confine(){
        final Sentence sentence = new Sentence("We went to the town and saw Jim and Bob in the bar drinking beer", 0L);
        final List<PhrasePosition> phrasePositions = Arrays.asList(new PhrasePosition(7L, 1));
        String contextForSentence = contextConfineService.confineContextForSentence(sentence, phrasePositions);
        contextForSentence = contextConfineService.processContextForOutputBeginning(contextForSentence);
        assertThat(contextForSentence, is("to the town and saw Jim and Bob in the bar ..."));
    }
}
