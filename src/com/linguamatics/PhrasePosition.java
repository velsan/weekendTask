package com.linguamatics;

/**
 * Represents a phrase to be highlighted
 */
public class PhrasePosition {

    public PhrasePosition(Long startPosition, Integer span) {
        this.startPosition = startPosition;
        this.span = span;
    }

    /**
     * word number counting from 0
     */
    private final Long startPosition;

    /**
     * number of words in phrase
     */
    private final Integer span;

    public Long getEndPosition(){
        return startPosition + span -1;
    }

    public Long getStartPosition() {
        return startPosition;
    }

    public Integer getSpan() {
        return span;
    }
}
