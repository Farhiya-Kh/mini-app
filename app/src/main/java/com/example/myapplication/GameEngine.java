package com.example.myapplication;

/**
 * Controls the main game state and score.
 * Also coordinates the Bird physics.
 */
public class GameEngine {

    public enum GameState {
        START,
        RUNNING,
        GAME_OVER
    }

    private GameState currentState;
    private int score;
    private Runnable onUpdate;

    // Add Bird
    private Bird bird;

    public GameEngine(Runnable onUpdate) {
        this.onUpdate = onUpdate;
        reset();
    }

    public void reset() {
        currentState = GameState.START;
        score = 0;

        // Initialize bird at starting position
        bird = new Bird(500f);
    }

    public void startGame() {
        if (currentState == GameState.START) {
            currentState = GameState.RUNNING;
        }
    }

    public void onTap() {
        switch (currentState) {
            case START:
                startGame();
                break;
            case GAME_OVER:
                reset();
                break;
            case RUNNING:
                flap(); // IMPORTANT: allow tap to flap
                break;
        }
    }

    public void update() {
        if (currentState != GameState.RUNNING) {
            return;
        }

        // Update bird physics
        bird.update();

        if (onUpdate != null) {
            onUpdate.run();
        }
    }

    public void flap() {
        if (currentState == GameState.RUNNING) {
            bird.flap();
        }
    }

    public void triggerGameOver() {
        currentState = GameState.GAME_OVER;
    }

    public void addPoint() {
        score++;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public int getScore() {
        return score;
    }

    // Expose bird data for tests
    public float getBirdY() {
        return bird.getYPosition();
    }

    public float getVelocity() {
        return bird.getVelocity();
    }
}