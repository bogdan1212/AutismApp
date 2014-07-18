package com.example.AutismApplication;

import android.app.Activity;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.example.AutismApplication.FragmentTitle.OnItemClickListener;
import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends FragmentActivity implements OnItemClickListener  {
    /**
     * Called when the activity is first created.
     */

    int position=0;
    boolean withDetails = true;
    private TextToSpeech mTTS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);

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


public void onClick(View view) {

        Button btn=(Button)view;
        Intent intent = new Intent(this, DetailActivity.class);
        startActivity(intent);
        String textToSpeech = "";


        switch (btn.getId())
        {
            case R.id.btn1:
                textToSpeech = "Я хочу есть";
                mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                position=1;
                break;

            case R.id.btn2:
                position=2;
                break;

            case R.id.btn3:
                position=3;
                break;

            case R.id.btn4:
                position=4;
                break;

            case R.id.btn5:
                position=5;
                break;

            case R.id.btn6:
                position=6;
                break;

            case R.id.btn7:
                position=7;
                break;

            case R.id.btn8:
                position=8;
                break;
        }
        itemClick(position);
    }

    public void onDestroy () {
        if(mTTS != null) {
            mTTS.stop();
            mTTS.shutdown();
        }
        super.onDestroy();
    }
}
