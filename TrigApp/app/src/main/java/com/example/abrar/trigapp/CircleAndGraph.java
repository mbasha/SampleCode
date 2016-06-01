package com.example.abrar.trigapp;

/*import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;
import android.graphics.*;
import org.achartengine.chart.*;*/

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


/**
 * Created by abrar on 3/28/16.
 */
public class CircleAndGraph extends Activity {

    private static final String TAG = "SohCahToa";
    private EditText aEditText;
    private Editable aField;
    private double adjacentValue;
    private EditText angleEditText;
    private Editable angleField;
    private double angleValue;
    private Button clearButton;
    private Button computeButton;
    private Context context;
    private boolean degrees;
    private EditText hEditText;
    private Editable hField;
    private double hypotenuseValue;
    private Button infoButton;
    private EditText oEditText;
    private Editable oField;
    private double oppositeValue;
    private Button quitButton;
    private boolean radians;
    private boolean rightAngle;
    private int selectionCount;

    class C00321 implements OnClickListener {
        C00321() {
        }

        public void onClick(View view) {
            CircleAndGraph.this.clearValues();
        }
    }

    class C00365 implements DialogInterface.OnClickListener {
        C00365() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    class C00376 implements DialogInterface.OnClickListener {
        C00376() {
        }

        public void onClick(DialogInterface dialog, int id) {
            dialog.cancel();
        }
    }

    class C00354 implements OnClickListener {
        C00354() {
        }

        public void onClick(View view) {
            CircleAndGraph.this.getValues();
            CircleAndGraph.this.doCompute();
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_and_graph_layout);
        super.onCreate(savedInstanceState);
        this.oEditText = (EditText) findViewById(R.id.oValue);
        this.oField = this.oEditText.getText();
        this.aEditText = (EditText) findViewById(R.id.aValue);
        this.aField = this.aEditText.getText();
        this.hEditText = (EditText) findViewById(R.id.hValue);
        this.hField = this.hEditText.getText();
        this.angleEditText = (EditText) findViewById(R.id.angleValue);
        this.angleField = this.angleEditText.getText();
        this.clearButton = (Button) findViewById(R.id.clearButton);
        this.clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleAndGraph.this.clearValues();
            }
        });
        this.computeButton = (Button) findViewById(R.id.computeButton);
        this.computeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CircleAndGraph.this.getValues();
                CircleAndGraph.this.doCompute();
            }
        });
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        /*LinearLayout layout=(LinearLayout)findViewById(R.id.chart);
        if(myChart==null)
        {
            initializeChart();
            addData();
            myChart=ChartFactory.getLineChartView(this, mySeries, myRenderer);
            layout.addView(myChart);
        }
        else
        {
            myChart.repaint();
        }*/
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        return false;
    }

    private void getValues() {
        this.selectionCount = 0;
        if (!TextUtils.isEmpty(this.oEditText.getText().toString())) {
            this.oppositeValue = Double.parseDouble(this.oEditText.getText().toString());
            this.selectionCount++;
        }
        if (!TextUtils.isEmpty(this.aEditText.getText().toString())) {
            this.adjacentValue = Double.parseDouble(this.aEditText.getText().toString());
            this.selectionCount++;
        }
        if (!TextUtils.isEmpty(this.hEditText.getText().toString())) {
            this.hypotenuseValue = Double.parseDouble(this.hEditText.getText().toString());
            this.selectionCount++;
        }
        if (!TextUtils.isEmpty(this.angleEditText.getText().toString())) {
            this.angleValue = Double.parseDouble(this.angleEditText.getText().toString());
            this.selectionCount++;
        }
    }

    private void doCompute() {
        if (!checkRules()) {
            if (this.oppositeValue != 0.0d && this.adjacentValue != 0.0d) {
                this.hypotenuseValue = Math.sqrt((this.oppositeValue * this.oppositeValue) + (this.adjacentValue * this.adjacentValue));
                this.angleValue = (Math.acos(this.adjacentValue / this.hypotenuseValue) * 180.0d) / 3.141592653589793d;
                this.angleValue = ((double) Math.round(this.angleValue * 100.0d)) / 100.0d;
                this.angleEditText.setText(Double.toString(this.angleValue));
                this.hypotenuseValue = ((double) Math.round(this.hypotenuseValue * 100.0d)) / 100.0d;
                this.hEditText.setText(Double.toString(this.hypotenuseValue));
            } else if (this.oppositeValue != 0.0d && this.hypotenuseValue != 0.0d) {
                this.angleValue = Math.asin(this.oppositeValue / this.hypotenuseValue);
                this.adjacentValue = this.oppositeValue / Math.tan(this.angleValue);
                this.angleValue = Math.toDegrees(this.angleValue);
                this.angleValue = ((double) Math.round(this.angleValue * 100.0d)) / 100.0d;
                this.angleEditText.setText(Double.toString(this.angleValue));
                this.adjacentValue = ((double) Math.round(this.adjacentValue * 100.0d)) / 100.0d;
                this.aEditText.setText(Double.toString(this.adjacentValue));
            } else if (this.oppositeValue != 0.0d && this.angleValue != 0.0d) {
                this.adjacentValue = this.oppositeValue / Math.tan(Math.toRadians(this.angleValue));
                this.hypotenuseValue = this.adjacentValue / Math.cos(Math.toRadians(this.angleValue));
                this.hypotenuseValue = ((double) Math.round(this.hypotenuseValue * 100.0d)) / 100.0d;
                this.hEditText.setText(Double.toString(this.hypotenuseValue));
                this.adjacentValue = ((double) Math.round(this.adjacentValue * 100.0d)) / 100.0d;
                this.aEditText.setText(Double.toString(this.adjacentValue));
            } else if (this.adjacentValue != 0.0d && this.hypotenuseValue != 0.0d) {
                this.angleValue = Math.acos(this.adjacentValue / this.hypotenuseValue);
                this.angleValue = Math.toDegrees(this.angleValue);
                this.angleValue = ((double) Math.round(this.angleValue * 100.0d)) / 100.0d;
                this.angleEditText.setText(Double.toString(this.angleValue));
                this.oppositeValue = this.hypotenuseValue * Math.sin(Math.toRadians(this.angleValue));
                this.oppositeValue = ((double) Math.round(this.oppositeValue * 100.0d)) / 100.0d;
                this.oEditText.setText(Double.toString(this.oppositeValue));
            } else if (this.adjacentValue != 0.0d && this.angleValue != 0.0d) {
                this.hypotenuseValue = this.adjacentValue / Math.cos(Math.toRadians(this.angleValue));
                this.hypotenuseValue = ((double) Math.round(this.hypotenuseValue * 100.0d)) / 100.0d;
                this.hEditText.setText(Double.toString(this.hypotenuseValue));
                this.oppositeValue = this.hypotenuseValue * Math.sin(Math.toRadians(this.angleValue));
                this.oppositeValue = ((double) Math.round(this.oppositeValue * 100.0d)) / 100.0d;
                this.oEditText.setText(Double.toString(this.oppositeValue));
            } else if (this.angleValue != 0.0d && this.hypotenuseValue != 0.0d) {
                this.oppositeValue = this.hypotenuseValue * Math.sin(Math.toRadians(this.angleValue));
                this.oppositeValue = ((double) Math.round(this.oppositeValue * 100.0d)) / 100.0d;
                this.oEditText.setText(Double.toString(this.oppositeValue));
                this.adjacentValue = this.oppositeValue / Math.tan(Math.toRadians(this.angleValue));
                this.adjacentValue = ((double) Math.round(this.adjacentValue * 100.0d)) / 100.0d;
                this.aEditText.setText(Double.toString(this.adjacentValue));
            }
        }
    }

    private boolean checkRules() {
        if (this.selectionCount > 2) {
            showError();
            return true;
        } else if (this.oppositeValue != 0.0d && this.hypotenuseValue != 0.0d && this.oppositeValue >= this.hypotenuseValue) {
            showError();
            return true;
        } else if (this.adjacentValue != 0.0d && this.hypotenuseValue != 0.0d && this.adjacentValue == this.hypotenuseValue) {
            showError();
            return true;
        } else if (this.rightAngle && this.hypotenuseValue != 0.0d && (this.oppositeValue > this.hypotenuseValue || this.adjacentValue > this.hypotenuseValue)) {
            showError();
            return true;
        } else if (this.angleValue < 90.0d) {
            return false;
        } else {
            showError();
            return true;
        }
    }

    private void showError() {
        clearValues();
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Error");
        builder.setMessage("Enter only two values and select compute. In right-angled triangles " +
                "the opposite or adjacent can't be bigger than the hypotenuse.");
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // do nothing
            }
        });
        builder.setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog = builder.create();
        dialog.show();
        /*Builder alertDialogBuilder = new Builder(this.context);
        alertDialogBuilder.setTitle("Error!");
        alertDialogBuilder.setMessage("Enter ONLY two values and select compute.  With the angle being less than 90 degrees, the opposite or adjacent can't be the same as the hypotenuse.  In right-angled triangles the opposite or adjacent can't be bigger than the hypotenuse.").setCancelable(false).setNegativeButton("Close", new C00365());
        alertDialogBuilder.create().show();*/
    }



    private void clearValues() {
        this.oEditText.setText(BuildConfig.FLAVOR);
        this.aEditText.setText(BuildConfig.FLAVOR);
        this.hEditText.setText(BuildConfig.FLAVOR);
        this.angleEditText.setText(BuildConfig.FLAVOR);
        this.oppositeValue = 0.0d;
        this.adjacentValue = 0.0d;
        this.hypotenuseValue = 0.0d;
        this.angleValue = 0.0d;
    }
}
