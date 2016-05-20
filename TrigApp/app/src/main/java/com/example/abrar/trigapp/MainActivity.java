package com.example.abrar.trigapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button unitCircleButton = (Button) findViewById(R.id.unitCircle);
        Button circleAndGraphButton =(Button) findViewById(R.id.circleAndGraph);
        Button graphAndInverseButton = (Button) findViewById(R.id.graphAndInverse);

        unitCircleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                //startActivity(new Intent(MainActivity.this, UnitCircle.class));
                startActivity(new Intent(MainActivity.this, UnitCircle.class));
            }
        });

        circleAndGraphButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CircleAndGraph.class));
            }
        });

        graphAndInverseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GraphAndInverse.class));
            }
        });

        /*bundle = (CircleDraw)findViewById(0x7f090041); //fix this
        bundle = Toast.makeText(this, getResources().getString(0x7f0a0011), 1); //fix this
        bundle.setGravity(17, 0, 0);
        bundle.show();

        ((ImageButton)findViewById(savedInstanceState)).setOnClickListener(new android.view.View.OnClickListener() {

            final MainActivity this;

            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, com / kiskiarea / theunitcircle2 / InfoScreen));
            }


            {
                this = MainActivity.this;
                super();
            }
        });*/
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }
}
