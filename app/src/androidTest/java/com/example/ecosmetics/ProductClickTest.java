package com.example.ecosmetics;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class ProductClickTest {
    @Rule
    public ActivityTestRule<ProductDetailedActivity> activity_productdetailActivityTestRule = new ActivityTestRule<>(ProductDetailedActivity.class);

    @Test
    public void ProductClickTest(){
        onView(withId(R.id.productimg)).perform(click());
    }
}
