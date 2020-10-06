package com.example.ecosmetics;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class SignUpTest {
    @Rule
    public ActivityTestRule<SignupActivity> activityTestRule =
            new ActivityTestRule<>(SignupActivity.class);

    @Test
    public void checkSignup() throws Exception
    {
        onView(withId(R.id.firstname))
                .perform(typeText("seema"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.lastname))
                .perform(typeText("rai"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.address))
                .perform(typeText("Anamnagar"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.email))
                .perform(typeText("seema12@gmail.com"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.phoneno))
                .perform(typeText("9805328518"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.username))
                .perform(typeText("seema123"))
                .perform(closeSoftKeyboard());


        onView(withId(R.id.password))
                .perform(typeText("seema123"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.confirmpassword))
                .perform(typeText("seema123"))
                .perform(closeSoftKeyboard());

        onView(withId(R.id.btnsignup))
                .perform(click());

    }
}
