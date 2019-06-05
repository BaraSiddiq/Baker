package com.example.android.bakerdemo.Classes;

import android.os.Parcelable;

import com.example.android.bakerdemo.Recipe.Ingredient;
import com.example.android.bakerdemo.Recipe.Recipe;
import com.example.android.bakerdemo.Recipe.Step;

import java.util.List;

public class Container {

    private static Recipe recipe;
    private static Step step;
    private static List<Ingredient> ingredients;
    private static boolean isBigScreen = false;
    private static long positionInplayer = 0;
    private static Parcelable state = null;



    public static Parcelable getState() {
        return state;
    }

    public static void setState(Parcelable state) {
        Container.state = state;
    }



    public static boolean isWhichSideView() {
        return whichSideView;
    }

    public static void setWhichSideView(boolean whichSideView) {
        Container.whichSideView = whichSideView;
    }

    private static boolean whichSideView = false;

    public static List<Ingredient> getIngredients() {
        return ingredients;
    }

    public static void setIngredients(List<Ingredient> ingredients) {
        Container.ingredients = ingredients;
    }

    public static long getPositionInplayer() {
        return positionInplayer;
    }

    public static void setPositionInplayer(long positionInplayer) {
        Container.positionInplayer = positionInplayer;
    }

    public static boolean isIsBigScreen() {
        return isBigScreen;
    }

    public static void setIsBigScreen(boolean isBigScreen) {
        Container.isBigScreen = isBigScreen;
    }

    public static Recipe getRecipe() {
        return recipe;
    }

    public static void setRecipe(Recipe recipe) {
        Container.recipe = recipe;
    }

    public static Step getStep() {
        return step;
    }

    public static void setStep(Step step) {
        Container.step = step;
    }

}
