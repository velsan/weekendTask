package com.linguamatics.service;


public class FiveWordsContextConfineService extends ContextConfineService {

    private static final int CONTEXT_WIDTH = 5;
    private static final String REPLACEMENT_STYLE = "...";

    @Override
    int getContextWidth() {
        return CONTEXT_WIDTH;
    }

    @Override
    String getReplacementStyle() {
        return REPLACEMENT_STYLE;
    }
}
