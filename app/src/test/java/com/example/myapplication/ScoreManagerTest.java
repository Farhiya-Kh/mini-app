package com.example.myapplication;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the ScoreManager class.
 */
public class ScoreManagerTest {

    private ScoreManager testScoreManager;

    /**
     * Setup operations before each test runs.
     */
    @Before
    public void setUp() {
        testScoreManager = new ScoreManager();
    }

    /**
     * Tests constructor initializes scores to zero.
     */
    @Test
    public void testConstructor() {
        assertEquals(0, testScoreManager.getCurrentScore());
        assertEquals(0, testScoreManager.getHighScore());
    }

    /**
     * Tests adding a single point.
     */
    @Test
    public void testAddPoint() {
        testScoreManager.addPoint();

        assertEquals(1, testScoreManager.getCurrentScore());
        assertEquals(1, testScoreManager.getHighScore());
    }

    /**
     * Tests adding multiple points.
     */
    @Test
    public void testAddPoints() {
        testScoreManager.addPoints(5);

        assertEquals(5, testScoreManager.getCurrentScore());
        assertEquals(5, testScoreManager.getHighScore());
    }

    /**
     * Tests that negative or zero points do not change the score.
     */
    @Test
    public void testAddPointsInvalid() {
        testScoreManager.addPoints(0);
        testScoreManager.addPoints(-3);

        assertEquals(0, testScoreManager.getCurrentScore());
        assertEquals(0, testScoreManager.getHighScore());
    }

    /**
     * Tests that high score persists after resetting current score.
     */
    @Test
    public void testResetCurrentScore() {
        testScoreManager.addPoints(5);

        testScoreManager.resetCurrentScore();

        assertEquals(0, testScoreManager.getCurrentScore());
        assertEquals(5, testScoreManager.getHighScore());
    }

    /**
     * Tests resetting all scores.
     */
    @Test
    public void testResetAllScores() {
        testScoreManager.addPoints(5);

        testScoreManager.resetAllScores();

        assertEquals(0, testScoreManager.getCurrentScore());
        assertEquals(0, testScoreManager.getHighScore());
    }

    /**
     * Tests score text formatting.
     */
    @Test
    public void testScoreText() {
        testScoreManager.addPoints(3);

        assertEquals("Score: 3", testScoreManager.getCurrentScoreText());
        assertEquals("High Score: 3", testScoreManager.getHighScoreText());
    }

    /**
     * Clean up after each test.
     */
    @After
    public void cleanUpEach() {
        testScoreManager = null;
    }
}