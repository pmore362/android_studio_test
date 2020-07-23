package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;

public class MainActivity extends Activity {

    GameEngine mEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int height = metrics.heightPixels;
        int width = metrics.widthPixels;

        mEngine = new GameEngine(this, width, height);
        setContentView(mEngine);

        /*Log.v("Height", Integer.toString(height));
        Log.v("Width", Integer.toString(width));

        Display display = getWindowManager().getDefaultDisplay();
        Point m_size = new Point();
        display.getSize(m_size);
        int m_width = m_size.x;
        int m_height = m_size.y;
        Log.v("My Width : ", Integer.toString(m_width));
        Log.v("My Height : ", Integer.toString(m_height));*/


    }

    @Override
    protected void onPause(){
        super.onPause();
        mEngine.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mEngine.resume();
    }
}