package com.example.AutismApplication;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Bogdan on 06.06.14.
 */
public class FragmentDetail extends Fragment {

    public static FragmentDetail newInstance(int pos)
    {
        FragmentDetail detail=new FragmentDetail();
        Bundle bundle=new Bundle();
        bundle.putInt("position",pos);
        detail.setArguments(bundle);
        return detail;
    }
    int getPosition()
    {
        return getArguments().getInt("position",0);
    }
}
