package com.linguamatics.service;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;

import java.util.List;

public abstract class ContextConfineService {

    public String processContextForOutputBeginning(String contextualSummary){
        if(contextualSummary.startsWith(getReplacementStyle())){
            return contextualSummary.substring(getReplacementStyle().length()).trim();
        }

        return contextualSummary.trim();
    }


    public String confineContextForSentence(Sentence sentence){
        final boolean[] wordsIncluded = new boolean[sentence.getWordCount()];

        final List<PhrasePosition> sentencePhrasePositions = sentence.getPhrasePositions();
        final Long sentenceEnd = sentence.getSentenceEnd();
        final Long sentenceStart = sentence.getSentenceStart();
        final int contextWidth = getContextWidth();

        sentencePhrasePositions.forEach(phrasePosition -> {
            final Long phraseStart= phrasePosition.getStartPosition();
            final Long phraseEnd = phrasePosition.getEndPosition();
            final int contextStart = (int) (Math.max(sentenceStart, phraseStart - contextWidth) - sentenceStart);
            final int contextEnd = (int) (Math.min(sentenceEnd, phraseEnd + contextWidth) - sentenceStart);

            for(int i = contextStart; i <= contextEnd; i++){
                wordsIncluded[i] = true;
            }
        });

        String result = "";
        final String[] words = sentence.getWords();
        for(int i = 0; i < sentence.getWordCount(); i++){
            if(wordsIncluded[i]){
                result += words[i] + " ";
            } else if(i != 0){
                if (!result.endsWith(getReplacementStyle() + " ")){
                    result += getReplacementStyle() + " ";
                }
            }
        }

        return result;
    }

    abstract int getContextWidth();

    abstract String getReplacementStyle();
}
