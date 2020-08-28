package com.example.myapplication;

import android.content.AbstractThreadedSyncAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.os.CountDownTimer;
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
    private boolean mPaused = false;
    private volatile boolean mPlaying;

    private Ball mBall;
    private Ball prev;
    ArrayList ball = new ArrayList();
    public GameEngine(Context context, int x, int y){

        super(context);
        //Log.v("method", "inside a Game engine constructor");
        mScreenX = x;
        mScreenY = y;

        radius = mScreenX / 8;

        mHolder = getHolder();
        mPaint = new Paint();

        //startNewGame();

    }
    @Override
    public void run() {


        //Log.v("inside run method", "run method");
        while(mPlaying){
            long frameTime = System.currentTimeMillis();

            draw();
            if(!mPaused){
                for(int i=0; i < ball.size();i++){
                    if(i>0){
                        prev = (Ball) ball.get(i-1);
                    }
                    if(i > 0 && ((prev.location.y - radius) < 5)){
                        continue;
                    }
                    mBall = (Ball) ball.get(i);
                    mBall.update(radius);
                }
            }


           /* long timeThisFrame = System.currentTimeMillis() - frameTime;
            if(timeThisFrame > 0){
                mFps = MILLIS_IN_SECOND / timeThisFrame;
            }*/
        }

    }

    void startNewGame(){

        for(int i=0;i<10;i++){
            mBall = new Ball(mScreenX / 2);
            ball.add(mBall);
        }
        //Log.v("method", "inside a startNewGame Method");
    }

    void draw(){
        if(mHolder.getSurface().isValid()){
            //Log.v("message","inside a draw method.");
            //mHolder.getSurface();
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255,26,128,152));

            mPaint.setColor(Color.argb(255,255,255,255));
            //mPaint.setShadowLayer(radius+5, mScreenX/2, y, Color.RED);


           for(int i =0; i < ball.size(); i++){
               mBall = (Ball) ball.get(i);
               mCanvas.drawCircle(mBall.location.x, mBall.location.y, radius, mPaint);
           }

            //mPaint.setColor(Color.argb(255,255,255,255));

            mCanvas.drawText("Works", mScreenX / 2,mScreenY/2, mPaint);
            mHolder.unlockCanvasAndPost(mCanvas);
        }
        //Log.v("method","end of draw method");
    }

    void pause(){
        mPlaying = false;
        try{
            mThread.join();
        }catch(InterruptedException e){
            Log.e("Error", "Joining thread");
    }
        //Log.v("method", "inside a pause method");
    }

    void resume(){
        //resumeTimer();
        mPlaying = true;
        mThread = new Thread(this);
        mThread.start();
        //Log.v("method", "inside a resume method");
    }

    void resumeTimer() {
        Log.v("message", "inside resume timer");
        //mHolder = getHolder();
        if (mHolder.getSurface().isValid()) {
            Log.v("message","inside a draw method.");
            mCanvas = mHolder.lockCanvas();
            mCanvas.drawColor(Color.argb(255, 26, 128, 152));

            mPaint.setColor(Color.argb(255, 255, 255, 255));
            //mPaint.setShadowLayer(radius+5, mScreenX/2, y, Color.RED);

           /* for(int i=0; i < 10; i++){
                mCanvas.drawCircle(mBall[i].location.x,  mBall[i].location.y, radius, mPaint);
            }*/

            for (int i = 0; i < ball.size(); i++) {
                mBall = (Ball) ball.get(i);
                mCanvas.drawCircle(mBall.location.x, mBall.location.y, radius, mPaint);
            }
            mPaint.setAlpha(10);
            new CountDownTimer(30000, 1000) {
                @Override
                public void onTick(long l) {
                    mCanvas.drawText("resuming in " + l / 1000, mScreenX / 2, mScreenY / 2, mPaint);
                    Log.v("message", "inside a onTick method");
                }

                @Override
                public void onFinish() {

                }
            }.start();
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction() & MotionEvent.ACTION_MASK){

            case MotionEvent.ACTION_DOWN:
                //Log.v("method", "inside a Action Down ");
                mPaused = !mPaused;
                int x = (int) event.getX();
                int y = (int) event.getY();
                //Log.v("value", ""+x+y);
                for(int i =0; i < ball.size(); i++){
                    mBall = (Ball) ball.get(i);
                    //Log.v("inside a for loop"," method");
                    //mCanvas.drawCircle(mBall.location.x, mBall.location.y, radius, mPaint);

                    if((x > mBall.location.x - radius) && (x < mBall.location.x + radius)){
                        if((y > mBall.location.y - radius) && (y < mBall.location.y + radius)){
                            Log.v("touch method", "success");
                        }
                    }
                }
                break;

            case MotionEvent.ACTION_MOVE:
                Log.v("method", "inside a action move");
                int xx = (int) event.getX();
                int xy = (int) event.getY();
                Log.v("value", ""+xx +" "+xy);
                break;
        }
        return true;
    }
}
