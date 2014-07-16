package com.example.AutismApplication;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.example.AutismApplication.FragmentTitle.OnItemClickListener;

public class MainActivity extends FragmentActivity implements OnItemClickListener {
    /**
     * Called when the activity is first created.
     */

    int position=0;
    boolean withDetails = true;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.main);
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


        switch (btn.getId())
        {
            case R.id.btn1:
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
}
