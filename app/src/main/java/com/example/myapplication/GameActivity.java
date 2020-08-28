package com.example.myapplication;

import android.annotation.SuppressLint;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;

//import static com.example.myapplication.MainActivity.mEngine;

public class GameActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        /*DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;
        mEngine = new GameEngine(this, width, height);*/

        setContentView(R.layout.activity_game);

        final Button resumeGameButton = findViewById(R.id.resumeButton);
        resumeGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        final Button newGameButton = findViewById(R.id.newGameButton);
        newGameButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                //setContentView(null);
                finish();

            }
        });

        final Button optionsButton = findViewById(R.id.optionsButton);
        optionsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){

            }
        });

        final Button creditsButton = findViewById(R.id.creditButton);
        creditsButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

            }
        });

        final Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener(){
            public void onClick(View V){

            }
        });
    }

}