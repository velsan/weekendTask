package com.linguamatics.service;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;

import java.util.List;

/**
 * To be used for confining context of sentence, based on given phrases.
 * Context width and style used for confining should be provided by derived classes
 */
public abstract class ContextConfineService {

    /**
     * @return width of Context
     */
    abstract int getContextWidth();

    /**
     * @return string to be used to replace confined words
     */
    abstract String getReplacementStyle();

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

        String sentenceText = "";
        final String[] words = sentence.getWords();
        for(int i = 0; i < sentence.getWordCount(); i++){
            if(wordsIncluded[i]){
                sentenceText += words[i] + " ";
            } else if(i != 0){
                if (!sentenceText.endsWith(getReplacementStyle() + " ")){
                    sentenceText += getReplacementStyle() + " ";
                }
            }
        }

        return  endTheSentence(sentenceText);
    }

    private String endTheSentence(String sentenceText) {
        if(!sentenceText.endsWith(getReplacementStyle() + " ")){
            sentenceText += ". ";
        }
        return sentenceText;
    }
}