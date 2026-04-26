package com.example.myapplication;

/**
 * Pipe.java
 *
 * Represents a single pipe obstacle in the game.
 * This class stores position, size, and movement logic.
 */
public class Pipe {

    // Position
    private float x;
    private float y;

    // Size
    private int width;
    private int height;

    // Movement speed (how fast pipe moves left)
    private float speed;

    /**
     * Constructor
     */
    public Pipe(float x, float y, int width, int height, float speed) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = speed;
    }

    /**
     * Move pipe left across the screen
     */
    public void update() {
        x -= speed;
    }

    /**
     * Check if pipe is off-screen
     */
    public boolean isOffScreen() {
        return x + width < 0;
    }

    // Getters
    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     *  collision check with bird
     */
    public boolean collidesWith(float birdX, float birdY, float birdSize) {
        return birdX < x + width &&
                birdX + birdSize > x &&
                birdY < y + height &&
                birdY + birdSize > y;
    }
}