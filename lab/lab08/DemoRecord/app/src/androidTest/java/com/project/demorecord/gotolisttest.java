package com.project.demorecord;


import android.os.SystemClock;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static com.project.demorecord.TestUtils.withRecyclerView;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class gotolisttest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testgotolistnotfound() {
        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.textNotFound), withText("Not Found"),isDisplayed()));

    }



    @Test
    public void checkposition1() {

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ying"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPosition(0)).check(matches(hasDescendant(withText("Ying"))));

        onView(withRecyclerView(R.id.list).atPosition(0)).check(matches(hasDescendant(withText("20"))));

        SystemClock.sleep(1000);
    }

    @Test
    public void checkposition2() {

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Ladarat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("20"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPosition(1)).check(matches(hasDescendant(withText("Ladarat"))));

        onView(withRecyclerView(R.id.list).atPosition(1)).check(matches(hasDescendant(withText("20"))));

        SystemClock.sleep(1000);
    }
    @Test
    public void checkposition3() {

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Somkiat"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("80"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPosition(2)).check(matches(hasDescendant(withText("Somkiat"))));

        onView(withRecyclerView(R.id.list).atPosition(2)).check(matches(hasDescendant(withText("80"))));

        SystemClock.sleep(1000);
    }

    @Test
    public void checkposition4() {

        onView(allOf(withId(R.id.editTExtName), isDisplayed())).perform(replaceText("Prayoch"), closeSoftKeyboard());

        onView(allOf(withId(R.id.editTextAge), isDisplayed())).perform(replaceText("60"), closeSoftKeyboard());

        onView(allOf(withId(R.id.buttonAdded), withText("ADDED"), isDisplayed())).perform(click());

        onView(allOf(withId(R.id.buttonGotoList), withText("GO TO LIST"), isDisplayed())).perform(click());

        onView(withRecyclerView(R.id.list).atPosition(3)).check(matches(hasDescendant(withText("Prayoch"))));

        onView(withRecyclerView(R.id.list).atPosition(3)).check(matches(hasDescendant(withText("60"))));

        SystemClock.sleep(1000);
    }




    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
