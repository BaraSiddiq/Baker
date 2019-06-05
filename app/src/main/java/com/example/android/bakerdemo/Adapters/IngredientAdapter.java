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

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter<IngredientAdapter.IngredientHolder> {
    private final String QTY = "Quantity: ";
    private final String MEASURE = "Measure: ";
    private final String INGREDIENT = "Ingredient: ";
    Context context;
    List<Ingredient> ingredients;


    public IngredientAdapter(Context context, List<Ingredient> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.ingredient_item, viewGroup, false);
        IngredientHolder ingredientHolder = new IngredientHolder(view);
        return ingredientHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientHolder ingredientHolder, int i) {
        ingredientHolder.qtyText.setText(QTY.concat(String.valueOf(ingredients.get(i).getQuantity())));
        ingredientHolder.measureText.setText(MEASURE.concat(ingredients.get(i).getMeasure()));
        ingredientHolder.ingredientText.setText(INGREDIENT.concat(ingredients.get(i).getIngredient()));

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    public class IngredientHolder extends RecyclerView.ViewHolder {

        TextView qtyText;
        TextView measureText;
        TextView ingredientText;

        public IngredientHolder(@NonNull View itemView) {
            super(itemView);
            qtyText = itemView.findViewById(R.id.qty_txt);
            measureText = itemView.findViewById(R.id.measure_txt);
            ingredientText = itemView.findViewById(R.id.ingredient_txt);
        }
    }

}
