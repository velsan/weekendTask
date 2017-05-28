package com.linguamatics;

/**
 * Highlights phrases in a sentence with a <b>bold style</b>
 * {@see HTMLTagHighlighter}
 */
public class BoldTagHighlighter extends HTMLTagHighlighter{

    private static final String  HTML_BOLD_TAG_NAME = "b";

    @Override
    String getTag() {
        return HTML_BOLD_TAG_NAME;
    }
}
