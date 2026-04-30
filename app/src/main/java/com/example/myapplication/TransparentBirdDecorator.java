package com.example.myapplication;

public class TransparentBirdDecorator extends BirdAppearanceDecorator {

    public TransparentBirdDecorator(BirdAppearance birdAppearance) {
        super(birdAppearance);
    }

    @Override
    public int getAlpha() {
        return 100;
    }
}