package com.example.myapplication;

public abstract class BirdAppearanceDecorator implements BirdAppearance {

    protected BirdAppearance birdAppearance;

    public BirdAppearanceDecorator(BirdAppearance birdAppearance) {
        this.birdAppearance = birdAppearance;
    }

    @Override
    public int getColor() {
        return birdAppearance.getColor();
    }

    @Override
    public int getAlpha() {
        return birdAppearance.getAlpha();
    }
}