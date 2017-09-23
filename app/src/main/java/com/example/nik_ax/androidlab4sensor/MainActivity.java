package com.example.nik_ax.androidlab4sensor;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{
    TextView aX;
    TextView aY;
    TextView aZ;
    TextView mX;
    TextView mY;
    TextView mZ;
    TextView proximity;
    TextView light;
    TextView brtns;
    SensorManager sensorManager;
    Sensor aSensor;
    Sensor pSensor;
    Sensor mSensor;
    Sensor lSensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Sensor Lab4");
        aX = (TextView)findViewById(R.id.textView2);
        aY = (TextView)findViewById(R.id.textView3);
        aZ = (TextView)findViewById(R.id.textView4);
        mX = (TextView)findViewById(R.id.textView6);
        mY = (TextView)findViewById(R.id.textView7);
        mZ = (TextView)findViewById(R.id.textView8);
        proximity = (TextView)findViewById(R.id.textView10);
        light = (TextView)findViewById(R.id.textView12);
        brtns = (TextView)findViewById(R.id.textView14);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        aSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        pSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        lSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
// TODO Auto-generated method stub
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            aX.setText(Float.toString(event.values[0]));
            aY.setText(Float.toString(event.values[1]));
            aZ.setText(Float.toString(event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            mX.setText(Float.toString(event.values[0]));
            mY.setText(Float.toString(event.values[1]));
            mZ.setText(Float.toString(event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            proximity.setText(Float.toString(event.values[0]));
        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){ // инд. задание
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            Float brightness = event.values[0];
            layout.screenBrightness = (brightness < 50) ? 0.1F : (brightness > 100) ? 0.7F : 0.9F;
            getWindow().setAttributes(layout);
            brtns.setText(Float.toString(layout.screenBrightness));
            light.setText(Float.toString(event.values[0]));
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    public void onStart(){
        super.onStart();
        sensorManager.registerListener(this, aSensor, sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, mSensor,sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, pSensor,sensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, lSensor,sensorManager.SENSOR_DELAY_FASTEST);
    }
    @Override
    public void onStop(){
        super.onStop();
        sensorManager.unregisterListener(this, aSensor);
        sensorManager.unregisterListener(this, mSensor);
        sensorManager.unregisterListener(this, pSensor);
        sensorManager.unregisterListener(this, lSensor);
    }

}
