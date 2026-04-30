package com.example.myapplication;

import android.graphics.Color;

public class BasicBirdAppearance implements BirdAppearance {

    @Override
    public int getColor() {
        return Color.YELLOW;
    }

    @Override
    public int getAlpha() {
        return 255;
    }
}