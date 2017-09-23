package com.savar.a2048ball;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;

public class BallViewTimeTrial extends View
{
    private Paint paint;

    SharedPreferences.Editor edit=MenuActivity.sharedPreferences.edit();
//    private Canvas canvas2;
//    Handler handler;
//    Runnable runnable;

    //public static Handler handler=new Handler();

    public BallViewTimeTrial(Context context)
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
        for (int x = 0; x < TimeTrialActivity.xPos.size(); x++)
        {
//            if (ClassicPlayActivity.type.get(x)==2 && ClassicPlayActivity.start.get(x)==1)
//            {
//                ClassicPlayActivity.start.set(x,0);
//                final float xK=ClassicPlayActivity.xPos.get(x);
//                final float yK=ClassicPlayActivity.yPos.get(x);
//                for (int i = 1; i<=ClassicPlayActivity.radius.get(x); i++)
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
            canvas.drawCircle(TimeTrialActivity.xPos.get(x), TimeTrialActivity.yPos.get(x), TimeTrialActivity.radius.get(x), paint);
            paint.setColor(Color.WHITE);
            canvas.drawText(TimeTrialActivity.type.get(x) + "", TimeTrialActivity.xPos.get(x), TimeTrialActivity.yPos.get(x), paint);
            TimeTrialActivity.txt.setText(TimeTrialActivity.score+"");
            if(MenuActivity.sharedPreferences.getInt("scoreTimeTrial",0)< TimeTrialActivity.score)
            {
                TimeTrialActivity.imgBest.setImageResource(R.drawable.crown);
                edit.putInt("scoreTimeTrial", TimeTrialActivity.score);
                edit.commit();
                MenuActivity.txtScoreBestTimeTrial.setText(TimeTrialActivity.score+"");
            }
            invalidate();
//            }
        }
        invalidate();
    }
}