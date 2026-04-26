package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the Pipe class.
 */
public class PipeTest {

    private Pipe testPipe;

    /**
     * Setup operations before each test runs.
     */
    @Before
    public void setUp() {
        testPipe = new Pipe(100, 50, 40, 200, 5);
    }

    /**
     * Tests constructor.
     */
    @Test
    public void testConstructor() {
        assertEquals(100, testPipe.getX(), 0.01);
        assertEquals(50, testPipe.getY(), 0.01);
        assertEquals(40, testPipe.getWidth());
        assertEquals(200, testPipe.getHeight());
    }

    /**
     * Tests that update moves the pipe left.
     */
    @Test
    public void testUpdate() {
        testPipe.update();

        assertEquals(95, testPipe.getX(), 0.01);
    }

    /**
     * Tests that a pipe past the left edge is off screen.
     */
    @Test
    public void testIsOffScreenTrue() {
        Pipe offScreenPipe = new Pipe(-50, 0, 40, 200, 5);

        assertTrue(offScreenPipe.isOffScreen());
    }

    /**
     * Tests that a visible pipe is not off screen.
     */
    @Test
    public void testIsOffScreenFalse() {
        Pipe visiblePipe = new Pipe(10, 0, 40, 200, 5);

        assertFalse(visiblePipe.isOffScreen());
    }

    /**
     * Tests that collision returns true when the bird overlaps the pipe.
     */
    @Test
    public void testCollidesWithTrue() {
        boolean result = testPipe.collidesWith(110, 60, 30);

        assertTrue(result);
    }

    /**
     * Tests that collision returns false when the bird does not overlap the pipe.
     */
    @Test
    public void testCollidesWithFalse() {
        boolean result = testPipe.collidesWith(10, 10, 20);

        assertFalse(result);
    }

    /**
     * Clean up our variables after each test.
     */
    @After
    public void cleanUpEach() {
        testPipe = null;
    }
}