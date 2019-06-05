package com.example.android.bakerdemo.Classes;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.android.bakerdemo.Adapters.StepsAdapter;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Recipe;

public class MasterFragment extends Fragment {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    StepsAdapter stepsAdapter;
    Recipe recipe;
    View view;
    Parcelable recyclerState;


    public MasterFragment() {

    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.master_fragment, container, false);

        recipe = Container.getRecipe();

        recyclerView = view.findViewById(R.id.steps_rv);
        layoutManager = new LinearLayoutManager(getContext());
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.hasFixedSize();
        stepsAdapter = new StepsAdapter(getContext(), recipe);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(stepsAdapter);
        if (Container.getState() != null) {
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
