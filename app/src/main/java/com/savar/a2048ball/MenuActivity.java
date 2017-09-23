package com.savar.a2048ball;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;
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
    SharedPreferences.Editor editor;

    Dialog dialogSetting;
    Dialog dialogInformation;
    private Effectstype effect;
    NiftyDialogBuilder dialogBuilder;

    Button btnSounds;
    Button btnBackgroundColor;
    Button btnLanguage;

    Button btnHowToPlay;
    Button btnAbout2048Ball;

    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);
        editor=sharedPreferences.edit();

        txtScoreBestClassicPlay = (ShimmerTextView) findViewById(R.id.txtScoreBestClassicPlay);
        txtScoreBestClassicPlay.setText(sharedPreferences.getInt("scoreClassicPlay",0)+"");
        shimmerClassicPlay = new Shimmer();
        shimmerClassicPlay.start(txtScoreBestClassicPlay);

        txtScoreBestTimeTrial = (ShimmerTextView) findViewById(R.id.txtScoreBestTimeTrial);
        txtScoreBestTimeTrial.setText(sharedPreferences.getInt("scoreTimeTrial",0)+"");
        shimmerTimeTrial = new Shimmer();
        shimmerTimeTrial.start(txtScoreBestTimeTrial);

        dialogBuilder=NiftyDialogBuilder.getInstance(this);
        effect=Effectstype.Newspager;

        ImageViewClassicPlay = (ImageView) findViewById(R.id.ImageViewClassicPlay);
        ImageViewClassicPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_legend_in);
                    mediaPlayer.start();
                }
                if (sharedPreferences.getString("pause","0").compareTo("0")==0)
                {
                    Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                    startActivity(intent);
                    if (sharedPreferences.getBoolean("sounds",true))
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                        mediaPlayer.start();
                    }
                }
                else
                {
//                    dialogBuilder.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialogBuilder
                            .withTitle("Message")                                  //.withTitle(null)  no title
                            .withTitleColor("#FFFFFF")                                  //def
                            .withDividerColor("#11000000")//def
                            .withMessage("Choose one of the options")                     //.withMessage(null)  no Msg
                            .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                            .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
//                            .withIcon(getResources().getDrawable(R.drawable.icon))
                            .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                            .withDuration(700)                                          //def
                            .withEffect(effect)                                         //def Effectstype.Slidetop
                            .withButton1Text("RESTART")                                 //def gone
                            .withButton2Text("CONTINUE")                                //def gone
                            .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                            .setButton1Click(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    ClassicPlayActivity.restart();
                                    Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                                    startActivity(intent);
                                    if (sharedPreferences.getBoolean("sounds",true))
                                    {
                                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                                        mediaPlayer.start();
                                    }
                                }
                            })
                            .setButton2Click(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                                    startActivity(intent);
                                    if (sharedPreferences.getBoolean("sounds",true))
                                    {
                                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                                        mediaPlayer.start();
                                    }
                                }
                            }).show();
                }
            }
        });

        ImageViewTimeTrial= (ImageView) findViewById(R.id.ImageViewTimeTrial);
        ImageViewTimeTrial.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(MenuActivity.this,"Coming Soon ...",Toast.LENGTH_LONG).show();
//                Intent intent = new Intent(MenuActivity.this, TimeTrialActivity.class);
//                startActivity(intent);
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
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_legend_in);
                    mediaPlayer.start();
                }
                dialogSetting.show();
            }
        });

        btnSounds= (Button) dialogSetting.findViewById(R.id.btnSounds);
        if (!sharedPreferences.getBoolean("sounds",true))
        {
            btnSounds.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_volume_off_black_24dp, 0, 0, 0);
        }
        btnSounds.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    editor.putBoolean("sounds",false);
                    editor.commit();
                    mediaPlayer.pause();
                    btnSounds.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_volume_off_black_24dp, 0, 0, 0);
                }
                else
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                    mediaPlayer.start();
                    editor.putBoolean("sounds",true);
                    editor.commit();
                    btnSounds.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.ic_volume_up_black_24dp, 0, 0, 0);
                }
            }
        });

        btnBackgroundColor=(Button) dialogSetting.findViewById(R.id.btnBackgroundColor);
        btnBackgroundColor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                    mediaPlayer.start();
                }
            }
        });

        btnLanguage= (Button) dialogSetting.findViewById(R.id.btnLanguage);
        btnLanguage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                    mediaPlayer.start();
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
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_legend_in);
                    mediaPlayer.start();
                }
                dialogInformation.show();
            }
        });

        btnHowToPlay= (Button) dialogInformation.findViewById(R.id.btnHowToPlay);
        btnHowToPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                    mediaPlayer.start();
                }
            }
        });

        btnAbout2048Ball= (Button) dialogInformation.findViewById(R.id.btnAbout2048Ball);
        btnAbout2048Ball.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (sharedPreferences.getBoolean("sounds",true))
                {
                    mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                    mediaPlayer.start();
                }
            }
        });
    }
}//end of class
