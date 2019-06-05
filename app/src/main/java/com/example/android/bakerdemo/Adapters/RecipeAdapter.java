package com.example.android.bakerdemo.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakerdemo.Activities.RecipeActivity;
import com.example.android.bakerdemo.Classes.Container;
import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Recipe;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.RecipeHolder> {
    Context context;
    List<Recipe> recipes;

    public RecipeAdapter(Context context) {
        this.context = context;

    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @NonNull
    @Override
    public RecipeHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_item, viewGroup, false);
        RecipeHolder recipeHolder = new RecipeHolder(view);
        return recipeHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecipeHolder recipeHolder, int i) {
        recipeHolder.recipeName.setText(recipes.get(i).getName());
        recipeHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, RecipeActivity.class);
                intent.putExtra("recipe", recipes.get(i));
                Container.setRecipe(recipes.get(i));
                Container.setState(null);
               context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class RecipeHolder extends RecyclerView.ViewHolder {
        TextView recipeName;

        public RecipeHolder(View itemView) {
            super(itemView);
            recipeName = itemView.findViewById(R.id.recipe_item_txt);

        }
    }
}
