package com.example.myapplication;

import android.graphics.Point;
import android.util.Log;

public class Ball {
    Point location;
    //static int radius;

    public Ball(int x_offset){

        location = new Point();
        location.x = x_offset;
        location.y = - location.x /4;
        Log.v("inside Ball constructor", "Constructor");
    }

    void update(int radius){
        location.y = location.y + 1;

    }
}
