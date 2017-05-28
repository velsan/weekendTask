package com.linguamatics;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class BoldHTMLTagHighlighterTest {

    private HTMLTagHighlighter boldTagHighlighter;

    @Before
    public void init(){
        boldTagHighlighter= new BoldHTMLTagHighlighter();
    }

    @Test
    public void should_add_bold_tag_to_phrase(){
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 10L);
        final PhrasePosition phrasePosition = new PhrasePosition(2L, 2);
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence, Arrays.asList(phrasePosition));
        assertThat(highlightedSentence.getText(), is("HAS YOUR <b>UNCLE JAIME</b> EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }

    @Test
    public void should_add_bold_tag_to_word(){
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 10L);
        final PhrasePosition phrasePosition = new PhrasePosition(3L, 1);
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence, Arrays.asList(phrasePosition));
        assertThat(highlightedSentence.getText(), is("HAS YOUR UNCLE <b>JAIME</b> EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }

    @Test
    public void should_handle_no_words_to_highlight(){
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 10L);
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence, Collections.emptyList());
        assertThat(highlightedSentence.getText(), is("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }
}