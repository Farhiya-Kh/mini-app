package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.HashSet;
import java.util.Set;

/**
 * Draws and updates the Flappy Bird game screen.
 *
 * <p>The GameView handles drawing the bird, drawing pipes, detecting taps,
 * updating score text, and applying simple decorator behavior when the bird
 * passes a pipe or hits an obstacle.</p>
 */
public class GameView extends View {

    private GameEngine gameEngine;
    private PipeManager pipeManager;
    private Bird bird;

    private final Paint birdPaint = new Paint();
    private final Paint pipePaint = new Paint();
    private final Paint bgPaint = new Paint();
    private final Paint textPaint = new Paint();

    private final float birdX = 300f;
    private final float birdRadius = 60f;

    private static final float START_BIRD_Y = 500f;
    private static final float GRAVITY = 1.2f;
    private static final float FLAP_STRENGTH = -20f;

    private int score = 0;
    private boolean gameOver = false;
    private final Set<Pipe> scoredPipes = new HashSet<>();

    /**
     * Creates a GameView.
     *
     * @param context app context
     */
    public GameView(Context context) {
        super(context);
        init();
    }

    /**
     * Creates a GameView with XML attributes.
     *
     * @param context app context
     * @param attrs XML attributes
     */
    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    /**
     * Creates a GameView with XML attributes and style.
     *
     * @param context app context
     * @param attrs XML attributes
     * @param defStyleAttr default style
     */
    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * Initializes paint objects and starting game objects.
     */
    private void init() {
        bird = new Bird(START_BIRD_Y);

        birdPaint.setStyle(Paint.Style.FILL);

        pipePaint.setColor(Color.GREEN);
        pipePaint.setStyle(Paint.Style.FILL);

        bgPaint.setColor(Color.rgb(135, 206, 235));
        bgPaint.setStyle(Paint.Style.FILL);

        textPaint.setColor(Color.WHITE);
        textPaint.setTextSize(70f);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setFakeBoldText(true);
    }

    /**
     * Sets the game engine.
     *
     * @param gameEngine game engine object
     */
    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        if (getWidth() <= 0 || getHeight() <= 0) {
            return;
        }

        if (pipeManager == null) {
            pipeManager = new PipeManager(
                    getWidth(),
                    getHeight(),
                    180,
                    600,
                    5f,
                    700
            );
        }

        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

        if (!gameOver) {
            updateBird();
            updatePipes();
            updateScore();
            updateScoreText();
            checkCollision();
        }

        drawBird(canvas);
        drawPipes(canvas);
        drawGameOverText(canvas);

        postInvalidateOnAnimation();
    }

    /**
     * Updates the bird movement.
     */
    private void updateBird() {
        bird.setVelocity(bird.getVelocity() + GRAVITY);
        bird.setYPosition(bird.getYPosition() + bird.getVelocity());

        if (bird.getYPosition() < birdRadius) {
            bird.setYPosition(birdRadius);
            bird.setVelocity(0);
        }

        if (bird.getYPosition() > getHeight() - birdRadius) {
            bird.setYPosition(getHeight() - birdRadius);
            endGame();
        }
    }

    /**
     * Updates all pipes.
     */
    private void updatePipes() {
        if (pipeManager != null) {
            pipeManager.update();
        }
    }

    /**
     * Updates the score when the bird passes a pipe.
     */
    private void updateScore() {
        if (pipeManager == null) {
            return;
        }

        for (int i = 0; i < pipeManager.getPipes().size(); i += 2) {
            Pipe topPipe = pipeManager.getPipes().get(i);

            boolean pipePassedBird = topPipe.getX() + topPipe.getWidth() < birdX;
            boolean alreadyScored = scoredPipes.contains(topPipe);

            if (pipePassedBird && !alreadyScored) {
                score++;
                scoredPipes.add(topPipe);
                bird.changeToRandomColor();

                if (gameEngine != null) {
                    gameEngine.addPoint();
                }
            }
        }
    }

    /**
     * Updates the score TextView.
     */
    private void updateScoreText() {
        TextView scoreText = getRootView().findViewById(R.id.scoreText);

        if (scoreText != null) {
            scoreText.setText("Score: " + score);
        }
    }

    /**
     * Checks if the bird hit any pipe.
     */
    private void checkCollision() {
        if (pipeManager == null) {
            return;
        }

        boolean collision = pipeManager.checkCollision(
                birdX - birdRadius,
                bird.getYPosition() - birdRadius,
                birdRadius * 2
        );

        if (collision) {
            endGame();
        }
    }

    /**
     * Ends the game and applies the transparent bird decorator.
     */
    private void endGame() {
        gameOver = true;
        bird.makeTransparent();

        if (gameEngine != null) {
            gameEngine.triggerGameOver();
        }
    }

    /**
     * Draws the bird.
     *
     * @param canvas canvas to draw on
     */
    private void drawBird(Canvas canvas) {
        birdPaint.setColor(bird.getColor());
        birdPaint.setAlpha(bird.getAlpha());
        canvas.drawCircle(birdX, bird.getYPosition(), birdRadius, birdPaint);
        birdPaint.setAlpha(255);
    }

    /**
     * Draws all pipes.
     *
     * @param canvas canvas to draw on
     */
    private void drawPipes(Canvas canvas) {
        if (pipeManager == null) {
            return;
        }

        for (Pipe pipe : pipeManager.getPipes()) {
            canvas.drawRect(
                    pipe.getX(),
                    pipe.getY(),
                    pipe.getX() + pipe.getWidth(),
                    pipe.getY() + pipe.getHeight(),
                    pipePaint
            );
        }
    }

    /**
     * Draws game over text when the game ends.
     *
     * @param canvas canvas to draw on
     */
    private void drawGameOverText(Canvas canvas) {
        if (gameOver) {
            canvas.drawText("Game Over", getWidth() / 2f, getHeight() / 2f, textPaint);
            canvas.drawText("Tap to Restart", getWidth() / 2f, getHeight() / 2f + 90f, textPaint);
        }
    }

    /**
     * Restarts the game.
     */
    private void restartGame() {
        bird = new Bird(START_BIRD_Y);
        gameOver = false;
        score = 0;
        scoredPipes.clear();

        if (pipeManager != null) {
            pipeManager.reset();
        }

        if (gameEngine != null) {
            gameEngine.reset();
        }

        updateScoreText();
        invalidate();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            performClick();

            if (gameOver) {
                restartGame();
                return true;
            }

            bird.setVelocity(FLAP_STRENGTH);

            if (gameEngine != null) {
                gameEngine.onTap();
            }

            invalidate();
            return true;
        }

        return super.onTouchEvent(event);
    }

    @Override
    public boolean performClick() {
        super.performClick();
        return true;
    }

    /**
     * Redraws the view.
     */
    public void refresh() {
        invalidate();
    }
}