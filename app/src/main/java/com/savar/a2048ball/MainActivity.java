package com.savar.a2048ball;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener2;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends Activity implements SensorEventListener2
{
    public static ArrayList<Float> xPos=new ArrayList<Float>();
    private ArrayList<Float> xVel=new ArrayList<Float>();
    public static ArrayList<Float> yPos=new ArrayList<Float>();
    private ArrayList<Float> yVel=new ArrayList<Float>();
    public static ArrayList<Float> radius=new ArrayList<Float>();

    public static ArrayList<Integer> type=new ArrayList<Integer>();
    public static ArrayList<Integer> start=new ArrayList<Integer>();

    private Random random=new Random();
    private int[][] Location;

    private float xAccel, yAccel;
    public static float xMax, yMax;

    private float xS, yS;

    private SensorManager sensorManager;

    private boolean flag=false;

    LinearLayout screen;
    Handler handler = new Handler();
    int R1=255;
    int G1=255;
    int B1=255;
    int R2=0;
    int G2=0;
    int B2=0;
    int flaggg;
    Random rand=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        final BallView ballView = new BallView(this);
        ballView.setBackgroundResource(R.color.colorBallView);
        setContentView(ballView);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        R1=rand.nextInt((255 - 0) + 1) + 0;
        G1=rand.nextInt((255 - 0) + 1) + 0;
        B1=rand.nextInt((255 - 0) + 1) + 0;
        R2=rand.nextInt((255 - 0) + 1) + 0;
        G2=rand.nextInt((255 - 0) + 1) + 0;
        B2=rand.nextInt((255 - 0) + 1) + 0;

        (new Thread()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    handler.post(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            ObjectAnimator colorFade = ObjectAnimator.ofObject(ballView, "backgroundColor", new ArgbEvaluator(), Color.argb(255, R1, G1, B1), Color.argb(255, R2, G2, B2));
                            colorFade.setDuration(7000);
                            colorFade.start();
                        }
                    });
                    // next will pause the thread for some time
                    try
                    {
                        sleep(10000);
                    }
                    catch (InterruptedException e)
                    {
                        break;
                    }
                    flaggg=R1;
                    R1=R2;
                    R2=flaggg;

                    flaggg=G1;
                    G1=G2;
                    G2=flaggg;

                    flaggg=B1;
                    B1=B2;
                    B2=flaggg;

                    R2=rand.nextInt((255 - 0) + 1) + 0;
                    G2=rand.nextInt((255 - 0) + 1) + 0;
                    B2=rand.nextInt((255 - 0) + 1) + 0;
                }
            }
        }).start();

        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        xMax = (float) size.x;
        yMax = (float) size.y;

        Location=new int[(int) xMax+1][(int) yMax+1];

        int xRandom=random.nextInt((int) xMax-99)+50;
        int yRandom=random.nextInt((int) yMax-99)+50;
        for (int n=xRandom-50;n<=xRandom+50;n++)
        {
            double a=Math.pow(n-xRandom,2);
            double r=2500.0;
            double b=Math.sqrt(r-a);
            Log.i("shaho",xRandom+"///"+yRandom+"///"+a+"///"+n+"///////"+b);
            for (int u = (int) (yRandom-b); u<=yRandom+b; u++)
                Location[n][u]=1;
        }

        xPos.add((float) xRandom);
        xVel.add(0.0f);
        yPos.add((float) yRandom);
        yVel.add(0.0f);
        radius.add((float) 50);
        type.add(2);
        start.add(1);

        while (true)
        {
            xRandom=random.nextInt((int) xMax-99)+50;
            yRandom=random.nextInt((int) yMax-99)+50;

            if (Location[xRandom][yRandom]==0)
            {
                float xDif = xPos.get(0) - xRandom;
                float yDif = yPos.get(0) - yRandom;
                float distanceSquared = (xDif * xDif) + (yDif * yDif);
                boolean collosion = distanceSquared < (radius.get(0) + 50) * (radius.get(0) + 50);

                if (!collosion)
                    break;
            }
        }
        for (int n=xRandom-50;n<=xRandom+50;n++)
        {
            double a=Math.pow(n-xRandom,2);
            double r=2500.0;
            double b=Math.sqrt(r-a);
            Log.i("shaho",n+"///////"+b);
            for (int u = (int) (yRandom-b);u<=yRandom+b;u++)
                Location[n][u]=1;
        }

        xPos.add((float) xRandom);
        xVel.add(0.0f);
        yPos.add((float) yRandom);
        yVel.add(0.0f);
        radius.add((float) 50);
        type.add(2);
        start.add(1);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop()
    {
        sensorManager.unregisterListener(this);
        super.onStop();
    }

    @Override
    public void onFlushCompleted(Sensor sensor)
    {

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent)
    {
        if (sensorEvent.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
        {
            xAccel=sensorEvent.values[0];
            yAccel=-sensorEvent.values[1];

            for (int i=0;i<xPos.size();i++)
                updateBall(i,radius.get(i));
        }
    }

    private void updateBall(int i,float Radius)
    {
//        float frameTime = 1;

        xVel.set(i,xVel.get(i)+xAccel);
        xS = xVel.get(i) / 2;
        xPos.set(i,xPos.get(i)-xS);

        yVel.set(i,yVel.get(i)+yAccel);
        yS = yVel.get(i) / 2;
        yPos.set(i,yPos.get(i)-yS);


        if (xPos.get(i) > xMax-Radius)
        {
            xPos.set(i,xMax-Radius);

            xVel.set(i,0.0f);
        }
        else if (xPos.get(i) < Radius)
        {
            xPos.set(i,Radius);

            xVel.set(i,0.0f);
        }

        if (yPos.get(i) > yMax-Radius)
        {
            yPos.set(i,yMax-Radius);

            yVel.set(i,0.0f);
        }
        else if (yPos.get(i) < Radius)
        {
            yPos.set(i,Radius);

            yVel.set(i,0.0f);
        }

        Collision(i);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i)
    {

    }

    public void Collision(int i)
    {
        boolean flagCollosion=true;
        for (int x=0;x<xPos.size();x++)
            if (x!=i && i<xPos.size())
            {
                float xDif = xPos.get(i) - xPos.get(x);
                float yDif = yPos.get(i) - yPos.get(x);
                float distanceSquared = (xDif * xDif) + (yDif * yDif);
                boolean collosion = distanceSquared < (radius.get(i) + radius.get(x)) * (radius.get(i) + radius.get(x));

                // وقتی دو توپ غیر هم امتیاز به هم برخورد می کنند
                if(collosion && type.get(i) != type.get(x) && flagCollosion)
                {
                    flagCollosion=false;
                    xPos.set(i, xPos.get(i) + xS);
                    yPos.set(i, yPos.get(i) + yS);

                    xVel.set(i, 0.0f);
                    yVel.set(i, 0.0f);
                }

                // وقتی دو توپ هم امتیاز به هم برخورد می کنند
                if (collosion && type.get(i).compareTo(type.get(x))==0)
                {
                    if (type.get(i)==2)
                        flag=true;

                    float xFlag=(xPos.get(i)+xPos.get(x))/2;
                    float yFlag=(yPos.get(i)+yPos.get(x))/2;
                    int typeFlag=type.get(i)+type.get(x);

                    if (i>x)
                    {
                        xPos.remove(i);
                        xVel.remove(i);
                        yPos.remove(i);
                        yVel.remove(i);
                        radius.remove(i);
                        type.remove(i);
                        start.remove(i);

                        xPos.remove(x);
                        xVel.remove(x);
                        yPos.remove(x);
                        yVel.remove(x);
                        radius.remove(x);
                        type.remove(x);
                        start.remove(x);
                    }
                    else
                    {
                        xPos.remove(x);
                        xVel.remove(x);
                        yPos.remove(x);
                        yVel.remove(x);
                        radius.remove(x);
                        type.remove(x);
                        start.remove(x);

                        xPos.remove(i);
                        xVel.remove(i);
                        yPos.remove(i);
                        yVel.remove(i);
                        radius.remove(i);
                        type.remove(i);
                        start.remove(i);
                    }

                    xPos.add(xFlag);
                    xVel.add(0.0f);
                    yPos.add(yFlag);
                    yVel.add(0.0f);
                    final int n=typeFlag/2;
                    radius.add((float) (49+n));
                    type.add(typeFlag);
                    start.add(1);

                    Thread thread=new Thread()
                    {
                        @Override
                        public void run()
                        {
                            boolean out=true;
                            while (out)
                            {
                                for (int[] row : Location)
                                    Arrays.fill(row,0);

                                out=false;

                                for (int w = 0; w < xPos.size(); w++)
                                {
                                    if (w!=xPos.size()-1)
                                    {
                                        float xDif2 = xPos.get(w) - xPos.get(xPos.size()-1);
                                        float yDif2 = yPos.get(w) - yPos.get(xPos.size()-1);
                                        float distanceSquared2 = (xDif2 * xDif2) + (yDif2 * yDif2);
                                        boolean collosion2 = distanceSquared2 < (radius.get(w) + radius.get(xPos.size()-1)) * (radius.get(w) + radius.get(xPos.size()-1));

                                        if (collosion2)
                                        {
                                            
                                        }
                                    }

                                    int count1=0;
                                    int count2=0;
                                    for (int num = (int) (xPos.get(w)-radius.get(w)+n-1); num<=xPos.get(w)+radius.get(w)-n+1; num++)
                                    {
                                        double a=Math.pow(num-xPos.get(w),2);
                                        double r=Math.pow(radius.get(w),2);
                                        double b=Math.sqrt(r-a);
                                        Log.i("shaho",num+"///////"+b);
                                        if (num<0)
                                            count1=n-1;
                                        else if (num>xMax)
                                            count1=1-n;
                                        for (int u = (int) (yPos.get(w)-b); u<=yPos.get(w)+b; u++)
                                        {
                                            if (u<0)
                                                count2=n-1;
                                            else if (u>yMax)
                                                count2=1-n;
                                            Location[num + count1][u + count2] = 1;
                                        }
                                    }
                                }
                            }

                        }
                    };
//                    boolean out=true;
//                    while (out)
//                    {
//                        for (int[] row : Location)
//                            Arrays.fill(row,0);
//
//                        out=false;
//
//                        for (int w = 0; w < xPos.size(); w++)
//                        {
//                            if (w!=xPos.size()-1)
//                            {
//                                float xDif2 = xPos.get(w) - xPos.get(xPos.size()-1);
//                                float yDif2 = yPos.get(w) - yPos.get(xPos.size()-1);
//                                float distanceSquared2 = (xDif2 * xDif2) + (yDif2 * yDif2);
//                                boolean collosion2 = distanceSquared2 < (radius.get(w) + radius.get(xPos.size()-1)) * (radius.get(w) + radius.get(xPos.size()-1));
//
//                                if (collosion2)
//                                {
//
//                                }
//                            }
//
//                            // شانسی :|
//                            int count1=0;
//                            int count2=0;
//                            for (int num = (int) (xPos.get(w)-radius.get(w)+n-1); num<=xPos.get(w)+radius.get(w)-n+1; num++)
//                            {
//                                double a=Math.pow(num-xPos.get(w),2);
//                                double r=Math.pow(radius.get(w),2);
//                                double b=Math.sqrt(r-a);
//                                Log.i("shaho",num+"///////"+b);
//                                if (num<0)
//                                    count1=n-1;
//                                else if (num>xMax)
//                                    count1=1-n;
//                                for (int u = (int) (yPos.get(w)-b); u<=yPos.get(w)+b; u++)
//                                {
//                                    if (u<0)
//                                        count2=n-1;
//                                    else if (u>yMax)
//                                        count2=1-n;
//                                    Location[num + count1][u + count2] = 1;
//                                }
//                            }
//                        }
//                    }

                    if (flag) //زمانی که دو تا 2 به هم میخورند
                    {
                        int xRandom;
                        int yRandom;
                        while (true)
                        {
                            xRandom=random.nextInt((int) xMax-99)+50;
                            yRandom=random.nextInt((int) yMax-99)+50;

                            if (Location[xRandom][yRandom]==0)
                            {
                                boolean j=false;
                                for (int s=0;s<xPos.size();s++)
                                {
                                    float xDif3 = xPos.get(s) - xRandom;
                                    float yDif3 = yPos.get(s) - yRandom;
                                    float distanceSquared3 = (xDif3 * xDif3) + (yDif3 * yDif3);
                                    boolean collosion3 = distanceSquared3 < (radius.get(s) + 50) * (radius.get(s) + 50);

                                    if (collosion3)
                                        j=true;
                                }
                                if (!j)
                                    break;
                            }
                        }
                        for (int z=xRandom-50;z<=xRandom+50;z++)
                        {
                            double a=Math.pow(z-xRandom,2);
                            double r=2500.0;
                            double b=Math.sqrt(r-a);
                            Log.i("shaho",z+"///////"+b);
                            for (int u = (int) (yRandom-b);u<=yRandom+b;u++)
                                Location[z][u]=1;
                        }

                        xPos.add((float) xRandom);
                        xVel.add(0.0f);
                        yPos.add((float) yRandom);
                        yVel.add(0.0f);
                        radius.add((float) 50);
                        type.add(2);
                        start.add(1);

                        while (true)
                        {
                            xRandom=random.nextInt((int) xMax-99)+50;
                            yRandom=random.nextInt((int) yMax-99)+50;

                            if (Location[xRandom][yRandom]==0)
                            {
                                boolean j=false;
                                for (int s=0;s<xPos.size();s++)
                                {
                                    float xDif3 = xPos.get(s) - xRandom;
                                    float yDif3 = yPos.get(s) - yRandom;
                                    float distanceSquared3 = (xDif3 * xDif3) + (yDif3 * yDif3);
                                    boolean collosion3 = distanceSquared3 < (radius.get(s) + 50) * (radius.get(s) + 50);

                                    if (collosion3)
                                        j=true;
                                }
                                if (!j)
                                    break;
                            }
                        }
                        for (int z=xRandom-50;z<=xRandom+50;z++)
                        {
                            double a=Math.pow(z-xRandom,2);
                            double r=2500.0;
                            double b=Math.sqrt(r-a);
                            Log.i("shaho",z+"///////"+b);
                            for (int u = (int) (yRandom-b);u<=yRandom+b;u++)
                                Location[z][u]=1;
                        }

                        xPos.add((float) xRandom);
                        xVel.add(0.0f);
                        yPos.add((float) yRandom);
                        yVel.add(0.0f);
                        radius.add((float) 50);
                        type.add(2);
                        start.add(1);

                        flag=false;
                    }
                }
            }
    }

}// end of class
