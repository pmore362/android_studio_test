package com.example.myapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView implements Runnable {

    Canvas mCanvas;
    SurfaceHolder mHolder;
    Paint mPaint;

    long mFps;
    private final int MILLIS_IN_SECOND = 1000;

    private int mScreenX;
    private int mScreenY;
    private int y = 0;
    private long mHighScore;
    private int radius;
    private Thread mThread = null;
    private boolean paused = true;
    private volatile boolean mPlaying;


    public GameEngine(Context context, int x, int y){

        super(context);
        mScreenX = x;
        mScreenY = y;

        radius = mScreenX / 8;

        mHolder = getHolder();
        mPaint = new Paint();

        startNewGame();
    }
    @Override
    public void run() {

        while(mPlaying){
            long frameTime = System.currentTimeMillis();

            draw();
            y = y+5;
            Log.e("y", Integer.toString(y));

            long timeThisFrame = System.currentTimeMillis() - frameTime;
            if(timeThisFrame > 0){
                mFps = MILLIS_IN_SECOND / timeThisFrame;
            }
        }

    }

    void startNewGame(){

    }

    void draw(){
        if(mHolder.getSurface().isValid()){
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,26,128,152));

            mPaint.setColor(Color.argb(255,255,255,255));
            //mPaint.setShadowLayer(radius+5, mScreenX/2, y, Color.RED);

            mCanvas.drawCircle(mScreenX/2, y, radius, mPaint);

            //mPaint.setColor(Color.argb(255,255,255,255));


            mHolder.unlockCanvasAndPost(mCanvas);
        }
    }

    void pause(){
        mPlaying = false;
        try{
            mThread.join();
        }catch(InterruptedException e){
            Log.e("Error", "JOining thread");
        }
    }

    void resume(){
        mPlaying = true;

        mThread = new Thread(this);
        mThread.start();
    }
}
