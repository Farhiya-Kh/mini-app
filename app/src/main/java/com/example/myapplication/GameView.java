package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class GameView extends View {

    private GameEngine gameEngine;

    private final Paint birdPaint = new Paint();
    private final Paint pipePaint = new Paint();
    private final Paint bgPaint = new Paint();

    public GameView(Context context) {
        super(context);
        init();
    }

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public GameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        birdPaint.setColor(Color.YELLOW);
        birdPaint.setStyle(Paint.Style.FILL);

        pipePaint.setColor(Color.GREEN);
        pipePaint.setStyle(Paint.Style.FILL);

        bgPaint.setColor(Color.rgb(135, 206, 235)); // sky blue
        bgPaint.setStyle(Paint.Style.FILL);
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Background
        canvas.drawRect(0, 0, getWidth(), getHeight(), bgPaint);

        if (gameEngine == null) {
            // Draw placeholder bird in center if engine not connected yet
            float cx = getWidth() / 4f;
            float cy = getHeight() / 2f;
            float radius = 40f;
            canvas.drawCircle(cx, cy, radius, birdPaint);
            return;
        }

        // Draw bird
        Bird bird = gameEngine.getBird();
        if (bird != null) {
            canvas.drawCircle(
                    bird.getX(),
                    bird.getY(),
                    bird.getRadius(),
                    birdPaint
            );
        }

        // Draw pipes
        List<Pipe> pipes = gameEngine.getPipes();
        if (pipes != null) {
            for (Pipe pipe : pipes) {
                canvas.drawRect(
                        pipe.getLeft(),
                        pipe.getTop(),
                        pipe.getRight(),
                        pipe.getBottom(),
                        pipePaint
                );
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            if (gameEngine != null) {
                gameEngine.onTap();
            }
            return true;
        }
        return super.onTouchEvent(event);
    }

    public void refresh() {
        invalidate();
    }
}
