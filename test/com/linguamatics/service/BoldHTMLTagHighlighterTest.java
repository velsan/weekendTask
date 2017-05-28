package com.linguamatics.service;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;
import org.junit.Before;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
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
        final List<PhrasePosition> phrasePosition = singletonList(new PhrasePosition(2L, 2));
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 0L, phrasePosition);
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence);
        assertThat(highlightedSentence.getText(), is("HAS YOUR <b>UNCLE JAIME</b> EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }

    @Test
    public void should_add_bold_tag_to_word(){
        final List<PhrasePosition> phrasePosition = singletonList(new PhrasePosition(3L, 1));
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 0L, phrasePosition);
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence);
        assertThat(highlightedSentence.getText(), is("HAS YOUR UNCLE <b>JAIME</b> EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }

    @Test
    public void should_handle_no_words_to_highlight(){
        final Sentence sentence = new Sentence("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?", 0L, Collections.emptyList());
        final Sentence highlightedSentence = boldTagHighlighter.addTagToPhrases(sentence);
        assertThat(highlightedSentence.getText(), is("HAS YOUR UNCLE JAIME EVER TOLD YOU WHAT HAPPENED TO HIM?"));
    }
}