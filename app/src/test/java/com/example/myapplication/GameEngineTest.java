package com.example.myapplication;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class GameEngineTest {

    private GameEngine engine;

    @Before
    public void setUp() {
        engine = new GameEngine(() -> {});
        engine.reset();
    }

    @Test
    public void testResetNormal() {
        assertEquals(GameEngine.GameState.START, engine.getCurrentState());
        assertEquals(500f, engine.getBirdY(), 0.01f);
        assertEquals(0f, engine.getVelocity(), 0.01f);
    }

    @Test
    public void testStartGameSetsRunningState() {
        engine.startGame();
        assertEquals(GameEngine.GameState.RUNNING, engine.getCurrentState());
    }

    @Test
    public void testFlapGivesUpwardVelocityWhenRunning() {
        engine.startGame();
        engine.flap();
        assertTrue(engine.getVelocity() < 0);
    }

    @Test
    public void testFlapDoesNothingWhenNotRunning() {
        float velocityBefore = engine.getVelocity();
        engine.flap();
        assertEquals(velocityBefore, engine.getVelocity(), 0.01f);
    }

    @Test
    public void testUpdateMovesBirdUpAfterFlap() {
        engine.startGame();
        float initialY = engine.getBirdY();
        engine.flap();
        engine.update();
        assertTrue(engine.getBirdY() < initialY);
    }

    @Test
    public void testUpdateMovesBirdDownWithGravity() {
        engine.startGame();
        float initialY = engine.getBirdY();
        engine.update();
        assertTrue(engine.getBirdY() > initialY);
    }

    @Test
    public void testUpdateDoesNothingWhenNotRunning() {
        float initialY = engine.getBirdY();
        engine.update();
        assertEquals(initialY, engine.getBirdY(), 0.01f);
    }

    @Test
    public void testTriggerGameOverSetsGameOverState() {
        engine.startGame();
        engine.triggerGameOver();
        assertEquals(GameEngine.GameState.GAME_OVER, engine.getCurrentState());
    }

    @Test
    public void testResetAfterGameOver() {
        engine.startGame();
        engine.flap();
        engine.update();
        engine.triggerGameOver();
        engine.reset();
        assertEquals(GameEngine.GameState.START, engine.getCurrentState());
        assertEquals(500f, engine.getBirdY(), 0.01f);
        assertEquals(0f, engine.getVelocity(), 0.01f);
    }

    @Test
    public void testOnTapStartsGameFromStartState() {
        engine.onTap();
        assertEquals(GameEngine.GameState.RUNNING, engine.getCurrentState());
    }

    @Test
    public void testOnTapFlapsWhenRunning() {
        engine.startGame();
        engine.onTap();
        assertTrue(engine.getVelocity() < 0);
    }

    @Test
    public void testOnTapResetsFromGameOver() {
        engine.startGame();
        engine.triggerGameOver();
        engine.onTap();
        assertEquals(GameEngine.GameState.START, engine.getCurrentState());
    }
}