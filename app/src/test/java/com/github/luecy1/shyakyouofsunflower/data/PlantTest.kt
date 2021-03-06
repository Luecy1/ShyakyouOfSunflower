package com.github.luecy1.shyakyouofsunflower.data

import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.util.*

class PlantTest {

    private lateinit var plant: Plant

    @Before
    fun setup() {
        plant = Plant("1", "Tomato", "A red vegetable", 1, 2, "")
    }

    @Test
    fun test_default_values() {
        val defaultPlant = Plant("2", "Apple", "Description", 1)
        assertEquals(7, defaultPlant.wateringInterval)
        assertEquals("", defaultPlant.imageUrl)
    }

    @Test
    fun test_shouldBeWatered() {
        Calendar.getInstance().let { now ->

            val lastWateringDate = Calendar.getInstance()

            // Test for lastWateringDate is today
            lastWateringDate.time = now.time
            assertFalse(plant.shoudBeWatered(now, lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -0) }))

            // Test for lastWateringDate is yesterday
            lastWateringDate.time = now.time
            assertFalse(plant.shoudBeWatered(now, lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -1) }))

            // Test for lastWateringDate is the day before yesterday
            lastWateringDate.time = now.time
            assertFalse(plant.shoudBeWatered(now, lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -2) }))

            // Test for lastWateringDate is some day ago, three day ago ,four day ago etc
            lastWateringDate.time = now.time
            assertTrue(plant.shoudBeWatered(now, lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, -3) }))
        }
    }

    @Test
    fun test_toString() {
        assertEquals("Tomato", plant.toString())
    }
}