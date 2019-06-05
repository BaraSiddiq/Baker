package com.example.android.bakerdemo;


import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.bakerdemo.Activities.MainActivity;
import com.example.android.bakerdemo.Activities.RecipeActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mainActivityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void preTest() {
        Intents.init();

    }

    @Test
    public void mainTest() {

        onView(withId(R.id.recipe_rv)).perform(RecyclerViewActions.actionOnItemAtPosition(0, ViewActions.click()));

        intended(hasComponent(RecipeActivity.class.getName()));

    }

}
