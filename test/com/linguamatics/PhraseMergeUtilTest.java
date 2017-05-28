package com.linguamatics;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class PhraseMergeUtilTest {

    @Test
    public void should_sort() throws Exception {
        PhraseMergeUtil util = new PhraseMergeUtil();
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(3l, 1));
        phrasePositions.add(new PhrasePosition(1l, 1));
        List<PhrasePosition> intervals = util.mergeAndSortPhrasePositions(phrasePositions);

        Assert.assertNotNull(intervals);
        Assert.assertEquals(2, intervals.size());
        Assert.assertEquals(1l, intervals.get(0).getStartPosition().longValue());
        Assert.assertEquals(1l, intervals.get(0).getEndPosition().longValue());
        Assert.assertEquals(3l, intervals.get(1).getStartPosition().longValue());
        Assert.assertEquals(3l, intervals.get(1).getEndPosition().longValue());
    }

    @Test
    public void should_merge_overlapping() {
        PhraseMergeUtil util = new PhraseMergeUtil();
        List<PhrasePosition> phrasePositions = new ArrayList<>();
        phrasePositions.add(new PhrasePosition(1l, 1));
        phrasePositions.add(new PhrasePosition(1l, 3));
        phrasePositions.add(new PhrasePosition(4l, 1));

        List<PhrasePosition> intervals = util.mergeAndSortPhrasePositions(phrasePositions);

        Assert.assertNotNull(intervals);
        Assert.assertEquals(2, intervals.size());
        Assert.assertEquals(1, intervals.get(0).getStartPosition().longValue());
        Assert.assertEquals(3, intervals.get(0).getEndPosition().longValue());
        Assert.assertEquals(4, intervals.get(1).getStartPosition().longValue());
        Assert.assertEquals(4, intervals.get(1).getEndPosition().longValue());
    }

    @Test
    public void should_merge_phrase_positions() throws Exception {
        PhraseMergeUtil util = new PhraseMergeUtil();

        final PhrasePosition merged = util.merge(new PhrasePosition(1l, 3), new PhrasePosition(2l, 3));
        Assert.assertEquals(1l, merged.getStartPosition().longValue());
        Assert.assertEquals(4l, merged.getEndPosition().longValue());
    }
}
