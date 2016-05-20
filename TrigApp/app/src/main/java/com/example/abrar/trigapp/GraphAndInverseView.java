package com.example.abrar.trigapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;
import static java.lang.Math.*;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.graphics.*;
import org.achartengine.chart.*;

/**
 * Created by abrar on 4/12/16.
 *
 * NOTE: DO NOT LOOK AT THIS. IT DOESN'T DO ANYTHING. LEFT IT IN FOR OLD TIME'S SAKE (REFERENCE)
 *
 */

public class GraphAndInverseView extends View {
    Paint paint = new Paint();
    private Path mPath;
    private GraphicalView myChart;
    private XYSeriesRenderer sineRenderer,cosineRenderer,tangentRenderer;
    private XYMultipleSeriesRenderer myRenderer;
    private XYSeries sineSeries,cosineSeries,tangentSeries;
    private XYMultipleSeriesDataset mySeries;

    public GraphAndInverseView(Context context) {
        super(context);
        /*mPath = new Path();
        mPath.moveTo(0,100);
        mPath.quadTo(50, -50, 100, 0);
        mPath.quadTo(150, 50, 200, 0);
        mPath.quadTo(250, -50, 300, 0);
        mPath.quadTo(350, 50, 400, 0);
        mPath.quadTo(450, -50, 500, 100);
        paint.setColor(Color.BLACK);*/
    }

    private void initializeChart()
    {
        sineRenderer=new XYSeriesRenderer();
        cosineRenderer=new XYSeriesRenderer();
        tangentRenderer=new XYSeriesRenderer();
        sineRenderer.setColor(Color.RED);
        cosineRenderer.setColor(Color.GREEN);
        tangentRenderer.setColor(Color.BLUE);
        myRenderer=new XYMultipleSeriesRenderer();
        myRenderer.addSeriesRenderer(sineRenderer);
        myRenderer.addSeriesRenderer(cosineRenderer);
        myRenderer.addSeriesRenderer(tangentRenderer);
        myRenderer.setPanEnabled(false);
        myRenderer.setYAxisMax(1);
        myRenderer.setYAxisMin(-1);
        sineSeries=new XYSeries("Sine");
        cosineSeries=new XYSeries("Cosine");
        tangentSeries=new XYSeries("Tangent");
        mySeries=new XYMultipleSeriesDataset();
        mySeries.addSeries(sineSeries);
        mySeries.addSeries(cosineSeries);
        mySeries.addSeries(tangentSeries);
    }

    private void addData()
    {
        for(int angle=5;angle<=1440;angle+=5)
        {
            sineSeries.add(angle, Math.sin(angle*(Math.PI/180)));
            cosineSeries.add(angle, Math.cos(angle*(Math.PI/180)));
            tangentSeries.add(angle, Math.tan(angle*(Math.PI/180)));
        }
    }

    @Override
    public void onDraw(Canvas canvas) {
        /*canvas.drawColor(Color.WHITE);
        canvas.drawLine(0,350,900,350, paint); // x-axis
        canvas.drawLine(450, 0, 450, 900, paint); // y-axis

        for(double x=-450;x<=450;x=x+0.5)
        {
            double y = 50 * sin(x*(3.1415926/180));
            int Y = (int)y;
            int X = (int)x;
            canvas.drawLine(450+X,350-Y,450+X,350-Y, paint);
        }*/
        /*canvas.save();
        canvas.translate(0, getMeasuredHeight() / 2F);
        // just making sure to clip starting and ending curve
        canvas.clipRect(50, -100, 450, 100, Region.Op.INTERSECT);
        canvas.drawPath(mPath, paint);
        canvas.restore();*/

        /*if(myChart==null)
        {
            initializeChart();
            addData();
            myChart=ChartFactory.getLineChartView(canvas, mySeries, myRenderer);
            layout.addView(myChart);
        }
        else
        {
            myChart.repaint();
        }*/
    }
}
