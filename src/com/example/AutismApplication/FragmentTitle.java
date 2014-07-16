package com.example.AutismApplication;

import android.app.Activity;
import android.support.v4.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
/**
 * Created by Bogdan on 06.06.14.
 */
public class FragmentTitle extends ListFragment {

    public interface OnItemClickListener
    {
        public void itemClick(int position);
    }
    OnItemClickListener listener;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*ArrayAdapter<String> adapter =
                new ArrayAdapter<String>(getActivity(),
                        android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.headers));
        setListAdapter(adapter);*/
        ArrayAdapter adapter1 = new ArrayAdapter (getActivity(),
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.headers));
        setListAdapter(adapter1);


    }

    @Override
    public void onAttach(Activity activity)
    {
        super.onAttach(activity);
        listener=(OnItemClickListener)activity;
    }

    @Override
    public void onListItemClick(ListView listView,View view,int position, long id)
    {
        super.onListItemClick(listView,view,position,id);
        listener.itemClick(position);
    }

}
