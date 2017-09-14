package com.savar.a2048ball;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity
{
    Button btnClassicPlay;

    @Override
    protected void onCreate( Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        btnClassicPlay= (Button) findViewById(R.id.btnClassicPlay);

        btnClassicPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent=new Intent(MenuActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}//end of class
