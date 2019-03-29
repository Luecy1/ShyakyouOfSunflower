package com.github.luecy1.shyakyouofsunflower.data

import androidx.room.TypeConverter
import java.util.Calendar

class Converters {
    @TypeConverter
    fun calenderToDatestamp(calendar: Calendar): Long {
        return calendar.timeInMillis
    }

    @TypeConverter
    fun datedtampToCalender(value: Long): Calendar {
        return Calendar.getInstance().apply { timeInMillis = value }
    }
}