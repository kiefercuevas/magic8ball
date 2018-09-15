package com.example.kiefer.a8barapplication;

import android.animation.Animator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener{

    private LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lottieAnimationView = findViewById(R.id.animation_view);
        lottieAnimationView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                lottieAnimationView.playAnimation();
            }
        });

        findViewById(R.id.btn_play).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        lottieAnimationView.setSpeed(3);
        lottieAnimationView.reverseAnimationSpeed();
        lottieAnimationView.playAnimation();


        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                ChangeIntent();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

    }

    private void ChangeIntent(){
        Intent intent = new Intent(this, MyPlayActivity.class);
        startActivity(intent);
    }
}
