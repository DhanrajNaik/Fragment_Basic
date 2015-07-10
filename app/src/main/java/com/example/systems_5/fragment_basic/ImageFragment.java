package com.example.systems_5.fragment_basic;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by System S-5 on 10-07-2015.
 */
public class ImageFragment extends Fragment {

    int[] drawableIdsArray = {R.drawable.apple, R.drawable.cherry,
            R.drawable.lemon, R.drawable.orange, R.drawable.pear,
            R.drawable.strawberry};
    private static int mCurrentPosition = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_layout, container, false);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        //check if there are arguments passed to the fragment.
        Bundle args = getArguments();
        if (args != null) {
            // Set image based on argument passed in
            updateImage(args.getInt("position"));
        } else if (mCurrentPosition != -1) {
            // Set image based on saved instance state
            // defined during onCreateView
            updateImage(mCurrentPosition);
        }
    }

    public void updateImage(int position) {
        ImageView imageView = (ImageView) getActivity()
                .findViewById(R.id.imageView);
        imageView.setImageResource(drawableIdsArray[position]);
        mCurrentPosition = position;
    }
}
