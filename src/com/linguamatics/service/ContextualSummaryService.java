package com.linguamatics.service;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;
import com.linguamatics.factory.PhrasePositionFactory;
import com.linguamatics.factory.SentenceFactory;

import java.io.File;
import java.util.LinkedList;
import java.util.List;


public class ContextualSummaryService {

    public ContextualSummaryService() {
        this.sentenceFactory = new SentenceFactory();
        this.phrasePositionFactory = new PhrasePositionFactory();
        this.boldTagHighlighter = new BoldHTMLTagHighlighter();
        this.contextConfineService = new FiveWordsContextConfineService();
    }

    private final SentenceFactory sentenceFactory;

    private final PhrasePositionFactory phrasePositionFactory;

    private final HTMLTagHighlighter boldTagHighlighter;

    private final ContextConfineService contextConfineService;

    public void processContextSummary(String positionsFile, String sourceTextFile) {
        final LinkedList<PhrasePosition> wordPositions = phrasePositionFactory.parsePhrasePositionsFromCSV(positionsFile);
        writeContext(wordPositions, new File(sourceTextFile));
    }

    public void writeContext(List<PhrasePosition> phrasePositions, File sourceTextFile) {
        final List<Sentence> sentences = sentenceFactory.parseFileToSentences(sourceTextFile, phrasePositions);

        String contextualSummary = processContextualSummaryForText(sentences);
        contextualSummary = contextConfineService.processContextForOutputBeginning(contextualSummary);

        System.out.println(contextualSummary);
    }

    protected String processContextualSummaryForText(List<Sentence> sentences) {
        String result = "";
        for (Sentence sentence : sentences) {
            final String processedSentence = processContextualSummaryForSentence(sentence);
            result += processedSentence.isEmpty() ? "" : processedSentence;
        }
        return result;
    }

    private String processContextualSummaryForSentence(Sentence sentence) {
        final Sentence sentenceWithHighlightedWords = boldTagHighlighter.addTagToPhrases(sentence);
        return contextConfineService.confineContextForSentence(sentenceWithHighlightedWords);
    }
}