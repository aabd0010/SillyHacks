package com.e.whatasillylife;

import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.os.Bundle;

public class LoadingPage extends AppCompatActivity {

    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loading_page);

        progressBar = findViewById(R.id.progressBar);
        textView  = findViewById(R.id.textView2);


        progressBar.setMax(100);
        progressBar.setScaleY(3f);

        progressAnimation();
    }

    public void progressAnimation (){
        ProgressBarAnimation anim = new ProgressBarAnimation(this, progressBar, textView, 0f ,101f);
        anim.setDuration(9000);
        progressBar.setAnimation(anim);
    }
}