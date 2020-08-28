package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    private static int SPLASH_SCREEN_TIME_OUT = 2000;
    GameEngine mEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mEngine = new GameEngine(this, width, height);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /*mEngine.startNewGame();
                setContentView(mEngine);*/
                setContentView(R.layout.activity_game);
              //Intent i =  new Intent(MainActivity.this, GameActivity.class);
              //startActivity(i);
                final Button newGameButton = findViewById(R.id.newGameButton);
                newGameButton.setOnClickListener(new View.OnClickListener(){
                    public void onClick(View v){
                        mEngine.startNewGame();
                        setContentView(mEngine);


                    }
                });
            }
        },SPLASH_SCREEN_TIME_OUT);



        //setContentView(mEngine);


    }

    @Override
    protected void onPause(){
        super.onPause();
        //Log.v("method","inside a main pause method");
        mEngine.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        //Log.v("method ", "inside a main resume method");
        mEngine.resume();
    }
}