package com.example.abrar.trigapp;

import android.app.Application;

/**
 * Created by abrar on 4/11/16.
 */
public class MyGlobals extends Application {
    private float global_angle_touched;

    public MyGlobals()
    {
    }

    public float getGlobalAngle()
    {
        return global_angle_touched;
    }

    public void setGlobalAngle(float f)
    {
        global_angle_touched = f;
    }
}
