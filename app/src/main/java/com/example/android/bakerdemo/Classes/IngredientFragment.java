package com.example.android.bakerdemo.Classes;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakerdemo.Adapters.IngredientAdapter;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Ingredient;

import java.util.List;

public class IngredientFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    IngredientAdapter ingredientAdapter;
    Parcelable recyclerState;

    public IngredientFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.ingredient_fragment, container, false);
        List<Ingredient> ingredients = Container.getIngredients();


        recyclerView = view.findViewById(R.id.ingredient_rv);
        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.hasFixedSize();
        ingredientAdapter = new IngredientAdapter(getContext(), ingredients);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(ingredientAdapter);
        if(Container.getState() != null){
            recyclerState = Container.getState();
            recyclerView.getLayoutManager().onRestoreInstanceState(recyclerState);
        }
        recyclerView.getAdapter().notifyDataSetChanged();
        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Container.setState(recyclerView.getLayoutManager().onSaveInstanceState());
    }
}
