package com.example.myapplication;

/**
 * Controls the main game state and score.
 *
 * <p>The GameEngine tracks whether the game is starting, running, or over.
 * It also stores the current score and handles simple state changes.</p>
 */
public class GameEngine {

    /**
     * Represents the current state of the game.
     */
    public enum GameState {
        START,
        RUNNING,
        GAME_OVER
    }

    private GameState currentState;
    private int score;
    private Runnable onUpdate;

    /**
     * Creates a game engine.
     *
     * @param onUpdate code that should run when the game updates
     */
    public GameEngine(Runnable onUpdate) {
        this.onUpdate = onUpdate;
        reset();
    }

    /**
     * Resets the game state and score.
     */
    public void reset() {
        currentState = GameState.START;
        score = 0;
    }

    /**
     * Starts the game if it is on the start screen.
     */
    public void startGame() {
        if (currentState == GameState.START) {
            currentState = GameState.RUNNING;
        }
    }

    /**
     * Handles a tap based on the current game state.
     */
    public void onTap() {
        switch (currentState) {
            case START:
                startGame();
                break;
            case GAME_OVER:
                reset();
                break;
            case RUNNING:
                break;
            default:
                break;
        }
    }

    /**
     * Updates the game.
     */
    public void update() {
        if (currentState != GameState.RUNNING) {
            return;
        }

        if (onUpdate != null) {
            onUpdate.run();
        }
    }

    /**
     * Changes the game state to game over.
     */
    public void triggerGameOver() {
        currentState = GameState.GAME_OVER;
    }

    /**
     * Adds one point to the score.
     */
    public void addPoint() {
        score++;
    }

    /**
     * Gets the current state.
     *
     * @return current game state
     */
    public GameState getCurrentState() {
        return currentState;
    }

    /**
     * Gets the current score.
     *
     * @return current score
     */
    public int getScore() {
        return score;
    }
}