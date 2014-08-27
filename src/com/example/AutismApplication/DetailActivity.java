package com.example.AutismApplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.util.Locale;


/**
 * Created by Bogdan on 07.06.14.
 */

public class DetailActivity extends FragmentActivity {

    ReferenceButton tmpBtn;
    Drawable icon;
    ImageView image;
    String data[] = {"Сделать снимок", "Выбрать из галереи"};
    int position=0;
    String string;
    private TextToSpeech mTTS;
    String textToSpeech = "";
    ImageView imageView;

    File directory;

    DialogInterface.OnClickListener pictureClickListener = new DialogInterface.OnClickListener() {
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case 0:
                    Intent takePicture = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    //takePicture.putExtra(MediaStore.EXTRA_OUTPUT, generateFileUri(1));
                    startActivityForResult(takePicture, 0);
                    break;

                case 1:
                    Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                            android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(pickPhoto , 1);
                    break;
            }
        }
    };

    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {

        if (requestCode == 0) {
            if (resultCode == RESULT_OK) {
                //Object obj = intent.getDataString();
                //icon = (Drawable) obj;
                Bundle bndl = intent.getExtras();
                if (bndl != null) {
                Object obj = intent.getExtras().get("data");
                    if (obj instanceof Bitmap) {
                        Bitmap bitmap = (Bitmap) obj;

                        icon=new BitmapDrawable(bitmap);
                        tmpBtn.setBackground(icon);
                    }
                }
            }
        }
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {

                Object obj = intent.getExtras().get("data");
                if (obj instanceof Bitmap) {
                    Bitmap bitmap = (Bitmap) obj;

                    icon=new BitmapDrawable(bitmap);

                    tmpBtn.setBackground(icon);
                }
            }
        }
        else if (resultCode == RESULT_CANCELED) {
            icon = null;
        }
    }

    private Uri generateFileUri(int type) {
        File file = null;
       if(type == 1)
       {
           file = new File(directory.getPath() + "/" + "photo_"
                   + System.currentTimeMillis() + ".jpg");
       }

        return Uri.fromFile(file);
    }
    private void createDirectory() {
        File directory = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),
                "AutismApplication");
        if (!directory.exists())
            directory.mkdirs();
    }

    protected Dialog onCreateDialog(int id) {
        final AlertDialog.Builder adb = new AlertDialog.Builder(this);

        if(id == 1){

            adb.setItems(data, pictureClickListener);

            adb.setPositiveButton("Save", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    adb.setTitle(string);
                    dialogInterface.dismiss();
                }
            });

            adb.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            adb.setCancelable(true);
            return adb.create();
        }

        if(id==2)
        {
           // final AlertDialog.Builder adb = new AlertDialog.Builder(this);
            LayoutInflater inflater = getLayoutInflater();

            adb.setView(inflater.inflate(R.layout.dialog2, null));

            adb.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    adb.setTitle(string);
                    dialogInterface.dismiss();
                }
            });

            adb.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                }
            });
            adb.setCancelable(true);
            return adb.create();
        }

        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


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

        createDirectory();

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            finish();
            return;
        }

        if (savedInstanceState == null) {
            FragmentDetail details = FragmentDetail.newInstance(getIntent().getIntExtra("position", 0));
            getSupportFragmentManager().beginTransaction()
                    .add(android.R.id.content, details).commit();

            int pos=details.getPosition();

            if(pos==1)
            {
                setContentView(R.layout.desire_layout);

                ReferenceButton btn_drink=new ReferenceButton(this);
                LinearLayout desire_layout1 = (LinearLayout) findViewById(R.id.desire_layout1);
                LinearLayout.LayoutParams desire_params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params.setMargins(0, 0, 10, 0);

                btn_drink.setLayoutParams(desire_params);
                btn_drink.setBackgroundResource(R.drawable.btn_drink);
                btn_drink.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        textToSpeech = "Я хочу пить";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout1.addView(btn_drink);

                ReferenceButton btn_eat = new ReferenceButton(this);
                LinearLayout.LayoutParams desire_params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params2.setMargins(10, 0, 0, 0);

                btn_eat.setLayoutParams(desire_params2);
                btn_eat.setBackgroundResource(R.drawable.btn_eat);
                btn_eat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        String textToSpeech = "Я хочу есть";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout1.addView(btn_eat);

                ReferenceButton btn_bath=new ReferenceButton(this);
                LinearLayout desire_layout2 = (LinearLayout) findViewById(R.id.desire_layout2);
                LinearLayout.LayoutParams desire_params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params3.setMargins(0, 0, 10, 0);
                btn_bath.setLayoutParams(desire_params3);

                btn_bath.setBackgroundResource(R.drawable.btn_bath);
                btn_bath.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        String textToSpeech = "Я хочу принять ванну";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout2.addView(btn_bath);


                ReferenceButton btn_music=new ReferenceButton(this);
                LinearLayout.LayoutParams desire_params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params4.setMargins(10, 0, 0, 0);
                btn_music.setLayoutParams(desire_params4);

                btn_music.setBackgroundResource(R.drawable.btn_music);

                btn_music.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        String textToSpeech = "Я хочу слушать музыку";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout2.addView(btn_music);

                ReferenceButton btn_read=new ReferenceButton(this);
                LinearLayout desire_layout3 = (LinearLayout) findViewById(R.id.desire_layout3);
                LinearLayout.LayoutParams desire_params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params5.setMargins(0, 0, 10, 0);

                btn_read.setLayoutParams(desire_params5);
                btn_read.setBackgroundResource(R.drawable.btn_read);
                btn_read.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        String textToSpeech = "Я хочу читать книгу";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout3.addView(btn_read);

                ReferenceButton btn_sleep=new ReferenceButton(this);
                LinearLayout.LayoutParams desire_params6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                desire_params6.setMargins(10, 0, 0, 0);

                btn_sleep.setLayoutParams(desire_params6);
                btn_sleep.setBackgroundResource(R.drawable.btn_sleep);
                btn_sleep.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        String textToSpeech = "Я хочу спать";
                        mTTS.speak(textToSpeech, TextToSpeech.QUEUE_FLUSH, null);
                    }
                });
                desire_layout3.addView(btn_sleep);
            }

            if(pos==2)
            {
                setContentView(R.layout.nowont_layout);
                ReferenceButton btn_like1=new ReferenceButton(this);
                LinearLayout like_layout1 = (LinearLayout) findViewById(R.id.nowont_layout1);
                LinearLayout.LayoutParams like_params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params1.setMargins(0, 0, 10, 0);

                btn_like1.setLayoutParams(like_params1);
                btn_like1.setBackgroundResource(R.drawable.btn_add);

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                btn_like1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        showDialog(1);
                        tmpBtn = btn;
                        String textToSpeech = "";
                    }
                });
                like_layout1.addView(btn_like1);

                ReferenceButton btn_like2=new ReferenceButton(this);
                LinearLayout.LayoutParams like_params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params2.setMargins(10, 0, 0, 0);

                btn_like2.setLayoutParams(like_params1);
                btn_like2.setBackgroundResource(R.drawable.btn_add);
                btn_like2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        //btn.setBackground(icon);
                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout1.addView(btn_like2);

                ReferenceButton btn_like3=new ReferenceButton(this);
                LinearLayout like_layout2 = (LinearLayout) findViewById(R.id.nowont_layout2);
                LinearLayout.LayoutParams like_params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params3.setMargins(0, 0, 10, 0);

                btn_like3.setLayoutParams(like_params3);
                btn_like3.setBackgroundResource(R.drawable.btn_add);
                btn_like3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);

                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout2.addView(btn_like3);

                ReferenceButton btn_like4=new ReferenceButton(this);
                LinearLayout.LayoutParams like_params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params4.setMargins(10, 0, 0, 0);

                btn_like4.setLayoutParams(like_params4);
                btn_like4.setBackgroundResource(R.drawable.btn_add);
                btn_like4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout2.addView(btn_like4);

                ReferenceButton btn_like5=new ReferenceButton(this);
                LinearLayout like_layout3 = (LinearLayout) findViewById(R.id.nowont_layout3);
                LinearLayout.LayoutParams like_params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params5.setMargins(0, 0, 10, 0);

                btn_like5.setLayoutParams(like_params5);
                btn_like5.setBackgroundResource(R.drawable.btn_add);
                btn_like5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        String textToSpeech = "";
                    }
                });
                like_layout3.addView(btn_like5);


                ReferenceButton btn_like6=new ReferenceButton(this);

                LinearLayout.LayoutParams like_params6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params6.setMargins(10, 0, 0, 0);

                btn_like6.setLayoutParams(like_params6);
                btn_like6.setBackgroundResource(R.drawable.btn_add);
                btn_like6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        String textToSpeech = "";
                    }
                });
                like_layout3.addView(btn_like6);
            }
            if(pos==3)
            {
                setContentView(R.layout.like_layout);

                ReferenceButton btn_like1=new ReferenceButton(this);
                LinearLayout like_layout1 = (LinearLayout) findViewById(R.id.like_layout1);
                LinearLayout.LayoutParams like_params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params1.setMargins(0, 0, 10, 0);

                btn_like1.setLayoutParams(like_params1);
                btn_like1.setBackgroundResource(R.drawable.btn_add);

                AlertDialog.Builder dialog = new AlertDialog.Builder(this);
                btn_like1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();

                        showDialog(1);
                        tmpBtn = btn;
                        String textToSpeech = "";
                    }
                });
                like_layout1.addView(btn_like1);

                ReferenceButton btn_like2=new ReferenceButton(this);
                LinearLayout.LayoutParams like_params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params2.setMargins(10, 0, 0, 0);

                btn_like2.setLayoutParams(like_params1);
                btn_like2.setBackgroundResource(R.drawable.btn_add);
                btn_like2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        //btn.setBackground(icon);
                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout1.addView(btn_like2);

                ReferenceButton btn_like3=new ReferenceButton(this);
                LinearLayout like_layout2 = (LinearLayout) findViewById(R.id.like_layout2);
                LinearLayout.LayoutParams like_params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params3.setMargins(0, 0, 10, 0);

                btn_like3.setLayoutParams(like_params3);
                btn_like3.setBackgroundResource(R.drawable.btn_add);
                btn_like3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);

                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout2.addView(btn_like3);

                ReferenceButton btn_like4=new ReferenceButton(this);
                LinearLayout.LayoutParams like_params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params4.setMargins(10, 0, 0, 0);

                btn_like4.setLayoutParams(like_params4);
                btn_like4.setBackgroundResource(R.drawable.btn_add);
                btn_like4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        tmpBtn=btn;
                        String textToSpeech = "";
                    }
                });
                like_layout2.addView(btn_like4);

                ReferenceButton btn_like5=new ReferenceButton(this);
                LinearLayout like_layout3 = (LinearLayout) findViewById(R.id.like_layout3);
                LinearLayout.LayoutParams like_params5 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params5.setMargins(0, 0, 10, 0);

                btn_like5.setLayoutParams(like_params5);
                btn_like5.setBackgroundResource(R.drawable.btn_add);
                btn_like5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        String textToSpeech = "";
                    }
                });
                like_layout3.addView(btn_like5);


                ReferenceButton btn_like6=new ReferenceButton(this);

                LinearLayout.LayoutParams like_params6 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.MATCH_PARENT,1);
                like_params6.setMargins(10, 0, 0, 0);

                btn_like6.setLayoutParams(like_params6);
                btn_like6.setBackgroundResource(R.drawable.btn_add);
                btn_like6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ReferenceButton btn=(ReferenceButton)view;
                        position=btn.getPosition();
                        showDialog(1);
                        String textToSpeech = "";
                    }
                });
                like_layout3.addView(btn_like6);
            }
            if(pos==4)
                setContentView(R.layout.dislike_layout);
            if(pos==5)
                setContentView(R.layout.fine_layout);
            if(pos==6)
                setContentView(R.layout.fine_layout);
            if(pos==7)
                setContentView(R.layout.bad_layout);
            if(pos==8)
                setContentView(R.layout.other_layout);
        }
    }

/*
        final ReferenceButton b=new ReferenceButton(this);
        LinearLayout linlike1 = (LinearLayout) findViewById(R.id.like_layout1);
        b.setPosition(1);
        b.setBackgroundResource(R.drawable.btn_one);
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        final AlertDialog.Builder dialog = new AlertDialog.Builder(this);

       /* b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ReferenceButton btn=(ReferenceButton)view;
                position=btn.getPosition();
                showDialog(1);
                // b.setBackground(icon);
                String textToSpeech = "";
            }
        });
        b.setOnClickListener(listener);
        linlike1.addView(b);
    }*/

public void BackClick(View view)
    {
        Button btn=(Button)view;
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}