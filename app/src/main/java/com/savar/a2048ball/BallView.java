package com.savar.a2048ball;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class BallView extends View
{
    private Paint paint;

    SharedPreferences.Editor edit=MenuActivity.sharedPreferences.edit();
//    private Canvas canvas2;
//    Handler handler;
//    Runnable runnable;

    //public static Handler handler=new Handler();

    public BallView(Context context)
    {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
//        canvas2=canvas;
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(50);
        paint.setTextAlign(Paint.Align.CENTER);
        for (int x = 0; x < MainActivity.xPos.size(); x++)
        {
//            if (MainActivity.type.get(x)==2 && MainActivity.start.get(x)==1)
//            {
//                MainActivity.start.set(x,0);
//                final float xK=MainActivity.xPos.get(x);
//                final float yK=MainActivity.yPos.get(x);
//                for (int i = 1; i<=MainActivity.radius.get(x); i++)
//                {
//                    final int finalX = x;
//                    final int finalI = i;
//                    new CountDownTimer(1,1000)
//                    {
//
//                        @Override
//                        public void onTick(long millisUntilFinished)
//                        {
//
//                        }
//
//                        @Override
//                        public void onFinish()
//                        {
//                            canvas2.drawCircle(xK, yK, finalI, paint);
//                            invalidate();
//                        }
//                    }.start();
//                }
//            }
//            else
//            {
                paint.setARGB(180, 0, 0, 0);
                canvas.drawCircle(MainActivity.xPos.get(x), MainActivity.yPos.get(x), MainActivity.radius.get(x), paint);
                paint.setColor(Color.WHITE);
                canvas.drawText(MainActivity.type.get(x) + "", MainActivity.xPos.get(x), MainActivity.yPos.get(x), paint);
                MainActivity.txt.setText(MainActivity.score+"");
                if(MenuActivity.sharedPreferences.getInt("score",0)<MainActivity.score)
                {
                    MainActivity.imgBest.setImageResource(R.drawable.a);
                    edit.putInt("score",MainActivity.score);
                    edit.commit();
                    MenuActivity.txtScoreBest.setText(MainActivity.score+"");
                }
                invalidate();
//            }
        }
        invalidate();
    }
}