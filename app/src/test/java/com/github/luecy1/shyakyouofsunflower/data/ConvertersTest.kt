package com.github.luecy1.shyakyouofsunflower.data


import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*
import java.util.Calendar.*

class ConvertersTest {

    private val cal = Calendar.getInstance().apply {
        set(YEAR, 1998)
        set(MONTH, SEPTEMBER)
        set(DAY_OF_MONTH, 4)
    }

    @Test
    fun calendarToDatestamp() {
        assertEquals(cal.timeInMillis, Converters().calenderToDatestamp(cal))
    }

    @Test
    fun datestampToCalendar() {
        assertEquals(Converters().datedtampToCalender(cal.timeInMillis), cal)
    }
}