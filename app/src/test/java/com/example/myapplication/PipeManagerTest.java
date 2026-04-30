package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the PipeManager class.
 */
public class PipeManagerTest {

    private PipeManager testPipeManager;

    /**
     * Setup operations before each test runs.
     */
    @Before
    public void setUp() {
        testPipeManager = new PipeManager(
                500,
                800,
                50,
                200,
                5,
                250
        );
    }

    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals(0, testPipeManager.getPipes().size());
        assertEquals(0, testPipeManager.getPipesPassed());
    }

    /**
     * Tests that spawnPipePair creates a top pipe and a bottom pipe.
     */
    @Test
    public void testSpawnPipePair() {
        testPipeManager.spawnPipePair();

        assertEquals(2, testPipeManager.getPipes().size());
    }

    /**
     * Tests that update spawns pipes when there are no pipes.
     */
    @Test
    public void testUpdateSpawnsPipesWhenEmpty() {
        testPipeManager.update();

        assertEquals(2, testPipeManager.getPipes().size());
    }

    /**
     * Tests that checkCollision returns true when the bird overlaps a pipe.
     */
    @Test
    public void testCheckCollisionTrue() {
        testPipeManager.spawnPipePair();

        Pipe firstPipe = testPipeManager.getPipes().get(0);

        boolean result = testPipeManager.checkCollision(
                firstPipe.getX() + 5,
                firstPipe.getY() + 5,
                20
        );

        assertTrue(result);
    }

    /**
     * Tests that checkCollision returns false when the bird does not overlap any pipe.
     */
    @Test
    public void testCheckCollisionFalse() {
        testPipeManager.spawnPipePair();

        boolean result = testPipeManager.checkCollision(
                10,
                400,
                20
        );

        assertFalse(result);
    }

    /**
     * Tests that updateScore counts one pipe pair after the bird passes it.
     */
    @Test
    public void testUpdateScore() {
        testPipeManager.spawnPipePair();

        int newlyPassed = testPipeManager.updateScore(600);

        assertEquals(1, newlyPassed);
        assertEquals(1, testPipeManager.getPipesPassed());
    }

    /**
     * Tests that updateScore does not count the same pipe pair twice.
     */
    @Test
    public void testUpdateScoreOnlyCountsOnce() {
        testPipeManager.spawnPipePair();

        int firstResult = testPipeManager.updateScore(600);
        int secondResult = testPipeManager.updateScore(600);

        assertEquals(1, firstResult);
        assertEquals(0, secondResult);
        assertEquals(1, testPipeManager.getPipesPassed());
    }

    /**
     * Tests that reset clears pipes and resets score tracking.
     */
    @Test
    public void testReset() {
        testPipeManager.spawnPipePair();
        testPipeManager.updateScore(600);

        testPipeManager.reset();

        assertEquals(0, testPipeManager.getPipes().size());
        assertEquals(0, testPipeManager.getPipesPassed());
    }

    /**
     * Clean up our variables after each test.
     */
    @After
    public void cleanUpEach() {
        testPipeManager = null;
    }
}