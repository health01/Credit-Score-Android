package com.kst.creditscoreapp.ui.main


import android.view.View
import android.view.ViewGroup
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.runner.AndroidJUnit4
import com.kst.creditscoreapp.MockServer
import com.kst.creditscoreapp.R
import okhttp3.mockwebserver.MockWebServer
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    lateinit var mockWebServer: MockWebServer

    @Before
    fun setup() {
        mockWebServer = MockWebServer()
        mockWebServer.setDispatcher(MockServer.ResponseDispatcher())
        mockWebServer.start(8080)
    }

    @After
    fun dropdown() {
        mockWebServer.shutdown()
    }

    @Test
    fun mainActivityDisplayCompletedTask() {

        launchActivity()

        val textView1 = onView(
            allOf(
                withId(R.id.textview_title_1), withText("Your credit score is"),
                withParent(
                    allOf(
                        withId(R.id.circleView),
                        withParent(withId(R.id.custom_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView1.check(matches(isDisplayed()))

        val textView2 = onView(
            allOf(
                withId(R.id.textview_title_2), withText("514"),
                withParent(
                    allOf(
                        withId(R.id.circleView),
                        withParent(withId(R.id.custom_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView2.check(matches(isDisplayed()))

        val textView3 = onView(
            allOf(
                withId(R.id.textview_title_3), withText("out of 700"),
                withParent(
                    allOf(
                        withId(R.id.circleView),
                        withParent(withId(R.id.custom_view))
                    )
                ),
                isDisplayed()
            )
        )
        textView3.check(matches(isDisplayed()))

        val myDonutView = onView(
            allOf(
                withId(R.id.custom_view),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.nav_host_fragment_content_main),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        myDonutView.perform(click())

        val button = onView(
            allOf(
                withId(R.id.button_second), withText("PREVIOUS"),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val textviewDetail = onView(
            allOf(
                withId(R.id.textview_detail),
                withText("LocalScoreDetail(score=514, angle=262, color=-16711936, maxScore=700)"),
                withParent(withParent(withId(R.id.nav_host_fragment_content_main))),
                isDisplayed()
            )
        )
        textviewDetail.check(matches(withText("LocalScoreDetail(score=514, angle=262, color=-16711936, maxScore=700)")))
    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }

    private fun launchActivity(): ActivityScenario<MainActivity>? =
        ActivityScenario.launch(MainActivity::class.java)
}
