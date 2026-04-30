package com.example.myapplication;

import android.graphics.Color;
import java.util.Random;

public class RandomColorBirdDecorator extends BirdAppearanceDecorator {

    private int color;

    public RandomColorBirdDecorator(BirdAppearance birdAppearance) {
        super(birdAppearance);

        Random random = new Random();
        color = Color.rgb(
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }

    @Override
    public int getColor() {
        return color;
    }
}