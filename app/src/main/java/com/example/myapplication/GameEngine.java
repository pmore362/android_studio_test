package com.example.myapplication;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;

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

    private Ball mBall;
    private Ball prev;
    ArrayList ball = new ArrayList();
    public GameEngine(Context context, int x, int y){

        super(context);
        mScreenX = x;
        mScreenY = y;

        radius = mScreenX / 8;

        mHolder = getHolder();
        mPaint = new Paint();

        /*for (int i = 0; i < 10; i++) {
            mBall[i] = new Ball(mScreenX / 2);
        }*/




        Log.v("constructor", "inside engine constructor");
        startNewGame();
    }
    @Override
    public void run() {


        Log.v("inside run method", "run method");
        while(mPlaying){
            long frameTime = System.currentTimeMillis();

            draw();

            /*for(int i = 0; i < 10; i++){

                if(i > 0 && mBall[i-1].location.y < radius * 2){
                    //Log.v("if condition", "inside if condition");
                    continue;
                }
                mBall[i].update(radius);
            }*/

            for(int i=0; i < ball.size();i++){
                if(i>0){
                    prev = (Ball) ball.get(i-1);
                }
                if(i > 0 && prev.location.y < radius * 2){
                    continue;
                }
                mBall = (Ball) ball.get(i);
                mBall.update(radius);
            }

           /* long timeThisFrame = System.currentTimeMillis() - frameTime;
            if(timeThisFrame > 0){
                mFps = MILLIS_IN_SECOND / timeThisFrame;
            }*/
        }

    }

    void startNewGame(){
        /*for(int i=0; i < ball.size(); i++){

            mBall = new Ball(mScreenX / 2);
        }*/
        for(int i=0;i<10;i++){
            mBall = new Ball(mScreenX / 2);
            ball.add(mBall);
        }
        Log.v("method", "inside a startNewGame Method");
    }

    void draw(){
        if(mHolder.getSurface().isValid()){
            //Log.e("message","inside a draw method.");
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,26,128,152));

            mPaint.setColor(Color.argb(255,255,255,255));
            //mPaint.setShadowLayer(radius+5, mScreenX/2, y, Color.RED);

           /* for(int i=0; i < 10; i++){
                mCanvas.drawCircle(mBall[i].location.x,  mBall[i].location.y, radius, mPaint);
            }*/

           for(int i =0; i < ball.size(); i++){
               mBall = (Ball) ball.get(i);
               mCanvas.drawCircle(mBall.location.x, mBall.location.y, radius, mPaint);
           }

            //mPaint.setColor(Color.argb(255,255,255,255));

            mCanvas.drawText("Works", mScreenX / 2,mScreenY/2, mPaint);
            mHolder.unlockCanvasAndPost(mCanvas);
        }
        Log.v("method","inside a draw method");
    }

    void pause(){
        mPlaying = false;
        try{
            mThread.join();
        }catch(InterruptedException e){
            Log.e("Error", "JOining thread");
        }
        Log.v("method", "inside a pause method");
    }

    void resume(){
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
        Log.v("method", "inside a resume method");
    }
}
