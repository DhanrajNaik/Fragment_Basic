package com.example.systems_5.fragment_basic;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class MainActivity extends FragmentActivity
        implements ItemFragment.OnImageItemSelectedListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);

//        are we using the single or double pane layout
        if (findViewById(R.id.container) != null) {
//            we're using the single pane

//            are we restoring the instance state
            if (savedInstanceState != null) {
                // yes, we're restoring from a saved state - do nothing and return
                return;
            }

//            if it's the first time onCreate is called,
//              we need to display the single pane fragment
            ItemFragment firstFragment = new ItemFragment();

            // Add the fragment to the 'fragment_container' FrameLayout
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, firstFragment).commit();
        }
    }

    public void onImageItemSelected(int position) {
        // The user selected a fruit,
        // now we need to display that fruit's image
        ImageFragment imageFragment = (ImageFragment)
                getSupportFragmentManager()
                        .findFragmentById(R.id.image_fragment);

        if (findViewById(R.id.container) != null) {
            /*we're using a single pane layout -
            need to replace the item list fragment
            with the selected image fragment*/
            // Create the fragment and give it the position
            // of the selected fruit as the argument
            ImageFragment newFragment = new ImageFragment();
            Bundle args = new Bundle();
            args.putInt("position", position);
            newFragment.setArguments(args);
            FragmentTransaction transaction = getSupportFragmentManager()
                    .beginTransaction();
            // Replace whatever is in the fragment_container view
            // with this fragment, and add the transaction to
            // the back stack so the user can navigate back
            transaction.replace(R.id.container, newFragment);
            transaction.addToBackStack(null);
            // Commit the transaction
            transaction.commit();
        } else {
//            we're in the dual pane layout, so simply display
//              the image for the selected fruit
            imageFragment.updateImage(position);
        }
    }
}
