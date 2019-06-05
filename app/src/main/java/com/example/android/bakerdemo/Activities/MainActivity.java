package com.example.android.bakerdemo.Activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.android.bakerdemo.Adapters.RecipeAdapter;
import com.example.android.bakerdemo.Classes.JsonUtil;
import com.example.android.bakerdemo.NetworkUtil;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Recipe;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    String json = "";
    List<Recipe> recipes = new ArrayList<>();

    RecyclerView recipeRecyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecipeAdapter recipeAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new RecipeTask().execute();

    }

    class RecipeTask extends AsyncTask<Void, Void, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                json = NetworkUtil.httpRequest();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return json;
        }

        @Override
        protected void onPostExecute(String json) {
            super.onPostExecute(json);
            json = "{\"rootArray\":" + json + "}";
            recipeRecyclerView = findViewById(R.id.recipe_rv);
            layoutManager = new LinearLayoutManager(MainActivity.this);
            ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
            recipeAdapter = new RecipeAdapter(MainActivity.this);
            try {
                recipes = JsonUtil.recipeExtractor(json);
                recipeAdapter.setRecipes(recipes);
                recipeRecyclerView.setLayoutManager(layoutManager);
                recipeRecyclerView.hasFixedSize();
                recipeRecyclerView.setAdapter(recipeAdapter);
                recipeRecyclerView.getAdapter().notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }


}
