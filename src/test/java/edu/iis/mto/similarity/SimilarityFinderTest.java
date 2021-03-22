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

    @Test
    public void shouldReturnOneWhenSequencesContainTheSameNumbersAndAreOfTheSameLength() {
        // given
        SimilarityFinder finder = new SimilarityFinder((elem, sequence) -> SearchResult.builder()
                                                                                       .withFound(true)
                                                                                       .build());
        int[] firstSeq = {1, 2, 3};
        int[] secondSeq = {1, 2, 3};

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(1.0d, similarity);
    }

    @Test
    public void shouldReturnZeroWhenFirstSequenceIsEmpty() {
        // given
        SimilarityFinder finder = new SimilarityFinder((elem, sequence) -> null);
        int[] firstSeq = {};
        int[] secondSeq = {1, 2, 3};

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(0d, similarity);
    }

    @Test
    public void shouldReturnZeroWhenSecondSequenceIsEmpty() {
        // given
        SimilarityFinder finder = new SimilarityFinder((elem, sequence) -> SearchResult.builder()
                                                                                       .withFound(false)
                                                                                       .build());
        int[] firstSeq = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] secondSeq = {};

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(0d, similarity);
    }

    @Test
    public void shouldReturnQuarterWhenSequencesContainTwoTheSameElementsAndTotalLengthIsTen() {
        // given
        SearchResult foundResult = SearchResult.builder().withFound(true).build();
        SearchResult notFoundResult = SearchResult.builder().withFound(false).build();
        int[] firstSeq = {1, 2, 3, 4};
        int[] secondSeq = {4, 1, 11, 19, 23, 35};

        SimilarityFinder finder = new SimilarityFinder(((elem, sequence) -> {
            if(elem == 1)
                return foundResult;
            else if (elem == 2)
                return notFoundResult;
            else if (elem == 3)
                return notFoundResult;
            else if (elem == 4)
                return foundResult;
            return null;
        }));

        // when
        double similarity = finder.calculateJackardSimilarity(firstSeq, secondSeq);

        // then
        assertEquals(0.25d, similarity);
    }

}
