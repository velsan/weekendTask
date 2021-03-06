package com.linguamatics.util;

import com.linguamatics.PhrasePosition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhrasePositionUtil {

    /**
     * Sorts {@see PhrasePosition} object by their position in ascending order.
     * Merges overlapping  {@see PhrasePosition}
     * @param phrasePositions {@see PhrasePosition} to sort and merge
     * @return
     */
    public static List<PhrasePosition> mergeAndSortPhrasePositions(List<PhrasePosition> phrasePositions) {
        final List<PhrasePosition> sortedPositions = phrasePositions.stream()
                .sorted((a, b) -> a.getStartPosition() == b.getStartPosition() ?
                        a.getEndPosition() <= b.getEndPosition() ? -1 : 1 :
                        a.getStartPosition() <= b.getStartPosition() ? -1 : 1).collect(Collectors.toList());
        return mergePositions(sortedPositions);
    }

    private static List<PhrasePosition> mergePositions(List<PhrasePosition> sortedPositions) {
        final List<PhrasePosition> merged = new ArrayList<>();
        int nextPosition = 0;
        while (nextPosition < sortedPositions.size()) {
            nextPosition = mergeOverlapping(sortedPositions, merged, nextPosition);
        }
        return merged;
    }

    private static int mergeOverlapping(List<PhrasePosition> sortedPositions, List<PhrasePosition> merged, int position) {
        PhrasePosition currentPhrasePosition = sortedPositions.get(position);
        int j = position + 1;
        while (j < sortedPositions.size() && currentPhrasePosition.getEndPosition() >= sortedPositions.get(j).getStartPosition()) {
            currentPhrasePosition = merge(currentPhrasePosition, sortedPositions.get(j));
            j++;
        }
        merged.add(currentPhrasePosition);
        return j;
    }

    protected static PhrasePosition merge(PhrasePosition first, PhrasePosition second) {
        return new PhrasePosition(first.getStartPosition(), (int) (second.getEndPosition() - first.getStartPosition() + 1));
    }
}