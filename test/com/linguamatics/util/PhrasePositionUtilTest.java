package com.linguamatics.util;

import com.linguamatics.PhrasePosition;
import com.linguamatics.util.PhrasePositionUtil;
import org.junit.Test;

import java.util.List;

import static java.util.Arrays.asList;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

public class PhrasePositionUtilTest {

    @Test
    public void should_sort() throws Exception {
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(3l, 1), new PhrasePosition(1l, 1));
        final List<PhrasePosition> intervals = PhrasePositionUtil.mergeAndSortPhrasePositions(phrasePositions);

        assertThat(intervals, is(notNullValue()));
        assertThat(2, is(intervals.size()));
        assertThat(1L, is(intervals.get(0).getStartPosition()));
        assertThat(1L, is(intervals.get(0).getEndPosition()));
        assertThat(3L, is(intervals.get(1).getStartPosition()));
        assertThat(3L, is(intervals.get(1).getEndPosition()));
    }

    @Test
    public void should_sort_and_merge_overlapping() {
        final List<PhrasePosition> phrasePositions = asList(new PhrasePosition(4l, 1), new PhrasePosition(1l, 1), new PhrasePosition(1l, 3));
        final List<PhrasePosition> intervals = PhrasePositionUtil.mergeAndSortPhrasePositions(phrasePositions);

        assertThat(intervals, is(notNullValue()));
        assertThat(2, is(intervals.size()));
        assertThat(1L, is(intervals.get(0).getStartPosition()));
        assertThat(3L, is(intervals.get(0).getEndPosition()));
        assertThat(4L, is(intervals.get(1).getStartPosition()));
        assertThat(4L, is(intervals.get(1).getEndPosition()));
    }

    @Test
    public void should_merge_overlapping() throws Exception {
        final PhrasePosition merged = PhrasePositionUtil.merge(new PhrasePosition(1l, 3), new PhrasePosition(2l, 3));
        assertThat(1L, is(merged.getStartPosition()));
        assertThat(4L, is(merged.getEndPosition()));
    }
}
