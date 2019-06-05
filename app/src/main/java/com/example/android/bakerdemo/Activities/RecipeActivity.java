package com.example.android.bakerdemo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakerdemo.Classes.Container;
import com.example.android.bakerdemo.Classes.IngredientFragment;
import com.example.android.bakerdemo.Classes.MasterFragment;
import com.example.android.bakerdemo.Classes.PlayerFragment;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Recipe;

public class RecipeActivity extends AppCompatActivity {


    private static FragmentManager fragmentManager;
    private static MasterFragment masterFragment;
    private static PlayerFragment playerFragment;
    private static IngredientFragment ingredientFragment;
    private static Recipe recipe;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
        Intent intent = getIntent();
        recipe = null;
        recipe = intent.getParcelableExtra("recipe");


        if (findViewById(R.id.big_screen) == null) {
            Container.setIsBigScreen(false);
            masterFragment = new MasterFragment();
            fragmentManager = getSupportFragmentManager();
            manageTransaction(fragmentManager, masterFragment);
        } else {
            Container.setIsBigScreen(true);
            Container.setIngredients(recipe.getIngredients());
            Container.setStep(recipe.getSteps().get(0));

            masterFragment = new MasterFragment();
            playerFragment = new PlayerFragment();
            ingredientFragment = new IngredientFragment();
            fragmentManager = getSupportFragmentManager();

            if (fragmentManager.getFragments().isEmpty()) {
                fragmentManager.beginTransaction().add(R.id.master_container, masterFragment).commit();
                if (Container.isWhichSideView()) {
                    fragmentManager.beginTransaction().add(R.id.player_container, playerFragment).commit();
                } else {
                    fragmentManager.beginTransaction().add(R.id.player_container, ingredientFragment).commit();
                }

            } else {
                fragmentManager.beginTransaction().replace(R.id.master_container, masterFragment).commit();
                if (Container.isWhichSideView()) {


                    fragmentManager.beginTransaction().replace(R.id.player_container, playerFragment).commit();
                } else {

                    fragmentManager.beginTransaction().replace(R.id.player_container, ingredientFragment).commit();

                }
            }


        }


    }

    public static void setSideView(int index) {
        if (recipe != null) {
            if (!Container.isWhichSideView()) {
                ingredientFragment = new IngredientFragment();
                fragmentManager.beginTransaction().replace(R.id.player_container, ingredientFragment).commit();
            } else {

                Container.setStep(recipe.getSteps().get(index));
                playerFragment = new PlayerFragment();
                fragmentManager.beginTransaction().replace(R.id.player_container, playerFragment).commit();
            }
        }
    }


    public void manageTransaction(FragmentManager fm, MasterFragment mf) {
        if (fm.getFragments().isEmpty()) {
            fm.beginTransaction().add(R.id.master_container, mf).commit();
        } else {
            fm.beginTransaction().replace(R.id.master_container, mf).commit();
        }
    }


}
