package com.linguamatics.service;

/**
 * Highlights phrases in a sentence with a <b>bold style</b>
 * {@see HTMLTagHighlighter}
 */
public class BoldHTMLTagHighlighter extends HTMLTagHighlighter {

    private static final String  HTML_BOLD_TAG_NAME = "b";

    @Override
    String getTag() {
        return HTML_BOLD_TAG_NAME;
    }
}
