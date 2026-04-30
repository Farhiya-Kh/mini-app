package com.example.myapplication;

public class GameEngine {

    public enum GameState {
        START,
        RUNNING,
        GAME_OVER
    }

    private GameState currentState = GameState.START;

    private static final float FLAP_FORCE = -15f;
    private static final float START_BIRD_Y = 500f;

    private float birdY;
    private float velocity;
    private float gravity = 1.2f;
    private int score;
    private Runnable onUpdate;

    public GameEngine(Runnable onUpdate) {
        this.onUpdate = onUpdate;
        reset();
    }

    public void reset() {
        currentState = GameState.START;
        birdY = START_BIRD_Y;
        velocity = 0f;
        score = 0;
    }

    public void startGame() {
        if (currentState == GameState.START) {
            currentState = GameState.RUNNING;
        }
    }

    public void flap() {
        if (currentState == GameState.RUNNING) {
            velocity = FLAP_FORCE;
        }
    }

    public void update() {
        if (currentState != GameState.RUNNING) {
            return;
        }

        velocity += gravity;
        birdY += velocity;

        if (onUpdate != null) {
            onUpdate.run();
        }
    }

    public void onTap() {
        switch (currentState) {
            case START:
                startGame();
                break;
            case RUNNING:
                flap();
                break;
            case GAME_OVER:
                reset();
                break;
        }
    }

    public void triggerGameOver() {
        currentState = GameState.GAME_OVER;
    }

    public void addPoint() {
        score++;
    }

    public float getBirdY() {
        return birdY;
    }

    public float getVelocity() {
        return velocity;
    }

    public GameState getCurrentState() {
        return currentState;
    }

    public int getScore() {
        return score;
    }
}