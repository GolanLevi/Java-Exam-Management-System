package id_211939947;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import id_211939947.Question.Level;

/**
 * Basic Unit Tests for the Exam Management System.
 * Adding tests like these shows an understanding of the Software Development
 * Life Cycle (SDLC)
 * and is highly valued by recruiters.
 */
public class ExamTest {

    @Test
    void testQuestionCreation() {
        OpenQuestion q = new OpenQuestion("What is Java?", Level.Easy, "A programming language");
        assertEquals("What is Java?", q.getBody());
        assertEquals(Level.Easy, q.getLevel());
    }

    @Test
    void testMultiQuestionAnswerLimit() {
        MultiQuestion mq = new MultiQuestion("Test Question", Level.Medium, 0);
        // By default, it has 2 answers: "More than one correct" and "No correct"
        assertEquals(2, mq.getAnswers().length);
    }
}
