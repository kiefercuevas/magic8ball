package com.example.kiefer.a8barapplication;

import android.animation.Animator;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import java.util.Random;

public class MyPlayActivity extends AppCompatActivity {

    Random random = new Random();
    SensorManager SensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int value = 0;
    TextView textView;
    private LottieAnimationView lottieAnimationView;

    private final String[][] answerArray = {
            {
                    "En mi opinion, si",
                    "Es cierto",
                    "Es decididamente asi",
                    "Probablemente",
                    "Buen pronostico",
                    "Todo apunta a que si",
                    "Sin duda",
                    "Si",
                    "Si,Definitivamente",
                    "Debes confiar en ello"
            },
            {
                    "Respuesta vaga,vuelte a intentarlo",
                    "Pregunta en otro momento",
                    "Sera mejor que no te lo diga ahora",
                    "No puedo predecirlo ahora",
                    "Concentrate y vuelve a preguntar"},
            {
                    "No cuentes con ello",
                    "Mi respuesta es no",
                    "Mis fuentes me dicen que no",
                    "Las perspectivas no son buenas",
                    "Muy dudoso"
            }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_play);

        textView = findViewById(R.id.txt_prueba);
        SensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = SensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        lottieAnimationView = findViewById(R.id.bounce_animation);

        if(sensor == null)
            finish();

            sensorEventListener = new SensorEventListener() {
                @Override
                public void onSensorChanged(SensorEvent event) {

                    if(!lottieAnimationView.isAnimating()){
                            float x = event.values[0];
                            if(x <= -5 && value == 0){
                                value++;
                            }else if(x >= 5 && value == 1){
                                value++;
                            }
                            if(value == 2){
                                textView.setText(" ");
                                int numberArray = random.nextInt(answerArray.length);
                                int numberAnswer = random.nextInt(answerArray[numberArray].length);
                                setLottieAnimationView(numberArray,numberAnswer,lottieAnimationView);
                                value = 0;
                            }
                    }
                }
                @Override
                public void onAccuracyChanged(Sensor sensor, int accuracy) {

                }
            };
        //para subscribir el evento
        start();
    }


    private void start(){
        SensorManager.registerListener(sensorEventListener,sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    private void stop(){
        SensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        stop();
        super.onPause();
    }

    @Override
    protected void onResume() {
        start();
        super.onResume();
    }

    private void setLottieAnimationView(int numberArray,int numberAnswer,LottieAnimationView lottieAnimationView){
        final int numArr = numberArray;
        final int ansArr = numberAnswer;

        if(numberArray == 0)
            lottieAnimationView.setAnimation("8BallAnimationBouncePositive.json");
        else if(numberArray == 1)
            lottieAnimationView.setAnimation("8BallAnimationBounceNeutral.json");
        else
            lottieAnimationView.setAnimation("8BallAnimationBounceNegative.json");
        lottieAnimationView.playAnimation();


        lottieAnimationView.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }
            @Override
            public void onAnimationEnd(Animator animation) {

                textView.setGravity(Gravity.CENTER);
                textView.setTextColor(Color.WHITE);
                textView.setMaxWidth(350);
                textView.setText(answerArray[numArr][ansArr]);


            }
            @Override
            public void onAnimationCancel(Animator animation) {

            }
            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });


    }
}
