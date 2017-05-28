package com.linguamatics.factory;

import com.linguamatics.PhrasePosition;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class PhrasePositionFactory {

    private static final String COMMA = ",";

    public LinkedList<PhrasePosition> parsePhrasePositionsFromCSV(String positionsFile) {
        try {
            final Path positionsPath = Paths.get(positionsFile);
            return Files.lines(positionsPath)
                    .map(s -> {
                        final String[] data = s.split(COMMA);
                        final Long startPosition = Long.valueOf(data[0]);
                        final Integer span = Integer.valueOf(data[1]);
                        return new PhrasePosition(startPosition, span);
                    })
                    .collect(Collectors.toCollection(LinkedList::new));
        } catch (IOException e) {
            throw new RuntimeException("I/O error occurred during opening the input file with phrases positions", e);
        }
    }
}
