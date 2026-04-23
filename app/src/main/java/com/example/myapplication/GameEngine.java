package com.example.myapplication;

import android.app.GameState;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class GameEngine {

    //private GameState currentState = GameState.CREATOR;

    private Handler handler = new MyHandler();
    private final int FRAME_DELAY = 16; // ~60 FPS

    private boolean isRunning = false;
    private float birdY;
    private float velocity;
    private float gravity = 1.2f;
    private Runnable onUpdate;

    public GameEngine(Runnable onUpdate) {
        this.onUpdate = onUpdate;
    }

    private static class MyHandler extends Handler {
        @Override
        public void close() throws SecurityException {

        }

        @Override
        public void flush() {

        }

        @Override
        public void publish(LogRecord record) {

        }
    }
}