package com.kst.creditscoreapp.donut;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyDonutViewAnimation extends Animation {

    private MyDonutView myDonutView;

    private float oldAngle;
    private float newAngle;

    public MyDonutViewAnimation(MyDonutView myDonutView, int newAngle) {
        this.oldAngle = myDonutView.getAngle();
        this.newAngle = newAngle;
        this.myDonutView = myDonutView;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation transformation) {
        float angle = oldAngle + ((newAngle - oldAngle) * interpolatedTime);

        myDonutView.setAngle(angle);
        myDonutView.requestLayout();
    }
}