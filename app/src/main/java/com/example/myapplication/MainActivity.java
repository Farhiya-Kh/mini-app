package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private GameEngine engine;
    private GameView gameView;
    private TextView scoreText;

    private final Handler handler = new Handler();
    private final Runnable gameLoop = new Runnable() {
        @Override
        public void run() {
            engine.update();
            gameView.refresh(); // Rahma's method which calls invalidate()

            if (engine.getCurrentState() == GameEngine.GameState.RUNNING) {
                handler.postDelayed(this, 16);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameView  = findViewById(R.id.gameView);
        scoreText = findViewById(R.id.scoreText);

        engine = new GameEngine(() -> {
            runOnUiThread(() -> scoreText.setText(String.valueOf(engine.getScore())));
        });

        gameView.setGameEngine(engine); // Rahma's method name
        engine.reset();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            engine.onTap();

            if (engine.getCurrentState() == GameEngine.GameState.RUNNING) {
                handler.removeCallbacks(gameLoop);
                handler.post(gameLoop);
            }

            if (engine.getCurrentState() == GameEngine.GameState.GAME_OVER) {
                handler.removeCallbacks(gameLoop);
            }
        }
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacks(gameLoop);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (engine != null &&
                engine.getCurrentState() == GameEngine.GameState.RUNNING) {
            handler.post(gameLoop);
        }
    }
}
