package com.e.whatasillylife;

import android.content.Context;
import android.view.animation.Animation;
import android.widget.ProgressBar;
import android.widget.TextView;


import android.view.animation.Transformation;


public class ProgressBarAnimation extends Animation {

    private Context context;
    private ProgressBar progressBar;
    private TextView textView;
    private float from;
    private float to;

    public ProgressBarAnimation(Context context, ProgressBar progressBar, TextView textView, float from, float to) {
        this.context = context;
        this.progressBar = progressBar;
        this.textView = textView;
        this.from = from;
        this.to = to;
    }


    @Override

    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        float value = from + (to - from) * interpolatedTime;
        progressBar.setProgress((int) value);
        String percent = Integer.toString((int) value);
        textView.setText(percent + " % ");
    }


}

