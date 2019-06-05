package com.example.android.bakerdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakerdemo.Activities.IngredientActivity;
import com.example.android.bakerdemo.Activities.RecipeActivity;
import com.example.android.bakerdemo.Activities.StepActivity;
import com.example.android.bakerdemo.Classes.Container;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Ingredient;
import com.example.android.bakerdemo.Recipe.Recipe;
import com.example.android.bakerdemo.Recipe.Step;

import java.util.ArrayList;
import java.util.List;

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepHolder> {

    private static final String INGREDIENT = " Ingredients";
    private static final String STEP = "Step ";
    Recipe recipe;
    Step step;
    List<Ingredient> ingredients;
    Context context;

    public StepsAdapter(Context context, Recipe recipe) {
        this.context = context;
        this.recipe = recipe;
    }

    @NonNull
    @Override
    public StepHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recipe_step_item, viewGroup, false);
        StepHolder stepHolder = new StepHolder(view);
        return stepHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StepHolder stepHolder, int i) {
        if (i == 0) {
            stepHolder.textView.setText(recipe.getName() + INGREDIENT);
        } else {
            stepHolder.textView.setText(STEP + (i) + ": " + recipe.getSteps().get(i - 1).getSummary());
        }


        stepHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Container.isIsBigScreen()) {
                    if (i > 0) {
                        step = recipe.getSteps().get(i - 1);
                        Container.setStep(step);
                        Container.setPositionInplayer(0);
                        Intent intent = new Intent(context, StepActivity.class);
                        intent.putExtra("step", step);
                        context.startActivity(intent);
                    } else {
                        ingredients = recipe.getIngredients();
                        Container.setState(null);
                        Intent intent = new Intent(context, IngredientActivity.class);
                        intent.putParcelableArrayListExtra("ingredients", (ArrayList<? extends Parcelable>) ingredients);
                        context.startActivity(intent);
                    }
                } else {
                    if (i == 0) {
                        Container.setState(null);
                        Container.setWhichSideView(false);
                        RecipeActivity.setSideView(i);
                    } else {
                        Container.setWhichSideView(true);
                        Container.setPositionInplayer(0);
                        RecipeActivity.setSideView(i - 1);
                    }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipe.getSteps().size() + 1;
    }

    public class StepHolder extends RecyclerView.ViewHolder {
        TextView textView;

        public StepHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.recipe_step_item_txt);
        }
    }
}
