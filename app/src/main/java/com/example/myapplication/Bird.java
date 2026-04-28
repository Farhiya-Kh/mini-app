package com.example.myapplication;

/**
 * The Bird class handles all physics for the bird in the game.
 * Responsibilities:
 * - Apply gravity (bird falls down)
 * - Handle flap (user tap → bird goes up)
 * - Update position every frame
 * This class ONLY handles logic, not UI.
 */
public class Bird {

    // Current vertical position of the bird
    private float yPosition;

    // Current vertical velocity (speed)
    private float velocity;

    // Constant gravity force applied each frame
    private final float GRAVITY_FORCE = 0.5f;

    // Upward force when user taps
    private final float FLAP_STRENGTH = -10f;

    /**
     * Constructor sets starting position of the bird
     */
    public Bird(float startY) {
        this.yPosition = startY;
        this.velocity = 0;
    }

    /**
     * Updates bird position every frame
     */
    public void update() {
        applyGravity();            // gravity affects velocity
        yPosition += velocity;     // position changes based on velocity
    }

    /**
     * Applies gravity (pulls bird downward)
     */
    public void applyGravity() {
        velocity += GRAVITY_FORCE;
    }

    /**
     * Called when user taps → bird moves upward
     */
    public void flap() {
        velocity = FLAP_STRENGTH;
    }

    /**
     * Returns current vertical position
     */
    public float getYPosition() {
        return yPosition;
    }

    /**
     * Returns current velocity
     */
    public float getVelocity() {
        return velocity;
    }
}