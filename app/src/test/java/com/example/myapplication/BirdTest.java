package com.example.myapplication;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for Bird class.
 * These tests check: Gravity behavior, Flap behavior, and Velocity updates
 */
public class BirdTest {

    /**
     * Test that gravity makes the bird move down
     */
    @Test
    public void testGravityMovesBirdDown() {
        Bird bird = new Bird(100);
        bird.update();
        assertTrue(bird.getYPosition() > 100);
    }

    /**
     * Test that the flap makes the bird move up
     */
    @Test
    public void testFlapMovesBirdUp() {
        Bird bird = new Bird(100);
        bird.flap();
        bird.update();
        assertTrue(bird.getYPosition() < 100);
    }

    /**
     * Test that gravity increases velocity
     */
    @Test
    public void testVelocityIncreasesWithGravity() {
        Bird bird = new Bird(100);
        bird.applyGravity();
        assertTrue(bird.getVelocity() > 0);
    }
}