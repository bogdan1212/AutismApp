package com.example.AutismApplication;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


/**
 * Created by Bogdan on 07.06.14.
 */
public class DetailActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

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
                setContentView(R.layout.desire_layout);
            if(pos==2)
                setContentView(R.layout.nowont_layout);
            if(pos==3)
                setContentView(R.layout.like_layout);
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

public void BackClick(View view)
    {
        Button btn=(Button)view;
        Intent intent =new Intent(this, MainActivity.class);
        startActivity(intent);

    }


}
