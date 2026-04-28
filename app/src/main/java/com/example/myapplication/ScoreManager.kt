package com.example.myapplication

/**
 * Creates a new ScoreManager with both scores starting at zero.
 */
/**
 * Manages the player's score during the game.
 *
 *
 * The ScoreManager is responsible for storing the current score,
 * increasing the score when the bird passes a pipe, resetting the score,
 * and keeping track of the highest score reached during the session.
 */
class ScoreManager {
    /**
     * Gets the current score.
     *
     * @return current player score
     */
    var currentScore: Int = 0
        private set

    /**
     * Gets the highest score reached during the current session.
     *
     * @return highest score reached
     */
    var highScore: Int = 0
        private set

    /**
     * Increases the current score by one point.
     *
     *
     * If the new current score is greater than the high score,
     * the high score is updated as well.
     */
    fun addPoint() {
        currentScore++

        if (currentScore > highScore) {
            highScore = currentScore
        }
    }

    /**
     * Increases the current score by a specific number of points.
     *
     * @param points number of points to add
     */
    fun addPoints(points: Int) {
        if (points <= 0) {
            return
        }

        currentScore += points

        if (currentScore > highScore) {
            highScore = currentScore
        }
    }

    /**
     * Resets the current score back to zero.
     *
     *
     * The high score is not reset because it should remain saved
     * during the current game session.
     */
    fun resetCurrentScore() {
        currentScore = 0
    }

    /**
     * Resets both the current score and the high score back to zero.
     */
    fun resetAllScores() {
        currentScore = 0
        highScore = 0
    }

    val currentScoreText: String
        /**
         * Formats the current score as text for the game screen.
         *
         * @return current score text
         */
        get() = "Score: " + currentScore

    val highScoreText: String
        /**
         * Formats the high score as text for the game over screen.
         *
         * @return high score text
         */
        get() = "High Score: " + highScore
}