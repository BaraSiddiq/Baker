package com.example.android.bakerdemo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakerdemo.Classes.PlayerFragment;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Step;

public class StepActivity extends AppCompatActivity {

    PlayerFragment playerFragment;
    Step step;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);


        Intent intent = getIntent();
        step = intent.getParcelableExtra("step");

        playerFragment = new PlayerFragment();


        FragmentManager fragmentManager = getSupportFragmentManager();

        if (fragmentManager.getFragments().isEmpty()) {
            fragmentManager.beginTransaction().add(R.id.player_container, playerFragment).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.player_container, playerFragment).commit();
        }


    }


}
