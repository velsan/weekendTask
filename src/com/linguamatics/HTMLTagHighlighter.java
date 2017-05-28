package com.linguamatics;

import java.util.List;

import static java.util.Arrays.stream;
import static java.util.stream.Collectors.joining;

/**
 * To be used for highlighting of phrases in a sentence with HTML
 * HTML tag used for highlighting should be provided by derived classes
 */
public abstract class HTMLTagHighlighter {


    /**
     * @return name of the element of the HTML tag, without surrounding angle brackets
     */
    abstract String getTag();

    /**
     * @param sentence Sentence in which to highlight given phrases
     * @param phrasePositions positions of phrases to be highlighted
     * @return
     */
    public Sentence addTagToPhrases(Sentence sentence, List<PhrasePosition> phrasePositions) {
        final String[] sentenceWords = sentence.getWords();
        final Integer wordCount = sentence.getWordCount();

        phrasePositions.forEach(phrasePosition -> {
            final int phraseStartPosition = (int) (phrasePosition.getStartPosition() - sentence.getSentenceStart());
            int phraseEndPosition = (int) (phrasePosition.getEndPosition() - sentence.getSentenceStart());

            if(phraseEndPosition > wordCount){
                phraseEndPosition = wordCount;
            }

            final String phraseToHighlightStart = sentenceWords[phraseStartPosition];
            sentenceWords[phraseStartPosition] = "<" + getTag() + ">" + phraseToHighlightStart;

            final String phraseToHighlightEnd = sentenceWords[phraseEndPosition];
            sentenceWords[phraseEndPosition] = phraseToHighlightEnd + "</" + getTag() +">";
        });

        return new Sentence(stream(sentenceWords).collect(joining(" ")), sentence.getSentenceEnd());
    }
}