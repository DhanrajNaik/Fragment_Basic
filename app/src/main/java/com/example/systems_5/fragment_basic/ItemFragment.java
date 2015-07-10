package com.example.systems_5.fragment_basic;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by System S-5 on 10-07-2015.
 */
public class ItemFragment extends ListFragment {

    OnImageItemSelectedListener mCallback;

    String[] imageItemsArray = {"Apple", "Cherries",
            "Lemon", "Orange", "Pear", "Strawberry"};

    public interface OnImageItemSelectedListener {
        void onImageItemSelected(int position);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // We need to use a different list item layout for
        // devices older than Honeycomb
        int layout;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
//            needs API Level 11
            layout = android.R.layout.simple_list_item_activated_1;
            Log.d("djboy layout", String.valueOf(layout));
//        used as of API Level 1
        }else {
            layout = android.R.layout.simple_list_item_1;
        }


        // Create an array adapter for the list view,
        // using the imageItemsArray array
        setListAdapter(new ArrayAdapter<String>(getActivity(),layout, imageItemsArray));

    }

    @Override
    public void onStart() {
        super.onStart();
        getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // makes sure parent MainActivity implements
        // the callback interface. If not, it throws an exception.
        try {
            mCallback = (OnImageItemSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " The MainActivity activity must " +
                    "implement OnImageItemSelectedListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v,
                                int position, long id) {
        super.onListItemClick(l, v, position, id);

        // Notify the MainActivity of selected item
        mCallback.onImageItemSelected(position);

    }
}
