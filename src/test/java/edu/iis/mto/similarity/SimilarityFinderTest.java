package edu.iis.mto.similarity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import edu.iis.mto.searcher.SearchResult;
import edu.iis.mto.searcher.SequenceSearcher;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SimilarityFinderTest {

    @Test
    public void shouldReturnOneWhenBothSequencesAreEmpty() {
        // given
        SimilarityFinder finder = new SimilarityFinder((elem, sequence) -> null);
        int[] firstSeq = {};
        int[] secondSeq = {};

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(1.0d, similarity);
    }
}
