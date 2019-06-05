package com.example.android.bakerdemo.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.android.bakerdemo.Classes.Container;
import com.example.android.bakerdemo.Classes.IngredientFragment;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Ingredient;

import java.util.List;

public class IngredientActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingredient);

        Intent intent = getIntent();
        List<Ingredient> ingredients = intent.getParcelableArrayListExtra("ingredients");
        Container.setIngredients(ingredients);

        IngredientFragment ingredientFragment = new IngredientFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.getFragments().isEmpty()) {
            fragmentManager.beginTransaction().add(R.id.ingredient_container, ingredientFragment).commit();
        } else {
            fragmentManager.beginTransaction().replace(R.id.ingredient_container, ingredientFragment).commit();
        }

    }
}
