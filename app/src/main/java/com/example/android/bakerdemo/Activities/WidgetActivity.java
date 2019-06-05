package com.example.android.bakerdemo.Activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.android.bakerdemo.Adapters.WidgetAdapter;
import com.example.android.bakerdemo.Classes.Container;
import com.example.android.bakerdemo.Classes.JsonUtil;
import com.example.android.bakerdemo.NetworkUtil;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Recipe;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WidgetActivity extends AppCompatActivity {
    String json = "";
    List<Recipe> recipes = new ArrayList<>();
    RecyclerView recyclerView;
    WidgetAdapter widgetAdapter;
    RecyclerView.LayoutManager layoutManager;
    Parcelable recyclerState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_widget);

        recyclerState = Container.getState();

        new RecipeTask().execute();


    }

    class RecipeTask extends AsyncTask<Void, Void, String> {

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

            recyclerView = findViewById(R.id.widget_rv);
            layoutManager = new LinearLayoutManager(WidgetActivity.this);

            try {
                recipes = JsonUtil.recipeExtractor(json);
                widgetAdapter = new WidgetAdapter(recipes, WidgetActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(widgetAdapter);
                recyclerView.hasFixedSize();
                if (recyclerState != null) {
                    recyclerView.getLayoutManager().onRestoreInstanceState(recyclerState);
                }
                recyclerView.getAdapter().notifyDataSetChanged();
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Container.setState(recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Container.setState(null);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
