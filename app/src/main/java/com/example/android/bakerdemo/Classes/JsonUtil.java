package com.example.android.bakerdemo.Classes;

import com.example.android.bakerdemo.Recipe.Ingredient;
import com.example.android.bakerdemo.Recipe.Recipe;
import com.example.android.bakerdemo.Recipe.Step;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtil {





    public static List<Recipe> recipeExtractor(String json) throws JSONException {
        Recipe recipe;
        Step step;
        Ingredient ingredient;
        List<Recipe> recipes = new ArrayList<>();
        List<Ingredient> ingredients = new ArrayList<>();
        List<Step> steps = new ArrayList<>();

        int jRecipeId, jStepId, jServings, jQuantity;
        String jName, jImg, jMeasure, jIngredient, jSummary, jDescription, jVid, jThumbnail;

        JSONObject root = new JSONObject(json);
        JSONArray rootArray = root.getJSONArray("rootArray");

        for (int i = 0; i < rootArray.length(); i++) {
            recipe = new Recipe();
            ingredients = new ArrayList<>();
            steps = new ArrayList<>();
            JSONObject recipeObj = rootArray.getJSONObject(i);

            jRecipeId = recipeObj.getInt("id");
            jName = recipeObj.getString("name");
            jServings = recipeObj.getInt("servings");
            jImg = recipeObj.getString("image");

            JSONArray ingredientsArray = recipeObj.getJSONArray("ingredients");
            JSONArray stepsArray = recipeObj.getJSONArray("steps");

            for (int j = 0; j < ingredientsArray.length(); j++) {
                JSONObject ingredientObj = ingredientsArray.getJSONObject(j);
                jQuantity = ingredientObj.getInt("quantity");
                jMeasure = ingredientObj.getString("measure");
                jIngredient = ingredientObj.getString("ingredient");

                ingredients.add(new Ingredient(jQuantity, jMeasure, jIngredient));
            }

            for (int k = 0; k < stepsArray.length(); k++) {
                JSONObject stepObj = stepsArray.getJSONObject(k);
                jStepId = stepObj.getInt("id");
                jSummary = stepObj.getString("shortDescription");
                jDescription = stepObj.getString("description");
                jVid = stepObj.getString("videoURL");
                jThumbnail = stepObj.getString("thumbnailURL");

                steps.add(new Step(jStepId, jSummary, jDescription, jVid, jThumbnail));

            }

            recipe.setRecipe(jRecipeId, jName, jServings, jImg, ingredients, steps);

            recipes.add(recipe);


        }

        return recipes;
    }


}
