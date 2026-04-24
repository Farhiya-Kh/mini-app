package com.example.myapplication;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Unit tests for Pipe class.
 * These tests verify the current pipe functionality:
 * movement, off-screen detection, and collision behavior.
 */
public class PipeTest {

    @Test
    public void update_movesPipeLeft() {
        Pipe pipe = new Pipe(100, 50, 40, 200, 5);

        pipe.update();

        assertEquals(95f, pipe.getX(), 0.01f);
    }

    @Test
    public void isOffScreen_returnsTrueWhenPipePastLeftEdge() {
        Pipe pipe = new Pipe(-50, 0, 40, 200, 5);

        assertTrue(pipe.isOffScreen());
    }

    @Test
    public void isOffScreen_returnsFalseWhenPipeStillVisible() {
        Pipe pipe = new Pipe(10, 0, 40, 200, 5);

        assertFalse(pipe.isOffScreen());
    }

    @Test
    public void collidesWith_returnsTrueWhenBirdTouchesPipe() {
        Pipe pipe = new Pipe(100, 100, 50, 200, 5);

        boolean result = pipe.collidesWith(110, 120, 30);

        assertTrue(result);
    }

    @Test
    public void collidesWith_returnsFalseWhenBirdDoesNotTouchPipe() {
        Pipe pipe = new Pipe(100, 100, 50, 200, 5);

        boolean result = pipe.collidesWith(10, 10, 20);

        assertFalse(result);
    }
}