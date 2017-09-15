package com.savar.a2048ball;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class MenuActivity extends Activity
{
    public static ShimmerTextView txtScoreBest;
    Shimmer shimmer;
    Button btnClassicPlay;

    public static SharedPreferences sharedPreferences;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPreferences= PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);

        txtScoreBest= (ShimmerTextView) findViewById(R.id.txtScoreBest);
        txtScoreBest.setText(sharedPreferences.getInt("score",0)+"");
        shimmer=new Shimmer();
        shimmer.start(txtScoreBest);

        btnClassicPlay= (Button) findViewById(R.id.btnClassicPlay);

        btnClassicPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });
    }
}//end of class
