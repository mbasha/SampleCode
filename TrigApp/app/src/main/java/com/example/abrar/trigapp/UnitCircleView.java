package com.example.abrar.trigapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.text.DecimalFormat;

import static java.lang.Math.*;

/**
 * Created by abrar on 4/12/16.
 */
public class UnitCircleView extends View {
    Paint paint = new Paint();

    RectF arcRectangle;
    private Bitmap canvasBitmap;
    private Paint canvasPaint;
    private Canvas circleCanvas;
    MyGlobals globalVariable;
    int myCanvasHeight, myCircler,myCirclex, myCircley;
    Paint paintAxes, paintCircle, paintLineToPoint, paintPoint, paintPointOthers,
            paintQuadCoordinates,paintTheta, paintTriangleLeg, paintTriangleLegText, paintTrigFunctionsText;
    Path path;
    float theta;

    public UnitCircleView(Context context) {
        super(context);
        paint.setColor(Color.BLACK);
        init();
    }

    public UnitCircleView(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        init();
    }

    public UnitCircleView(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        init();
    }

    private void drawArc(float f, Canvas canvas)
    {
        int i = myCirclex;
        int j = myCircler / 3;
        int k = myCircley;
        int l = myCircler / 3;
        int i1 = myCirclex;
        int j1 = myCircler / 3;
        int k1 = myCircley;
        int l1 = myCircler / 3;
        arcRectangle.set(i - j, k - l, i1 + j1, k1 + l1);
        canvas.drawArc(arcRectangle, 0.0F, -1F * f, false, paintLineToPoint);
    }

    private void drawAxes(Canvas canvas)
    {
        int i;
        int j;
        int k;
        int l;
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        if (canvas.getWidth() < canvas.getHeight())
        {
            k = (int)(0.050000000000000003D * (double)canvas.getWidth());
            l = myCircley;
            i = (int)(0.94999999999999996D * (double)canvas.getWidth());
            j = myCircley;
        } else
        {
            k = (int)(0.050000000000000003D * (double)canvas.getHeight());
            l = myCircley;
            i = (int)(0.94999999999999996D * (double)canvas.getHeight());
            j = myCircley;
        }
        paintAxes.setStyle(android.graphics.Paint.Style.STROKE);
        canvas.drawLine(k, l, i, j, paintAxes);
        if (canvas.getWidth() < canvas.getHeight())
        {
            k = myCirclex;
            l = (int)(0.050000000000000003D * (double)canvas.getWidth());
            i1 = myCirclex;
            j1 = (int)(0.94999999999999996D * (double)canvas.getWidth());
        } else
        {
            k = myCirclex;
            l = (int)(0.050000000000000003D * (double)canvas.getHeight());
            i1 = myCirclex;
            j1 = (int)(0.94999999999999996D * (double)canvas.getHeight());
        }
        canvas.drawLine(k, l, i1, j1, paintAxes);
        if (canvas.getWidth() < canvas.getHeight())
        {
            i1 = (int)(0.029999999999999999D * (double)canvas.getWidth());
        } else
        {
            i1 = (int)(0.029999999999999999D * (double)canvas.getHeight());
        }
        j1 = (int)((double)myCirclex + (double)i1 * Math.cos(1.0471975511965976D));
        k1 = (int)((double)l + (double)i1 * Math.sin(1.0471975511965976D));
        l1 = (int)((double)myCirclex - (double)i1 * Math.cos(1.0471975511965976D));
        i2 = (int)((double)l + (double)i1 * Math.sin(1.0471975511965976D));
        paintCircle.setStyle(android.graphics.Paint.Style.STROKE);
        canvas.drawLine(j1, k1, k, l, paintAxes);
        canvas.drawLine(l1, i2, k, l, paintAxes);
        k = (int)((double)i - (double)i1 * Math.cos(0.52359877559829882D));
        l = (int)((double)j + (double)i1 * Math.sin(0.52359877559829882D));
        j1 = (int)((double)i - (double)i1 * Math.cos(0.52359877559829882D));
        i1 = (int)((double)j - (double)i1 * Math.sin(0.52359877559829882D));
        canvas.drawLine(k, l, i, j, paintAxes);
        canvas.drawLine(j1, i1, i, j, paintAxes);
    }

    private void drawBlackPoint(double d, Canvas canvas)
    {
        d = (3.1415926535897931D * d) / 180D;
        int i = (int)((double)myCirclex + (double)myCircler * Math.cos(d));
        int j = (int)((double)myCircley - (double)myCircler * Math.sin(d));
        canvas.drawCircle(i, j, 12F, paintPointOthers);
    }

    private void drawCoordinates(float f, Canvas canvas)
    {
        int j = 0;
        int k = 0;
        int i = 1;

        Options opts = new BitmapFactory.Options();

        DecimalFormat decimalformat = new DecimalFormat("000");
        (new StringBuilder("coord")).append(decimalformat.format(f)).toString();
        if (f != 0.0F && f != 90F && f != 180F && f != 270F)
        {
            double d;
            Bitmap bitmap;
            if (f == 30F)
            {
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.thirty);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);*/
            } else if (f == 45F)
            {
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fourtyfive);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);*/
            } else if (f == 60F)
            {
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sixty);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
                j = -10;
                k = -20;
            } /*else if (f == 90F)
            {
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.fourtyfive);
            }*/ else if (f == 120F)
            {
                i = 0;
                j = 5;
                k = -20;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.onehundredtwenty, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 135F)
            {
                i = 0;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.onehundredthirtyfive, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 150F)
            {
                i = 0;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.onehundredfifty, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } /*else if (f == 180F)
            {
                i = 0;
                bitmap = BitmapFactory.decodeResource(getResources(), 0x7f02004f);
            } */else if (f == 210F)
            {
                i = 0;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twohundredten, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 225F)
            {
                i = 0;
                j = -5;
                k = 5;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twohundredtwentyfive, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 240F)
            {
                i = 0;
                j = -5;
                k = 25;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.twohundredfourty, opts);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } /*else if (f == 270F)
            {
                bitmap = BitmapFactory.decodeResource(getResources(), 0x7f020053);
            }*/ else if (f == 300F)
            {
                j = -5;
                k = 25;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.threehundred);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 315F)
            {
                j = -5;
                k = 20;
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.threehundredfifteen);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else if (f == 330F)
            {
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.threehundredthirty);
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            } else
            {
                /*bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.threehundredthirty);//TODO CHANGE THIS
                bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);*/
            }
            d = Math.toRadians(f);
            /*if (i != 0)
            {
                i = j + ((int)((double)myCirclex + (double)myCircler * Math.cos(d)) + 35);
            } else
            {
                i = j + ((int)((double)myCirclex + (double)myCircler * Math.cos(d)) - bitmap.getWidth() - 35);
            }
            j = (int)((double)myCircley - (double)myCircler * Math.sin(d) - 40D);*/
            //canvas.drawBitmap(bitmap, i, k + j, null);
        }
    }


    private void drawQuadrantCoordinates(Canvas canvas)
    {
        int i;
        int j;
        int k;
        if (canvas.getWidth() < canvas.getHeight())
        {
            i = canvas.getWidth();
        } else
        {
            i = canvas.getHeight();
        }
        j = (int)(0.90000000000000002D * (double)i + 5D);
        k = myCircley;
        canvas.drawText("(1,0)", 0, "(1,0)".length(), j, k - 15, paintQuadCoordinates);
        j = myCirclex;
        k = (int)(0.10000000000000001D * (double)i - 15D);
        canvas.drawText("(0,1)", 0, "(0,1)".length(), j + 5, k, paintQuadCoordinates);
        j = (int)(0.10000000000000001D * (double)i + 5D);
        k = myCircley;
        canvas.drawText("(-1,0)", 0, "(-1,0)".length(), j, k - 15, paintQuadCoordinates);
        j = myCirclex;
        i = (int)(0.90000000000000002D * (double)i - 15D);
        canvas.drawText("(0,-1)", 0, "(0,-1)".length(), j + 5, i, paintQuadCoordinates);
    }

    private void drawRedLine(double d, Canvas canvas)
    {
        d = (3.1415926535897931D * d) / 180D;
        int i = (int)((double)myCirclex + (double)myCircler * Math.cos(d));
        int j = (int)((double)myCircley - (double)myCircler * Math.sin(d));
        paintLineToPoint.setStyle(android.graphics.Paint.Style.STROKE);
        canvas.drawLine(i, j, myCirclex, myCircley, paintLineToPoint);
    }

    private void drawRedPoint(double d, Canvas canvas)
    {
        d = (3.1415926535897931D * d) / 180D;
        int i = (int)((double)myCirclex + (double)myCircler * Math.cos(d));
        int j = (int)((double)myCircley - (double)myCircler * Math.sin(d));
        canvas.drawCircle(i, j, 12F, paintPoint);
    }

    private void drawSixTrigFunctions(float f, Canvas canvas)
    {
        String s;
        int i;
        int j;
        int k;
        int l;
        if (canvas.getWidth() < canvas.getHeight())
        {
            i = canvas.getWidth();
        } else
        {
            i = canvas.getHeight();
        }
        paintTrigFunctionsText.setColor(0xff000000);
        j = (int)(0.10000000000000001D * (double)i);
        i = (int)(1.0D * (double)i);
        canvas.drawText("sin \u03B8 = ", j, i, paintTrigFunctionsText);
        canvas.drawBitmap(getSineBmp(f), j + 100, i - 40, null);
        k = j + 200;
        canvas.drawText("csc \u03B8 = ", k, i, paintTrigFunctionsText);
        canvas.drawBitmap(getCosecantBmp(f), k + 100, i - 40, null);
        l = i + 75;
        canvas.drawText("cos \u03B8 = ", j, l, paintTrigFunctionsText);
        canvas.drawBitmap(getCosineBmp(f), j + 100, l - 40, null);
        canvas.drawText("sec \u03B8 = ", k, l, paintTrigFunctionsText);
        canvas.drawBitmap(getSecantBmp(f), k + 100, l - 40, null);
        l += 75;
        canvas.drawText("tan \u03B8 = ", j, l, paintTrigFunctionsText);
        canvas.drawBitmap(getTangentBmp(f), j + 100, l - 40, null);
        canvas.drawText("cot \u03B8 = ", k, l, paintTrigFunctionsText);
        canvas.drawBitmap(getCotangentBmp(f), k + 100, l - 40, null);
        s = getThetaText(f);
        paintTrigFunctionsText.setColor(0xffff0000);
        canvas.drawText(s, j, i - 75, paintTrigFunctionsText);
    }

    private void drawThetaText(float f, Canvas canvas)
    {
        String s = getThetaText(f);
        int i = (int)((double)myCirclex + 0.40000000000000002D * (double)myCircler);
        int j = (int)((double)myCircley - 0.10000000000000001D * (double)myCircler);
        int k = myCirclex;
        k = myCircler / 3;
        k = myCircley;
        k = myCircler / 3;
        canvas.drawText(s, 0, s.length(), i, j, paintTheta);
    }

    private void drawTriangleLegs(float f, Canvas canvas)
    {
        double d = Math.toRadians(f);
        int i = (int)((double)myCirclex + (double)myCircler * Math.cos(d));
        int j = (int)((double)myCircley - (double)myCircler * Math.sin(d));
        int k = getQuadrant(f);
        paintTriangleLeg.setColor(0xff0000ff);
        canvas.drawLine(myCirclex, myCircley, i, myCircley, paintTriangleLeg);
        paintTriangleLegText.setColor(0xff0000ff);
        if (k == 3 || k == 4)
        {
            canvas.drawText("x", (myCirclex + i) / 2, myCircley - 15, paintTriangleLegText);
        } else
        {
            canvas.drawText("x", (myCirclex + i) / 2, myCircley + 25, paintTriangleLegText);
        }
        paintTriangleLeg.setColor(Color.rgb(0, 102, 0));
        canvas.drawLine(i, myCircley, i, j, paintTriangleLeg);
        paintTriangleLegText.setColor(Color.rgb(0, 102, 0));
        if (k == 2 || k == 3)
        {
            canvas.drawText("y", i - 25, (myCircley + j) / 2, paintTriangleLegText);
            return;
        } else
        {
            canvas.drawText("y", i + 20, (myCircley + j) / 2, paintTriangleLegText);
            return;
        }
    }


    public void drawUnitCircle(Canvas canvas) {
        if (canvas.getWidth() < canvas.getHeight())
        {
            myCirclex = canvas.getWidth() / 2;
            myCircley = canvas.getWidth() / 2;
            myCircler = (int)((double)(canvas.getWidth() / 2) * 0.80000000000000004D);
        } else
        {
            myCirclex = canvas.getHeight() / 2;
            myCircley = canvas.getHeight() / 2;
            myCircler = (int)((double)(canvas.getHeight() / 2) * 0.80000000000000004D);
        }

        if(paintCircle==null){
            System.out.println("Paint Circle Null");
        }else {
            paintCircle.setStyle(android.graphics.Paint.Style.STROKE);
            canvas.drawCircle(myCirclex, myCircley, myCircler, paintCircle);
        }
    }

    private float getDistanceClicked(float f, float f1, float f2, float f3)
    {
        return (float)Math.sqrt(Math.pow(f - f2, 2D) + Math.pow(f1 - f3, 2D));
    }

    private void init()
    {
        canvasPaint = new Paint(4);
        theta = 45F;
        paintCircle = new Paint();
        paintCircle.setColor(0xff000000);
        paintCircle.setStrokeWidth(7F);
        paintCircle.setStyle(android.graphics.Paint.Style.STROKE);
        paintAxes = new Paint();
        paintAxes.setColor(0xff000000);
        paintAxes.setStrokeWidth(5F);
        paintAxes.setStyle(android.graphics.Paint.Style.STROKE);
        paintTriangleLeg = new Paint();
        paintTriangleLeg.setStrokeWidth(7F);
        paintTriangleLeg.setStyle(android.graphics.Paint.Style.STROKE);
        paintTriangleLegText = new Paint();
        paintTriangleLegText.setColor(0xff0000ff);
        paintTriangleLegText.setTextSize(30F);
        paintTriangleLegText.setStyle(android.graphics.Paint.Style.FILL);
        paintPoint = new Paint();
        paintPoint.setColor(0xffff0000);
        paintPoint.setStrokeWidth(3F);
        paintPoint.setStyle(android.graphics.Paint.Style.FILL);
        paintPointOthers = new Paint();
        paintPointOthers.setColor(0xff000000);
        paintPointOthers.setStrokeWidth(3F);
        paintPointOthers.setStyle(android.graphics.Paint.Style.FILL);
        paintLineToPoint = new Paint();
        paintLineToPoint.setColor(0xffff0000);
        paintLineToPoint.setStrokeWidth(7F);
        paintLineToPoint.setStyle(android.graphics.Paint.Style.STROKE);
        paintQuadCoordinates = new Paint();
        paintQuadCoordinates.setColor(0xff000000);
        paintQuadCoordinates.setTextSize(30F);
        paintQuadCoordinates.setStyle(android.graphics.Paint.Style.FILL);
        arcRectangle = new RectF();
        paintTheta = new Paint();
        paintTheta.setColor(0xffff0000);
        paintTheta.setTextSize(36F);
        paintTheta.setStyle(android.graphics.Paint.Style.FILL);
        paintTrigFunctionsText = new Paint();
        paintTrigFunctionsText.setColor(0xff000000);
        paintTrigFunctionsText.setTextSize(30F);
        paintTrigFunctionsText.setStyle(android.graphics.Paint.Style.FILL);
    }

    public float getAngleTouched(float f, float f1)
    {
        float f2 = -1F;
        boolean flag = false;
        int ai[] = new int[16];
        ai[1] = 30;
        ai[2] = 45;
        ai[3] = 60;
        ai[4] = 90;
        ai[5] = 120;
        ai[6] = 135;
        ai[7] = 150;
        ai[8] = 180;
        ai[9] = 210;
        ai[10] = 225;
        ai[11] = 240;
        ai[12] = 270;
        ai[13] = 300;
        ai[14] = 315;
        ai[15] = 330;
        int i = 0;
        do
        {
            if (flag || i >= ai.length)
            {
                return f2;
            }
            int j = ai[i];
            if ((double)getDistanceClicked(getMyPt_x1(j), getMyPt_y1(j), f, f1) <= 50D)
            {
                flag = true;
                f2 = j;
            } else
            {
                flag = false;
            }
            i++;
        } while (true);
    }

    public int getMyPt_x1(float f)
    {
        double d = ((double)f * 3.1415926535897931D) / 180D;
        return (int)((double)myCirclex + (double)myCircler * Math.cos(d));
    }

    public int getMyPt_y1(float f)
    {
        double d = ((double)f * 3.1415926535897931D) / 180D;
        return (int)((double)myCircley - (double)myCircler * Math.sin(d));
    }

    public int getQuadrant(float f)
    {
        if (f > 0.0F && f < 90F)
        {
            return 1;
        }
        if (f > 90F && f < 180F)
        {
            return 2;
        }
        if (f > 180F && f < 270F)
        {
            return 3;
        }
        return f <= 270F || f >= 360F ? 0 : 4;
    }

    public Bitmap getSineBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F || f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 30F || f == 150F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
            return bitmap;
        }
        if (f == 45F || f == 135F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 60F || f == 120F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 90F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
            return bitmap;
        }
        if (f == 210F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin210);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 225F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin225);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 240F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin240);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin270);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sin0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
    }

    public Bitmap getCosineBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
            return bitmap;
        }
        if (f == 30F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 45F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 60F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 90F || f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
            return bitmap;
        }
        if (f == 120F || f == 240F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos120);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 135F || f == 225F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos135);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 150F || f == 210F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos150);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
        if (f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos180);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 2, bitmap.getHeight() / 2, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cos0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth() / 3, bitmap.getHeight() / 3, false);
            return bitmap;
        }
    }

    public Bitmap getTangentBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F || f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 30F || f == 210F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
            return bitmap;
        }
        if (f == 45F || f == 225F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 60F || f == 240F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 90F || f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 120F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan120);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 135F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan135);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 150F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan150);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tan0); //TODO: this might be wrong
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
    }

    public Bitmap getCosecantBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F || f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 30F || f == 150F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 45F || f == 135F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 60F || f == 120F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/3, bitmap.getHeight()/3, false);
            return bitmap;
        }
        if (f == 90F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 210F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc210);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 225F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc225);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 240F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc240);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc270);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.csc0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
    }


    public Bitmap getCotangentBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F || f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 30F || f == 210F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 45F || f == 225F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 60F || f == 240F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 90F || f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 120F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot120);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 135F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot135);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 150F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot150);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.cot0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
    }

    public Bitmap getSecantBmp(float f)
    {
        Bitmap bitmap;
        if (f == 0.0F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 30F || f == 330F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec30);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 45F || f == 315F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec45);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 60F || f == 300F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec60);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 90F || f == 270F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec90);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 120F || f == 240F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec120);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 135F || f == 225F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec135);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 150F || f == 210F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec150);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
        if (f == 180F)
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec180);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        } else
        {
            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sec0);
            bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth()/2, bitmap.getHeight()/2, false);
            return bitmap;
        }
    }

    public String getThetaText(float f)
    {
        String s = (new StringBuilder("\u03B8 = ")).append((int)f).append("\260 = ").toString();
        if (f == 0.0F)
        {
            return (new StringBuilder(String.valueOf(s))).append("0").toString();
        }
        if (f == 90F)
        {
            return (new StringBuilder(String.valueOf(s))).append("\u03C0/2").toString();
        }
        if (f == 180F)
        {
            return (new StringBuilder(String.valueOf(s))).append("\u03C0").toString();
        }
        if (f == 270F)
        {
            return (new StringBuilder(String.valueOf(s))).append("3\u03C0/2").toString();
        }
        if (f == 360F)
        {
            return (new StringBuilder(String.valueOf(s))).append("2\u03C0").toString();
        }
        if (f == 45F)
        {
            return (new StringBuilder(String.valueOf(s))).append("\u03C0/4").toString();
        }
        if (f == 135F)
        {
            return (new StringBuilder(String.valueOf(s))).append("3\u03C0/4").toString();
        }
        if (f == 225F)
        {
            return (new StringBuilder(String.valueOf(s))).append("5\u03C0/4").toString();
        }
        if (f == 315F)
        {
            return (new StringBuilder(String.valueOf(s))).append("7\u03C0/4").toString();
        }
        if (f == 30F)
        {
            return (new StringBuilder(String.valueOf(s))).append("\u03C0/6").toString();
        }
        if (f == 150F)
        {
            return (new StringBuilder(String.valueOf(s))).append("5\u03C0/6").toString();
        }
        if (f == 210F)
        {
            return (new StringBuilder(String.valueOf(s))).append("9\u03C0/6").toString();
        }
        if (f == 330F)
        {
            return (new StringBuilder(String.valueOf(s))).append("11\u03C0/6").toString();
        }
        if (f == 60F)
        {
            return (new StringBuilder(String.valueOf(s))).append("\u03C0/3").toString();
        }
        if (f == 120F)
        {
            return (new StringBuilder(String.valueOf(s))).append("2\u03C0/3").toString();
        }
        if (f == 240F)
        {
            return (new StringBuilder(String.valueOf(s))).append("4\u03C0/3").toString();
        }
        if (f == 300F)
        {
            return (new StringBuilder(String.valueOf(s))).append("5\u03C0/3").toString();
        } else
        {
            return "error";
        }
    }


    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(canvasBitmap, 0.0F, 0.0F, canvasPaint);
        drawUnitCircle(canvas);
        drawAxes(canvas);
        drawQuadrantCoordinates(canvas);
        drawBlackPoint(0.0D, canvas);
        drawBlackPoint(30D, canvas);
        drawBlackPoint(45D, canvas);
        drawBlackPoint(60D, canvas);
        drawBlackPoint(90D, canvas);
        drawBlackPoint(120D, canvas);
        drawBlackPoint(135D, canvas);
        drawBlackPoint(150D, canvas);
        drawBlackPoint(180D, canvas);
        drawBlackPoint(210D, canvas);
        drawBlackPoint(225D, canvas);
        drawBlackPoint(240D, canvas);
        drawBlackPoint(270D, canvas);
        drawBlackPoint(300D, canvas);
        drawBlackPoint(315D, canvas);
        drawBlackPoint(330D, canvas);
        drawRedPoint(theta, canvas);
        drawRedLine(theta, canvas);
        drawArc(theta, canvas);
        drawCoordinates(theta, canvas);
        drawTriangleLegs(theta, canvas);
        drawThetaText(theta, canvas);
        drawRedPoint(theta, canvas);
        myCanvasHeight = canvas.getHeight();
        drawSixTrigFunctions(theta, canvas);
    }

    protected void onSizeChanged(int i, int j, int k, int l)
    {
        super.onSizeChanged(i, j, k, l);
        canvasBitmap = Bitmap.createBitmap(i, j, android.graphics.Bitmap.Config.ARGB_8888);
        circleCanvas = new Canvas(canvasBitmap);
    }

    public boolean onTouchEvent(MotionEvent motionevent) {
        float f = motionevent.getX();
        float f1 = motionevent.getY();
        switch (motionevent.getAction()) {
            default:
                return false;

            case 0: // '\0'
                circleCanvas.drawColor(0, android.graphics.PorterDuff.Mode.CLEAR);
                invalidate();
                float f2 = getAngleTouched(f, f1);
                if (f2 != -1F) {
                    theta = f2;
                }
                (new StringBuilder("xt=")).append(f).append("  yt=").append(f1).append("  angle = ").append(theta).append("  angle = ").append(f2).toString();
                // fall through

            case 1: // '\001'
            case 2: // '\002'
                invalidate();
                return true;
        }
    }
}
