package com.linguamatics.factory;

import com.linguamatics.PhrasePosition;
import com.linguamatics.Sentence;
import com.linguamatics.util.PhrasePositionUtil;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class SentenceFactory {

    private static final String WHITESPACE_REGEX = "\\s+";
    private static final String SENTENCE_END_REGEX = "\\s+\\.\\s+";

    /**
     * Parse text file into list of {@see Sentence}. Note that only sentences containing {see @ PhrasePosition} are processed.
     * @param input input file to be parsed
     * @param phrasePositions {@see PhrasePosition} to be present in the sentences
     */
    public List<Sentence> parseFileToSentences(File input, List<PhrasePosition> phrasePositions) {
        phrasePositions = PhrasePositionUtil.mergeAndSortPhrasePositions(phrasePositions);
        final Scanner inputScanner = getSentenceScanner(input);

        final List<Sentence> sentences = new ArrayList<>();

        long sentenceStart = 0;
        while (inputScanner.hasNext() && !phrasePositions.isEmpty()) {
            final String sentenceText = inputScanner.next();
            final String[] words = sentenceText.split(WHITESPACE_REGEX);

            final Map<Boolean, List<PhrasePosition>> partitions = matchPhrasePositions(sentenceStart, words.length, phrasePositions);
            final List<PhrasePosition> matchingPositions = partitions.get(true);
            if (matchingPositions.size() > 0) {
                final Sentence sentence = new Sentence(sentenceStart, words, matchingPositions);
                phrasePositions = partitions.get(false);
                sentences.add(sentence);
            }
            sentenceStart = sentenceStart + words.length + 1;
        }

        return sentences;
    }

    /**
     * Partitions the phrase positions into two groups - one that overlaps the given sentence, and one that doesn't
     */
    private Map<Boolean, List<PhrasePosition>> matchPhrasePositions(long currentPosition, int wordsOnLine, List<PhrasePosition> phrasePositions) {
        return phrasePositions.stream()
                .collect(Collectors.partitioningBy(phrasePosition -> containsPhrasePosition(currentPosition, wordsOnLine, phrasePosition)));
    }

    protected boolean containsPhrasePosition(long sentenceStart, int wordsCount, PhrasePosition phrasePosition) {
        final long sentenceEnd = sentenceStart + wordsCount;
        return sentenceStart <= phrasePosition.getStartPosition() && phrasePosition.getStartPosition() <= sentenceEnd ||
                sentenceStart <= phrasePosition.getEndPosition() && phrasePosition.getEndPosition() <= sentenceEnd;
    }

    private Scanner getSentenceScanner(File input) {
        try {
            final Scanner scanner = new Scanner(input);
            scanner.useDelimiter(SENTENCE_END_REGEX);
            return scanner;
        } catch (FileNotFoundException e) {
            throw new RuntimeException("I/O error occurred during opening the source file" , e);
        }
    }

}
