package com.example.a02_animation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.a02_animation.animation.AnimatorExecutor;
import com.example.a02_animation.animation.ViewAnimatorExecutor04;
import com.example.a02_animation.animation.view.CircleView;

public class MainActivity extends AppCompatActivity {

    private Button mButton;
    private TextView mTextView;
    private CircleView mCircleView;
    private AnimatorExecutor mExecutor;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        mButton = (Button) findViewById(R.id.startAnimation);
        mTextView = (TextView) findViewById(R.id.textView);
        mCircleView = (CircleView) findViewById(R.id.circleView);

        mExecutor = new ViewAnimatorExecutor04(mTextView);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExecutor.onStartAnimation();
            }
        });
    }
}
