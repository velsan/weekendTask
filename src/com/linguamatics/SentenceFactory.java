package com.linguamatics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public class SentenceFactory {

    private static final String WHITESPACE_REGEX = "\\s+";

    public List<Sentence> parseFileToSentences(String filePath) throws IOException {
        final String text = parseFile(filePath);
        return parseTextToSentences(text);
    }

    public List<Sentence> parseTextToSentences(String text) {
        final String[] sentences = text.split(WHITESPACE_REGEX + "\\." + WHITESPACE_REGEX);
        final List<Sentence> sentencesWithEndPositions = new ArrayList<>();
        long firstWordNumber = 0;
        for (String sentence : sentences) {
            sentencesWithEndPositions.add(new Sentence(sentence, firstWordNumber));
            firstWordNumber += sentence.split(WHITESPACE_REGEX).length + 1;
        }
        return sentencesWithEndPositions;
    }

    //    TODO try to load only sentences that contain selected words
    private String parseFile(String sourceTextFile) throws IOException {
        final Path sourceTextPath = Paths.get(sourceTextFile);
//        todo change to all lines
        return Files.lines(sourceTextPath)
                .collect(joining(" "));
    }
}
