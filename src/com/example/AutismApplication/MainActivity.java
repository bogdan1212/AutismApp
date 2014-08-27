package com.example.AutismApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.DialogFragment;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.example.AutismApplication.FragmentTitle.OnItemClickListener;

import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnItemClickListener {

    int position=0;
    boolean withDetails = true;
    private TextToSpeech mTTS;
    String data[] = {"Take form camera", "Take from library"};
    Drawable icon;
    ImageView image;
    String textToSpeech = "";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        final MainActivity _self=this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        final ReferenceButton b1=new ReferenceButton(this);
        LinearLayout lin1 = (LinearLayout) findViewById(R.id.layout1);
        b1.setPosition(1);
        b1.setBackgroundResource(R.drawable.btn_one);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params.setMargins(0, 0, 10, 0);
        b1.setLayoutParams(params);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Я хочу";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        lin1.addView(b1);


        final ReferenceButton b2=new ReferenceButton(this);
        b2.setPosition(2);
        b2.setBackgroundResource(R.drawable.btn_two);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params2.setMargins(10, 0, 0, 0);
        b2.setLayoutParams(params2);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Я не хочу";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin1.addView(b2);


        final ReferenceButton b3=new ReferenceButton(this);
        LinearLayout lin2 = (LinearLayout) findViewById(R.id.layout2);
        b3.setPosition(3);
        b3.setBackgroundResource(R.drawable.btn_three);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params3.setMargins(0, 0, 10, 0);
        b3.setLayoutParams(params3);

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Мне нравится";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin2.addView(b3);


        final ReferenceButton b4=new ReferenceButton(this);
        b4.setPosition(4);
        b4.setBackgroundResource(R.drawable.btn_four);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params4.setMargins(10, 0, 0, 0);
        b4.setLayoutParams(params4);


        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Мне не нравится";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin2.addView(b4);

        final ReferenceButton b5=new ReferenceButton(this);
        LinearLayout lin3 = (LinearLayout) findViewById(R.id.layout3);
        b5.setPosition(5);
        b5.setBackgroundResource(R.drawable.btn_five);
        LinearLayout.LayoutParams params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params5.setMargins(0, 0, 10, 0);
        b5.setLayoutParams(params5);

        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Хорошо";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin3.addView(b5);


        final ReferenceButton b6=new ReferenceButton(this);
        b6.setPosition(6);
        b6.setBackgroundResource(R.drawable.btn_six);
        LinearLayout.LayoutParams params6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params6.setMargins(10, 0, 0, 0);
        b6.setLayoutParams(params6);

        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Мне не очень";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin3.addView(b6);

        final ReferenceButton b7=new ReferenceButton(this);
        LinearLayout lin4 = (LinearLayout) findViewById(R.id.layout4);
        b7.setPosition(7);
        b7.setBackgroundResource(R.drawable.btn_seven);
        LinearLayout.LayoutParams params7 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params7.setMargins(0, 0, 10, 0);
        b7.setLayoutParams(params7);

        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Мне нужно в туалет";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin4.addView(b7);


        final ReferenceButton b8=new ReferenceButton(this);
        b8.setPosition(8);
        b8.setBackgroundResource(R.drawable.btn_eight);
        LinearLayout.LayoutParams params8 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT,1);
        params8.setMargins(10, 0, 0, 0);
        b8.setLayoutParams(params8);

        b8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton button=(ReferenceButton)view;
                position=button.getPosition();
                Intent intent = new Intent(_self, DetailActivity.class).putExtra("position", position);
                startActivity(intent);
                textToSpeech = "Другое";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
            }
        });

        lin4.addView(b8);


        mTTS = new TextToSpeech(this,new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status == TextToSpeech.SUCCESS) {
                    Locale locale = new Locale("ru");

                    int result = mTTS.setLanguage(locale);

                    if(result == TextToSpeech.LANG_MISSING_DATA ||
                            result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Language is not supported");
                    } else {
                        Log.e("TTS", "It should work");
                    }
                } else {
                    Log.e("TTS", "Error");
                }
            }
        });

        if (savedInstanceState != null)
            position = savedInstanceState.getInt("position");
        withDetails = (findViewById(R.id.cont) != null);
        if (withDetails){
            showDetails(position);}
    }

    void showDetails(int pos) {
        if (withDetails) {
            FragmentDetail details = (FragmentDetail) getSupportFragmentManager()
                    .findFragmentById(R.id.cont);
            if (details == null || details.getPosition() != pos) {
                details = FragmentDetail.newInstance(pos);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.cont, details).commit();
            }
        } else {
            startActivity(new Intent(this, DetailActivity.class).putExtra("position", position));
        }
    }

    @Override
    public void itemClick(int position) {
        this.position=position;
        showDetails(position);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("position", position);
    }

    public void onDestroy () {
        if(mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
