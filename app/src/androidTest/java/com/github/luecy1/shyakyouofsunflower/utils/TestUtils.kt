package com.github.luecy1.shyakyouofsunflower.utils

import android.app.Activity
import android.content.Intent
import android.widget.Toolbar
import androidx.test.espresso.intent.matcher.IntentMatchers.hasAction
import androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra
import com.github.luecy1.shyakyouofsunflower.data.GardenPlanting
import com.github.luecy1.shyakyouofsunflower.data.Plant
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import java.util.*

val testPlants = arrayListOf(
    Plant("1", "Apple", "A red fruit", 1),
    Plant("2", "B", "Description B", 1),
    Plant("3", "C", "Description C", 2)
)

val testPlant = testPlants[0]

val testCalendar: Calendar = Calendar.getInstance().apply {
    set(Calendar.YEAR, 1998)
    set(Calendar.MONTH, Calendar.SEPTEMBER)
    set(Calendar.DAY_OF_MONTH, 4)
}

val testGardenPlanting = GardenPlanting(testPlant.plantId, testCalendar, testCalendar)

fun getToolbarNAbigationContentDescription(activity: Activity, toolbarId: Int) =
    activity.findViewById<Toolbar>(toolbarId).navigationContentDescription as String

fun chooser(matcher: Matcher<Intent>): Matcher<Intent> = allOf(
    hasAction(Intent.ACTION_CHOOSER),
    hasExtra(`is`(Intent.EXTRA_INTENT), matcher)
)