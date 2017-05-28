package com.linguamatics;

import java.util.List;

/**
 * Represents a sentence with a phrase inside which is to be highlighted
 */
public class Sentence {

    private static final String WHITESPACE_REGEX = "\\s+";

    public Sentence(Long sentenceStart, String text, List<PhrasePosition> phrasePositions) {
        this.text = text;
        this.phrasePositions = phrasePositions;
        this.words = text.split(WHITESPACE_REGEX);
        this.sentenceStart = sentenceStart == null ? -1 : sentenceStart;
    }

    public Sentence(Long sentenceStart, String[] words, List<PhrasePosition> phrasePositions) {
        this.sentenceStart = sentenceStart;
        this.words = words;
        this.phrasePositions = phrasePositions;
    }

    /**
     * Text of the sentence
     */
    private String text;

    /**
     * Position of the first word of this sentence
     */
    private final Long sentenceStart;

    /**
     * {@see PhrasePosition} which can be found inside the text of this sentence
     */
    private final List<PhrasePosition> phrasePositions;

    /**
     * Words of the sentence
     */
    private final String[] words;

    public String[] getWords(){
        return words;
    }

    public Integer getWordCount() {
        return getWords().length;
    }

    public Long getSentenceStart(){
        return sentenceStart;
    }

    public String getText() {
        return text;
    }

    public Long getSentenceEnd() {
        return sentenceStart + getWordCount();
    }

    public List<PhrasePosition> getPhrasePositions() {
        return phrasePositions;
    }
}
