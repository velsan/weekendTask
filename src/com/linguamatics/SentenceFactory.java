package com.linguamatics;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

/**
 * Created by vojtech on 28/05/17.
 */
public class SentenceFactory {

    private static final String WHITESPACE_REGEX = "\\s+";

    public List<Sentence> parseFileToSentences(String filePath) throws IOException {
        final String text = parseFile(filePath);
        return parseTextToSentences(text);
    }

    //    The list contains item for each sentence. Key is position of last word in the sentence, value is the sentence
    public List<Sentence> parseTextToSentences(String text) {
        final String[] sentences = text.split("\\s+\\.\\s+");
        final List<Sentence> sentencesWithEndPositions = new ArrayList<>();
        long lastWordNumber = 0;
        for (String sentence : sentences) {
            lastWordNumber += sentence.split(WHITESPACE_REGEX).length;
            sentencesWithEndPositions.add(new Sentence(sentence, lastWordNumber));
            lastWordNumber++;
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
