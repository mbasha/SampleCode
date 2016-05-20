package com.example.abrar.trigapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.FrameLayout;

/**
 * Created by abrar on 3/28/16.
 */
public class UnitCircle extends Activity {
    UnitCircleView unitCircleView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit_circle_layout);
        unitCircleView = new UnitCircleView(this);
        unitCircleView.setBackgroundColor(Color.WHITE);

        FrameLayout frame = (FrameLayout)findViewById(R.id.surfaceViewUnitCircle);
        frame.addView(unitCircleView);
    }
}
