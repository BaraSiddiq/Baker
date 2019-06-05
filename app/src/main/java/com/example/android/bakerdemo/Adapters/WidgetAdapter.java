package com.example.android.bakerdemo.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.bakerdemo.R;
import com.example.android.bakerdemo.Recipe.Ingredient;
import com.example.android.bakerdemo.Recipe.Recipe;

import java.util.List;

public class WidgetAdapter extends RecyclerView.Adapter<WidgetAdapter.WidgetIngredientHolder> {

    List<Recipe> recipes;
    Context context;

    public WidgetAdapter(List<Recipe> recipes, Context context) {
        this.recipes = recipes;
        this.context = context;
    }

    @NonNull
    @Override
    public WidgetIngredientHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.widget_item, viewGroup, false);
        WidgetIngredientHolder widgetIngredientHolder = new WidgetIngredientHolder(view);
        return widgetIngredientHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull WidgetIngredientHolder widgetIngredientHolder, int i) {
        String mH = "";
        String iH = "";
        String qH = "";
        String txtHolder = "";
        Ingredient ingredient;
        widgetIngredientHolder.title.setText(recipes.get(i).getName());
        widgetIngredientHolder.ingredient.setText("");
        for (int j = 0; j < recipes.get(i).getIngredients().size(); j++) {
            ingredient = recipes.get(i).getIngredients().get(j);
            mH = ingredient.getMeasure() + "\n";
            iH = ingredient.getIngredient() + "\n";
            qH = String.valueOf(ingredient.getQuantity()) + "\n";
            txtHolder = mH.concat(iH).concat(qH) + "\n\n";
            widgetIngredientHolder.ingredient.setText(widgetIngredientHolder.ingredient.getText() + txtHolder);
        }
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }

    public class WidgetIngredientHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView ingredient;

        public WidgetIngredientHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.recipe_title);
            ingredient = itemView.findViewById(R.id.recipe_ingredient);
        }
    }
}
