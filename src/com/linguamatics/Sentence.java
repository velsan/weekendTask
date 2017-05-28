package com.linguamatics;

/**
 * Created by vojtech on 28/05/17.
 */
public class Sentence {

    private static final String WHITESPACE_REGEX = "\\s+";

    public Sentence(String text, Long sentenceEnd) {
        this.text = text == null ? "" : text;
        this.sentenceEnd = sentenceEnd == null ? -1 : sentenceEnd;
    }

    private final String text;

    private final Long sentenceEnd;

    public String[] getWords(){
        return  text.split(WHITESPACE_REGEX);
    }

    public Integer getWordCount() {
        return getWords().length;
    }

    public Long getSentenceStart(){
        return sentenceEnd - getWordCount() == 0 ? 0 : sentenceEnd - getWordCount() + 1;
    }

    public String getText() {
        return text;
    }

    public Long getSentenceEnd() {
        return sentenceEnd;
    }
}
