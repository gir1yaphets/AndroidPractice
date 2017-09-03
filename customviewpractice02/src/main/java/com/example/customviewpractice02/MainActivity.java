package com.example.customviewpractice02;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.customviewpractice02.view.CircleProgressBar;

public class MainActivity extends AppCompatActivity {

    private Button mStartButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton = (Button) findViewById(R.id.startButton);
        final CircleProgressBar progressBar = (CircleProgressBar) findViewById(R.id.progressbar);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressBar.startProgress();
            }
        });
    }
}
