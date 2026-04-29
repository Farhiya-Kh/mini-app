package com.example.myapplication;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * Manages all pipe obstacles in the game.
 *
 * <p>The PipeManager is responsible for creating pipes, updating their
 * positions, removing pipes that move off screen, checking collisions, and
 * tracking when the bird passes pipes for scoring.</p>
 */
public class PipeManager {

    private final List<Pipe> pipes;
    private final Random random;

    private final int screenWidth;
    private final int screenHeight;
    private final int pipeWidth;
    private final int gapHeight;
    private final float pipeSpeed;
    private final int spawnDistance;

    private int pipesPassed;

    /**
     * Creates a PipeManager with the values needed to spawn and move pipes.
     *
     * @param screenWidth width of the game screen
     * @param screenHeight height of the game screen
     * @param pipeWidth width of each pipe
     * @param gapHeight vertical space between the top and bottom pipe
     * @param pipeSpeed speed that pipes move left
     * @param spawnDistance horizontal distance between pipe pairs
     */
    public PipeManager(
            int screenWidth,
            int screenHeight,
            int pipeWidth,
            int gapHeight,
            float pipeSpeed,
            int spawnDistance
    ) {
        this.pipes = new ArrayList<>();
        this.random = new Random();

        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
        this.pipeWidth = pipeWidth;
        this.gapHeight = gapHeight;
        this.pipeSpeed = pipeSpeed;
        this.spawnDistance = spawnDistance;

        this.pipesPassed = 0;
    }

    /**
     * Updates all pipes for one frame.
     *
     * <p>This moves every pipe left, removes pipes that are off screen,
     * and spawns new pipes when needed.</p>
     */
    public void update() {
        for (Pipe pipe : pipes) {
            pipe.update();
        }

        removeOffScreenPipes();

        if (shouldSpawnPipePair()) {
            spawnPipePair();
        }
    }

    /**
     * Creates one top pipe and one bottom pipe with a gap between them.
     */
    public void spawnPipePair() {
        int minGapY = 100;
        int maxGapY = screenHeight - gapHeight - 100;

        if (maxGapY <= minGapY) {
            maxGapY = minGapY + 1;
        }

        int gapY = minGapY + random.nextInt(maxGapY - minGapY);

        Pipe topPipe = new Pipe(
                screenWidth,
                0,
                pipeWidth,
                gapY,
                pipeSpeed
        );

        Pipe bottomPipe = new Pipe(
                screenWidth,
                gapY + gapHeight,
                pipeWidth,
                screenHeight - (gapY + gapHeight),
                pipeSpeed
        );

        pipes.add(topPipe);
        pipes.add(bottomPipe);
    }

    /**
     * Checks whether the bird has hit any pipe.
     *
     * @param birdX x-position of the bird
     * @param birdY y-position of the bird
     * @param birdSize size of the bird
     * @return true if the bird collides with any pipe, false otherwise
     */
    public boolean checkCollision(float birdX, float birdY, float birdSize) {
        for (Pipe pipe : pipes) {
            if (pipe.collidesWith(birdX, birdY, birdSize)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Updates the number of pipe pairs the bird has passed.
     *
     * <p>This method assumes pipes are stored as pairs. Since each pair has
     * a top pipe and bottom pipe, scoring is checked using every other pipe.</p>
     *
     * @param birdX x-position of the bird
     * @return number of newly passed pipe pairs
     */
    public int updateScore(float birdX) {
        int newlyPassed = 0;

        for (int i = 0; i < pipes.size(); i += 2) {
            Pipe pipe = pipes.get(i);

            if (pipe.getX() + pipe.getWidth() < birdX && i / 2 >= pipesPassed) {
                pipesPassed++;
                newlyPassed++;
            }
        }

        return newlyPassed;
    }

    /**
     * Removes all pipes that have moved fully off the left side of the screen.
     */
    private void removeOffScreenPipes() {
        Iterator<Pipe> iterator = pipes.iterator();

        while (iterator.hasNext()) {
            Pipe pipe = iterator.next();

            if (pipe.isOffScreen()) {
                iterator.remove();
            }
        }
    }

    /**
     * Checks whether a new pipe pair should be created.
     *
     * @return true if the game needs another pipe pair, false otherwise
     */
    private boolean shouldSpawnPipePair() {
        if (pipes.isEmpty()) {
            return true;
        }

        Pipe lastPipe = pipes.get(pipes.size() - 1);
        return screenWidth - lastPipe.getX() >= spawnDistance;
    }

    /**
     * Clears all pipes and resets score tracking.
     */
    public void reset() {
        pipes.clear();
        pipesPassed = 0;
    }

    /**
     * Gets the current pipes.
     *
     * @return list of pipes currently active in the game
     */
    public List<Pipe> getPipes() {
        return pipes;
    }

    /**
     * Gets the number of pipe pairs passed.
     *
     * @return number of pipe pairs passed by the bird
     */
    public int getPipesPassed() {
        return pipesPassed;
    }
}
