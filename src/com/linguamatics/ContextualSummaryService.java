package com.linguamatics;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import static java.util.stream.Collectors.toList;

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

    //        call the context writing method
    public void processContextSummary(String positionsFile, String sourceTextFile) {
        final LinkedList<PhrasePosition> wordPositions = phrasePositionFactory.parsePhrasePositionsFromCSV(positionsFile);
        writeContext(wordPositions, sourceTextFile);
    }

    //    this parameter will be linked list

    public void writeContext(List<PhrasePosition> phrasePositions, String sourceTextFile) {
        try {
            final List<Sentence> sentences = sentenceFactory.parseFileToSentences(sourceTextFile);
            String contextualSummary = processContextualSummaryForText(phrasePositions, sentences);
            contextualSummary = contextConfineService.processContextForOutputBeginning(contextualSummary);
            System.out.println(contextualSummary);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected String processContextualSummaryForText(List<PhrasePosition> phrasePositions, List<Sentence> sentences) {
        String result = "";
        for (Sentence sentence : sentences) {
            final String processedSentence = processContextualSummaryForSentence(phrasePositions, sentence);
            result += processedSentence.isEmpty() ? "" : processedSentence;
        }
        return result;
    }

    private String processContextualSummaryForSentence(List<PhrasePosition> phrasePositions, Sentence sentence) {
        final List<PhrasePosition> sentencePhrases = getPhrasesForSentence(sentence, phrasePositions);
        if (sentencePhrases.isEmpty()) {
            return "";
        }
        final Sentence sentenceWithHighlightedWords = boldTagHighlighter.addTagToPhrases(sentence, sentencePhrases);
        return contextConfineService.confineContextForSentence(sentenceWithHighlightedWords, sentencePhrases);
    }

    private List<PhrasePosition> getPhrasesForSentence(Sentence sentence, List<PhrasePosition> phrasePositions) {
        final Long sentenceStart = sentence.getSentenceStart();
        final Long sentenceEnd = sentence.getSentenceEnd();
        return phrasePositions.stream()
                .filter(phrasePosition -> phrasePosition.getStartPosition() >= sentenceStart && phrasePosition.getEndPosition() <= sentenceEnd)
                .collect(toList());
    }
}