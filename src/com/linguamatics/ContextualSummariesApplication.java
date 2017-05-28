package com.linguamatics;

import com.linguamatics.service.ContextualSummaryService;

public class ContextualSummariesApplication {

    //    will take filepath of the source text (mandatory)
//    will take filepath of file with the words positions (mandatory)
    public static void main(String[] args) {
        final String sourceTextFile = args[0];
        final String positionsFile = args[1];

        final ContextualSummaryService contextualSummaryService = new ContextualSummaryService();
        contextualSummaryService.processContextSummary(positionsFile, sourceTextFile);
    }


}
