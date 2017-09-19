package com.savar.a2048ball;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

public class MenuActivity extends Activity {
    public static ShimmerTextView txtScoreBestTimeTrial;
    public static ShimmerTextView txtScoreBestClassicPlay;
    Shimmer shimmerClassicPlay;
    Shimmer shimmerTimeTrial;
    ImageView ImageViewClassicPlay;
    ImageView ImageViewTimeTrial;
    FloatingActionButton float_action_button_setting;
    FloatingActionButton float_action_button_information;

    public static SharedPreferences sharedPreferences;
    Dialog dialogSetting;
    Dialog dialogInformation;

    boolean sounds=true;
    Button btnSounds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);

        txtScoreBestClassicPlay = (ShimmerTextView) findViewById(R.id.txtScoreBestClassicPlay);
        txtScoreBestClassicPlay.setText(sharedPreferences.getInt("scoreClassicPlay",0)+"");
        shimmerClassicPlay = new Shimmer();
        shimmerClassicPlay.start(txtScoreBestClassicPlay);

        txtScoreBestTimeTrial = (ShimmerTextView) findViewById(R.id.txtScoreBestTimeTrial);
        txtScoreBestTimeTrial.setText(sharedPreferences.getInt("scoreTimeTrial",0)+"");
        shimmerTimeTrial = new Shimmer();
        shimmerTimeTrial.start(txtScoreBestTimeTrial);

        ImageViewClassicPlay = (ImageView) findViewById(R.id.ImageViewClassicPlay);
        ImageViewClassicPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });

        ImageViewTimeTrial= (ImageView) findViewById(R.id.ImageViewTimeTrial);
        ImageViewTimeTrial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Intent intent = new Intent(MenuActivity.this, TimeTrialActivity.class);
                startActivity(intent);
                MenuActivity.this.finish();
            }
        });

        dialogSetting=new Dialog(MenuActivity.this);
        dialogSetting.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(R.layout.activity_dialog_setting);

        float_action_button_setting= (FloatingActionButton) findViewById(R.id.float_action_button_setting);
        float_action_button_setting.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogSetting.show();
            }
        });

        btnSounds= (Button) dialogSetting.findViewById(R.id.btnSounds);
        btnSounds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sounds)
                {
                    sounds=false;
                    btnSounds.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_volume_off_black_24dp, 0, 0, 0);
                }
                else
                {
                    sounds=true;
                    btnSounds.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_volume_up_black_24dp, 0, 0, 0);
                }
            }
        });

        dialogInformation=new Dialog(MenuActivity.this);
        dialogInformation.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogInformation.setContentView(R.layout.activity_dialog_information);

        float_action_button_information= (FloatingActionButton) findViewById(R.id.float_action_button_information);
        float_action_button_information.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dialogInformation.show();
            }
        });
    }
}//end of class
