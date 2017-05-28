package com.linguamatics;

import java.util.List;

public class Sentence {

    private static final String WHITESPACE_REGEX = "\\s+";

    public Sentence(String text, Long sentenceStart, List<PhrasePosition> phrasePositions) {
        this.text = text;
        this.phrasePositions = phrasePositions;
        this.words = text.split(WHITESPACE_REGEX);
        this.sentenceStart = sentenceStart == null ? -1 : sentenceStart;
    }

    public Sentence(long sentenceStart, String[] words, List<PhrasePosition> matchingPhrasePositions) {
        this.sentenceStart = sentenceStart;
        this.words = words;
        this.phrasePositions = matchingPhrasePositions;
    }

//    todo make final
    private String text;

    private final Long sentenceStart;

    private final List<PhrasePosition> phrasePositions;

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
