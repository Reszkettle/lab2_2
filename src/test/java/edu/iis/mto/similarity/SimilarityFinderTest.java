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

    @Test
    public void shouldReturnZeroWhenSequencesContainCompletelyDifferentNumbers() {
        // given
        SimilarityFinder finder = new SimilarityFinder((elem, sequence) -> SearchResult.builder()
                                                                                       .withFound(false)
                                                                                       .build());
        int[] firstSeq = {1, 2, 3, 4, 5};
        int[] secondSeq = {6, 7, 8, 9, 10};

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(0d, similarity);
    }

}
