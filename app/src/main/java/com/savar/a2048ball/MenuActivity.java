package com.savar.a2048ball;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.gitonway.lee.niftymodaldialogeffects.lib.Effectstype;
import com.gitonway.lee.niftymodaldialogeffects.lib.NiftyDialogBuilder;

import ir.myteam.adsdk.AdCommon;

public class MenuActivity extends Activity {
    public static TextView txtScoreBestTimeTrial;
    public static TextView txtScoreBestClassicPlay;
//    Shimmer shimmerClassicPlay;
//    Shimmer shimmerTimeTrial;
    ImageView ImageViewClassicPlay;
    ImageView ImageViewTimeTrial;
    FloatingActionButton float_action_button_setting;
    FloatingActionButton float_action_button_information;

    TextView textView;
    TextView btnClassicPlay;
    TextView btnTimeTrial;
    TextView txtSetting;
    TextView txtSounds;
    TextView txtBackgroundColor;
    TextView txtLanguage;
    TextView txtAbout;

    String title;
    String message;
    String text1;
    String text2;

    public static Typeface farsi_font;
    public static Typeface english_font;

    public static SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    LinearLayout screen_color;

    Dialog dialogSetting;
    Dialog dialogInformation;
    private Effectstype effect;
    NiftyDialogBuilder dialogBuilder;

    public static boolean flag_resume=false;
    boolean sound_flag=false;
    Switch btnSounds;
    boolean backgroundColor_flag=false;
    Switch btnBackgroundColor;
    Switch btnLanguage;

    Button btnHowToPlay;
    Button btnAbout2048Ball;

    public static MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        AdCommon.init(this, "NBCsupeNzC",false,true);

        screen_color= (LinearLayout) findViewById(R.id.screen_color);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(MenuActivity.this);
        editor=sharedPreferences.edit();

        txtScoreBestClassicPlay = (TextView) findViewById(R.id.txtScoreBestClassicPlay);
        txtScoreBestClassicPlay.setText(sharedPreferences.getInt("scoreClassicPlay",0)+"");
//        shimmerClassicPlay = new Shimmer();
//        shimmerClassicPlay.start(txtScoreBestClassicPlay);
//
        txtScoreBestTimeTrial = (TextView) findViewById(R.id.txtScoreBestTimeTrial);
        txtScoreBestTimeTrial.setText(sharedPreferences.getInt("scoreTimeTrial",0)+"");
//        shimmerTimeTrial = new Shimmer();
//        shimmerTimeTrial.start(txtScoreBestTimeTrial);

        dialogSetting=new Dialog(MenuActivity.this);
        dialogSetting.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogSetting.setContentView(R.layout.activity_dialog_setting);

        dialogInformation=new Dialog(MenuActivity.this);
        dialogInformation.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialogInformation.setContentView(R.layout.activity_dialog_information);

        textView= (TextView) findViewById(R.id.textView);
        btnClassicPlay=(TextView) findViewById(R.id.btnClassicPlay);
        btnTimeTrial= (TextView) findViewById(R.id.btnTimeTrial);
        txtSetting= (TextView) dialogSetting.findViewById(R.id.txtSetting);
        txtSounds= (TextView) dialogSetting.findViewById(R.id.txtSounds);
        txtBackgroundColor= (TextView) dialogSetting.findViewById(R.id.txtBackgroundColor);
        txtLanguage= (TextView) dialogSetting.findViewById(R.id.txtLanguage);
        btnLanguage= (Switch) dialogSetting.findViewById(R.id.btnLanguage);
        txtAbout= (TextView) dialogInformation.findViewById(R.id.txtAbout);
        btnHowToPlay= (Button) dialogInformation.findViewById(R.id.btnHowToPlay);
        btnAbout2048Ball= (Button) dialogInformation.findViewById(R.id.btnAbout2048Ball);

        farsi_font=Typeface.createFromAsset(getAssets(),"fonts/bkoodkbd.ttf");
        english_font=Typeface.createFromAsset(getAssets(),"fonts/old_english.ttf");

        if (sharedPreferences.getBoolean("language",true))
        {
            textView.setText("2048 Ball");
            textView.setTypeface(english_font);
            btnClassicPlay.setText("Classic play");
            btnClassicPlay.setTypeface(english_font);
            txtScoreBestClassicPlay.setTypeface(english_font);
            btnTimeTrial.setText("Time trial");
            btnTimeTrial.setTypeface(english_font);
            txtScoreBestTimeTrial.setTypeface(english_font);
            txtSetting.setText("Setting");
            txtSetting.setTypeface(english_font);
            txtSounds.setText("Sounds");
            txtSounds.setTypeface(english_font);
            txtBackgroundColor.setText("Background color");
            txtBackgroundColor.setTypeface(english_font);
            txtLanguage.setText("Language");
            txtLanguage.setTypeface(english_font);
            txtAbout.setText("About");
            txtAbout.setTypeface(english_font);
            btnHowToPlay.setText("How to play");
            btnHowToPlay.setTypeface(english_font);
            btnAbout2048Ball.setText("About 2048 Ball");
            btnAbout2048Ball.setTypeface(english_font);

            title="Message";
            message="Choose one of the options";
            text1="RESTART";
            text2="CONTINUE";
        }
        else
        {
            btnLanguage.setChecked(true);

            textView.setText("2048 توپی");
            textView.setTypeface(farsi_font);
            btnClassicPlay.setText("کلاسیک");
            btnClassicPlay.setTypeface(farsi_font);
            txtScoreBestClassicPlay.setTypeface(farsi_font);
            btnTimeTrial.setText("زمان");
            btnTimeTrial.setTypeface(farsi_font);
            txtScoreBestTimeTrial.setTypeface(farsi_font);
            txtSetting.setText("تنظیمات");
            txtSetting.setTypeface(farsi_font);
            txtSounds.setText("صداها");
            txtSounds.setTypeface(farsi_font);
            txtBackgroundColor.setText("رنگ پس زمینه");
            txtBackgroundColor.setTypeface(farsi_font);
            txtLanguage.setText("زبان");
            txtLanguage.setTypeface(farsi_font);
            txtAbout.setText("درباره");
            txtAbout.setTypeface(farsi_font);
            btnHowToPlay.setText("راهنمای بازی");
            btnHowToPlay.setTypeface(farsi_font);
            btnAbout2048Ball.setText("درباره 2048 توپی");
            btnAbout2048Ball.setTypeface(farsi_font);

            title="پیغام";
            message="یکی از موارد زیر را انتخاب کنید";
            text1="بازی جدید";
            text2="ادامه بازی";
        }

        dialogBuilder=NiftyDialogBuilder.getInstance(this);
        effect=Effectstype.Newspager;

        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_legend_in);

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
//                    MenuActivity.this.finish();
                    if (sharedPreferences.getBoolean("sounds",true))
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                        mediaPlayer.start();
                        mediaPlayer.setLooping(true);
                    }
                }
                else
                {
//                    dialogBuilder.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
                    dialogBuilder
                            .withTitle(title)                                  //.withTitle(null)  no title
                            .withTitleColor("#FFFFFF")                                  //def
                            .withDividerColor("#11000000")//def
                            .withMessage(message)                     //.withMessage(null)  no Msg
                            .withMessageColor("#FFFFFFFF")                              //def  | withMessageColor(int resid)
                            .withDialogColor("#FFE74C3C")                               //def  | withDialogColor(int resid)                               //def
//                            .withIcon(getResources().getDrawable(R.drawable.icon))
                            .isCancelableOnTouchOutside(true)                           //def    | isCancelable(true)
                            .withDuration(700)                                          //def
                            .withEffect(effect)                                         //def Effectstype.Slidetop
                            .withButton1Text(text1)                                 //def gone
                            .withButton2Text(text2)                                //def gone
                            .setCustomView(R.layout.custom_view,v.getContext())         //.setCustomView(View or ResId,context)
                            .setButton1Click(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    flag_resume=false;
                                    Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                                    startActivity(intent);
//                                    MenuActivity.this.finish();
//                                    Intent intent=new Intent(MenuActivity.this,ClassicPlayActivity.class);
//                                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
//                                    startActivity(intent);
                                    ClassicPlayActivity.restart();
//                                    shimmerTimeTrial.cancel();
//                                    shimmerClassicPlay.cancel();
                                    MenuActivity.this.finish();
                                    if (sharedPreferences.getBoolean("sounds",true))
                                    {
                                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                                        mediaPlayer.start();
                                        mediaPlayer.setLooping(true);
                                    }
                                    dialogBuilder.cancel();
                                }
                            })
                            .setButton2Click(new View.OnClickListener()
                            {
                                @Override
                                public void onClick(View v)
                                {
                                    flag_resume=true;
                                    Intent intent = new Intent(MenuActivity.this, ClassicPlayActivity.class);
                                    startActivity(intent);
//                                    shimmerTimeTrial.cancel();
//                                    shimmerClassicPlay.cancel();
                                    MenuActivity.this.finish();
//                                    MenuActivity.this.finish();
                                    if (sharedPreferences.getBoolean("sounds",true))
                                    {
                                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.music_game);
                                        mediaPlayer.start();
                                        mediaPlayer.setLooping(true);
                                    }
                                    dialogBuilder.cancel();
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
                    btnSounds.setChecked(true);
                    sound_flag=false;
                }
//                else
//                    sound_flag=true;
                if (sharedPreferences.getBoolean("backgroundColor",true))
                {
                    btnBackgroundColor.setChecked(true);
                    backgroundColor_flag=false;
                }
                else
                    backgroundColor_flag=true;
                dialogSetting.show();
            }
        });

        btnSounds= (Switch) dialogSetting.findViewById(R.id.btnSounds);

        btnSounds.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    if (sound_flag)
                    {
                        mediaPlayer = MediaPlayer.create(MenuActivity.this, R.raw.ui_fail);
                        mediaPlayer.start();
                    }
                    editor.putBoolean("sounds",true);
                    editor.commit();
                    sound_flag=true;
                }
                else
                {
                    editor.putBoolean("sounds",false);
                    editor.commit();
                    mediaPlayer.pause();
                    sound_flag=true;
                }
            }
        });

        if (!sharedPreferences.getBoolean("backgroundColor",true))
            screen_color.setBackgroundColor(Color.argb(255, 69, 90, 100));

        btnBackgroundColor=(Switch) dialogSetting.findViewById(R.id.btnBackgroundColor);
        btnBackgroundColor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    editor.putBoolean("backgroundColor",true);
                    editor.commit();
                    if (sharedPreferences.getBoolean("sounds",true) && backgroundColor_flag)
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                        mediaPlayer.start();
                    }
                    if (backgroundColor_flag)
                    {
                        ObjectAnimator colorFade = ObjectAnimator.ofObject(screen_color, "backgroundColor", new ArgbEvaluator(), Color.argb(255, 69, 90, 100), Color.argb(255,24, 255, 255));
                        colorFade.setDuration(2000);
                        colorFade.start();
                    }
                    backgroundColor_flag=true;
                }
                else
                {
                    editor.putBoolean("backgroundColor",false);
                    editor.commit();
                    if (sharedPreferences.getBoolean("sounds",true))
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                        mediaPlayer.start();
                    }
                    ObjectAnimator colorFade = ObjectAnimator.ofObject(screen_color, "backgroundColor", new ArgbEvaluator(), Color.argb(255, 24, 255, 255), Color.argb(255, 69, 90, 100));
                    colorFade.setDuration(2000);
                    colorFade.start();
                    backgroundColor_flag=true;
                }
            }
        });

        btnLanguage.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    editor.putBoolean("language",false);
                    editor.commit();
                    if (sharedPreferences.getBoolean("sounds",true))
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                        mediaPlayer.start();
                    }
                    textView.setText("2048 توپی");
                    textView.setTypeface(farsi_font);
                    btnClassicPlay.setText("کلاسیک");
                    btnClassicPlay.setTypeface(farsi_font);
                    txtScoreBestClassicPlay.setTypeface(farsi_font);
                    btnTimeTrial.setText("زمان");
                    btnTimeTrial.setTypeface(farsi_font);
                    txtScoreBestTimeTrial.setTypeface(farsi_font);
                    txtSetting.setText("تنظیمات");
                    txtSetting.setTypeface(farsi_font);
                    txtSounds.setText("صداها");
                    txtSounds.setTypeface(farsi_font);
                    txtBackgroundColor.setText("رنگ پس زمینه");
                    txtBackgroundColor.setTypeface(farsi_font);
                    txtLanguage.setText("زبان");
                    txtLanguage.setTypeface(farsi_font);
                    txtAbout.setText("درباره");
                    txtAbout.setTypeface(farsi_font);
                    btnHowToPlay.setText("راهنمای بازی");
                    btnHowToPlay.setTypeface(farsi_font);
                    btnAbout2048Ball.setText("درباره 2048 توپی");
                    btnAbout2048Ball.setTypeface(farsi_font);

                    title="پیغام";
                    message="یکی از موارد زیر را انتخاب کنید";
                    text1="بازی جدید";
                    text2="ادامه بازی";

                    dialogSetting.cancel();
                    dialogSetting.show();
                }
                else
                {
                    editor.putBoolean("language",true);
                    editor.commit();
                    if (sharedPreferences.getBoolean("sounds",true))
                    {
                        mediaPlayer=MediaPlayer.create(MenuActivity.this,R.raw.ui_fail);
                        mediaPlayer.start();
                    }
                    textView.setText("2048 Ball");
                    textView.setTypeface(english_font);
                    btnClassicPlay.setText("Classic play");
                    btnClassicPlay.setTypeface(english_font);
                    txtScoreBestClassicPlay.setTypeface(english_font);
                    btnTimeTrial.setText("Time trial");
                    btnTimeTrial.setTypeface(english_font);
                    txtScoreBestTimeTrial.setTypeface(english_font);
                    txtSetting.setText("Setting");
                    txtSetting.setTypeface(english_font);
                    txtSounds.setText("Sounds");
                    txtSounds.setTypeface(english_font);
                    txtBackgroundColor.setText("Background color");
                    txtBackgroundColor.setTypeface(english_font);
                    txtLanguage.setText("Language");
                    txtLanguage.setTypeface(english_font);
                    txtAbout.setText("About");
                    txtAbout.setTypeface(english_font);
                    btnHowToPlay.setText("How to play");
                    btnHowToPlay.setTypeface(english_font);
                    btnAbout2048Ball.setText("About 2048 Ball");
                    btnAbout2048Ball.setTypeface(english_font);

                    title="Message";
                    message="Choose one of the options";
                    text1="RESTART";
                    text2="CONTINUE";
                }
            }
        });

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
