package com.linguamatics;

import com.linguamatics.service.ContextualSummaryService;

public class ContextualSummariesApplication {

    //    will take filepath of the source text (mandatory)
//    will take filepath of file with the words positions (mandatory)
    public static void main(String[] args) {
        validateArguments(args);

        final String sourceTextFile = args[0];
        final String positionsFile = args[1];

        final ContextualSummaryService contextualSummaryService = new ContextualSummaryService();
        contextualSummaryService.processContextSummary(positionsFile, sourceTextFile);
    }

    private static void validateArguments(String[] args) {
        if(args.length != 2){
            throw new RuntimeException("Invalid number of arguments. Please provide path to Source Text file and path to file with list of words positions.");
        } else if (args[0].isEmpty()){
            throw new RuntimeException("Please provide valid  path to Source Text file");
        } else if(args[1].isEmpty()){
            throw new RuntimeException("Please provide valid  path to file with list of words positions");
        }
    }


}
