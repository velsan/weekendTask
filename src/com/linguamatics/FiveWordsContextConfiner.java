package com.linguamatics;

/**
 * Created by vojtech on 28/05/17.
 */
public class FiveWordsContextConfiner extends ContextConfineService {

    private static final int CONTEXT_WIDTH = 5;

    @Override
    int getContextWidth() {
        return CONTEXT_WIDTH;
    }
}
