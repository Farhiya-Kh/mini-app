package com.example.myapplication;

/**
 * Manages the player's score during the game.
 *
 * <p>The ScoreManager is responsible for storing the current score,
 * increasing the score when the bird passes a pipe, resetting the score,
 * and keeping track of the highest score reached during the session.</p>
 */
public class ScoreManager {

    private int currentScore;
    private int highScore;

    /**
     * Creates a new ScoreManager with both scores starting at zero.
     */
    public ScoreManager() {
        this.currentScore = 0;
        this.highScore = 0;
    }

    /**
     * Increases the current score by one point.
     *
     * <p>If the new current score is greater than the high score,
     * the high score is updated as well.</p>
     */
    public void addPoint() {
        currentScore++;

        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    /**
     * Increases the current score by a specific number of points.
     *
     * @param points number of points to add
     */
    public void addPoints(int points) {
        if (points <= 0) {
            return;
        }

        currentScore += points;

        if (currentScore > highScore) {
            highScore = currentScore;
        }
    }

    /**
     * Resets the current score back to zero.
     *
     * <p>The high score is not reset because it should remain saved
     * during the current game session.</p>
     */
    public void resetCurrentScore() {
        currentScore = 0;
    }

    /**
     * Resets both the current score and the high score back to zero.
     */
    public void resetAllScores() {
        currentScore = 0;
        highScore = 0;
    }

    /**
     * Gets the current score.
     *
     * @return current player score
     */
    public int getCurrentScore() {
        return currentScore;
    }

    /**
     * Gets the highest score reached during the current session.
     *
     * @return highest score reached
     */
    public int getHighScore() {
        return highScore;
    }

    /**
     * Formats the current score as text for the game screen.
     *
     * @return current score text
     */
    public String getCurrentScoreText() {
        return "Score: " + currentScore;
    }

    /**
     * Formats the high score as text for the game over screen.
     *
     * @return high score text
     */
    public String getHighScoreText() {
        return "High Score: " + highScore;
    }
}