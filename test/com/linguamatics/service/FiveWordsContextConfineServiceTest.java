package com.linguamatics.service;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Collections.singletonList;
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
        final List<PhrasePosition> phrasePositions = singletonList(new PhrasePosition(7L, 1));
        final Sentence sentence = new Sentence(0L, "We went to the town and saw Jim and Bob in the bar drinking beer", phrasePositions);
        String contextForSentence = contextConfineService.confineContextForSentence(sentence);
        contextForSentence = contextConfineService.processContextForOutputBeginning(contextForSentence);
        assertThat(contextForSentence, is("to the town and saw Jim and Bob in the bar ..."));
    }

    @Test
    public void should_confine_two_sentences(){
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(7L, 1),new PhrasePosition(10L, 1));
        final Sentence sentence = new Sentence(0L, "We went to the town and saw Jim . And Bob in the bar drinking beer and juice", phrasePositions);

        String contextForSentence = contextConfineService.confineContextForSentence(sentence);
        contextForSentence = contextConfineService.processContextForOutputBeginning(contextForSentence);
        assertThat(contextForSentence, is("to the town and saw Jim . And Bob in the bar drinking beer ..."));
    }
}
