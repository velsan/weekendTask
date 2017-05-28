package com.linguamatics.factory;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SentenceFactory {

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SENTENCE_END_REGEX = "\\s+\\.\\s+";

    public List<Sentence> parseFileToSentences(File input, List<PhrasePosition> phrasePositions) {
        final Scanner inputScanner = getSentenceScanner(input);
        final List<Sentence> sentences = new ArrayList<>();
        int sentenceStart = 0;
        while (inputScanner.hasNext()) {
            String sentenceText = inputScanner.next();
            String[] words = sentenceText.split(WHITESPACE_REGEX);
            List<PhrasePosition> matchingPhrasePositions = matchingPhrasePositions(sentenceStart, words.length, phrasePositions);
            if (matchingPhrasePositions.size() > 0) {
                final Sentence sentence = new Sentence(sentenceStart, words, matchingPhrasePositions);
                sentences.add(sentence);
            }
            sentenceStart = sentenceStart + words.length + 1;
        }

        return sentences;
    }

    private List<PhrasePosition> matchingPhrasePositions(int currentPosition, int wordsOnLine, List<PhrasePosition> phrasePositions) {
        List<PhrasePosition> matchingPhrasePositions = new ArrayList<>();
        for (PhrasePosition phrasePosition : phrasePositions) {
            if (containsPhrasePosition(currentPosition, wordsOnLine, phrasePosition.getStartPosition(), phrasePosition.getEndPosition())) {
                matchingPhrasePositions.add(phrasePosition);
            }
        }
        return matchingPhrasePositions;
    }

    protected boolean containsPhrasePosition(int sentenceStart, int wordsCount, long phraseContextStart, long phraseContextEnd) {
        int sentenceEnd = sentenceStart + wordsCount;
        return sentenceStart <= phraseContextStart && phraseContextStart <= sentenceEnd ||
                sentenceStart <= phraseContextEnd && phraseContextEnd <= sentenceEnd;
    }

    private Scanner getSentenceScanner(File input) {
        try {
            Scanner scanner = new Scanner(input);
            scanner.useDelimiter(SENTENCE_END_REGEX);
            return scanner;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
