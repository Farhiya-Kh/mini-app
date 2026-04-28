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

public class GameView extends View {

    private GameEngine gameEngine;
    private PipeManager pipeManager;

    private final Paint birdPaint = new Paint();
    private final Paint pipePaint = new Paint();
    private final Paint bgPaint = new Paint();
    private final Paint textPaint = new Paint();

    private final float birdX = 300f;
    private final float birdRadius = 60f;

    private float birdY = 500f;
    private float velocity = 0f;

    private final float gravity = 1.2f;
    private final float flapStrength = -20f;

    private boolean gameOver = false;

    private int score = 0;
    private final Set<Pipe> scoredPipes = new HashSet<>();

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        birdPaint.setColor(Color.YELLOW);
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

    private void updateBird() {
        velocity += gravity;
        birdY += velocity;

        if (birdY < birdRadius) {
            birdY = birdRadius;
            velocity = 0;
        }

        if (birdY > getHeight() - birdRadius) {
            birdY = getHeight() - birdRadius;
            gameOver = true;
        }
    }

    private void updatePipes() {
        if (pipeManager != null) {
            pipeManager.update();
        }
    }

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
            }
        }
    }

    private void updateScoreText() {
        TextView scoreText = getRootView().findViewById(R.id.scoreText);

        if (scoreText != null) {
            scoreText.setText("Score: " + score);
        }
    }

    private void checkCollision() {
        if (pipeManager == null) {
            return;
        }

        boolean collision = pipeManager.checkCollision(
                birdX - birdRadius,
                birdY - birdRadius,
                birdRadius * 2
        );

        if (collision) {
            gameOver = true;
        }
    }

    private void drawBird(Canvas canvas) {
        canvas.drawCircle(birdX, birdY, birdRadius, birdPaint);
    }

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

    private void drawGameOverText(Canvas canvas) {
        if (gameOver) {
            canvas.drawText("Game Over", getWidth() / 2f, getHeight() / 2f, textPaint);
            canvas.drawText("Tap to Restart", getWidth() / 2f, getHeight() / 2f + 90f, textPaint);
        }
    }

    private void restartGame() {
        birdY = 500f;
        velocity = 0f;
        gameOver = false;
        score = 0;
        scoredPipes.clear();

        if (pipeManager != null) {
            pipeManager.reset();
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

            velocity = flapStrength;

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

    public void refresh() {
        invalidate();
    }
}