package com.example.myapplication;

/**
 * Handles the bird's movement and appearance state.
 *
 * <p>The Bird class is responsible for gravity, flap movement, position updates,
 * and appearance changes. The appearance uses the decorator pattern so the bird
 * can change color or become transparent without changing the movement logic.</p>
 */
public class Bird {

    private float yPosition;
    private float velocity;

    private static final float GRAVITY_FORCE = 0.5f;
    private static final float FLAP_STRENGTH = -10f;

    private BirdAppearance appearance;

    /**
     * Creates a bird at the given starting y-position.
     *
     * @param startY starting vertical position
     */
    public Bird(float startY) {
        this.yPosition = startY;
        this.velocity = 0;
        this.appearance = new BasicBirdAppearance();
    }

    /**
     * Updates the bird's position for one frame.
     */
    public void update() {
        applyGravity();
        yPosition += velocity;
    }

    /**
     * Applies gravity to the bird.
     */
    public void applyGravity() {
        velocity += GRAVITY_FORCE;
    }

    /**
     * Makes the bird move upward.
     */
    public void flap() {
        velocity = FLAP_STRENGTH;
    }

    /**
     * Changes the bird to a random color.
     */
    public void changeToRandomColor() {
        appearance = new RandomColorBirdDecorator(new BasicBirdAppearance());
    }

    /**
     * Makes the bird transparent.
     */
    public void makeTransparent() {
        appearance = new TransparentBirdDecorator(appearance);
    }

    /**
     * Resets the bird appearance back to the normal default appearance.
     */
    public void resetAppearance() {
        appearance = new BasicBirdAppearance();
    }

    /**
     * Gets the bird's current y-position.
     *
     * @return current y-position
     */
    public float getYPosition() {
        return yPosition;
    }

    /**
     * Sets the bird's current y-position.
     *
     * @param yPosition new y-position
     */
    public void setYPosition(float yPosition) {
        this.yPosition = yPosition;
    }

    /**
     * Gets the bird's current velocity.
     *
     * @return current velocity
     */
    public float getVelocity() {
        return velocity;
    }

    /**
     * Sets the bird's current velocity.
     *
     * @param velocity new velocity
     */
    public void setVelocity(float velocity) {
        this.velocity = velocity;
    }

    /**
     * Gets the bird's current color.
     *
     * @return current color
     */
    public int getColor() {
        return appearance.getColor();
    }

    /**
     * Gets the bird's current transparency.
     *
     * @return alpha value
     */
    public int getAlpha() {
        return appearance.getAlpha();
    }
}