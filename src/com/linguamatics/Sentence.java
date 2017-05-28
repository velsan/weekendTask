package com.linguamatics;

public class Sentence {

    private static final String WHITESPACE_REGEX = "\\s+";

    public Sentence(String text, Long sentenceStart) {
        this.text = text == null ? "" : text;
        this.sentenceStart = sentenceStart == null ? -1 : sentenceStart;
    }

    private final String text;

    private final Long sentenceStart;

    public String[] getWords(){
        return  text.split(WHITESPACE_REGEX);
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
}
