package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView implements Runnable {

    Canvas mCanvas;
    SurfaceHolder mHolder;
    Paint mPaint;

    long mFps;

    private int mScreenX;
    private int mScreenY;

    private long mHighScore;

    private Thread mThread = null;
    private boolean paused = true;
    private volatile boolean mPlaying;


    public GameEngine(Context context, int x, int y){

        super(context);
        mScreenX = x;
        mScreenY = y;

        mHolder = getHolder();
        mPaint = new Paint();

        startNewGame();
    }
    @Override
    public void run() {

    }

    void startNewGame(){

    }
}
