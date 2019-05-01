package com.github.luecy1.shyakyouofsunflower.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.github.luecy1.shyakyouofsunflower.utils.*
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertNull
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class GardenPlantingDaoTest {
    private lateinit var database: AppDatabase
    private lateinit var gardenPlantingDao: GardenPlantingDao
    private var testGardenPlantingId = 0L

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        gardenPlantingDao = database.gardenPlantingDao()

        database.plantDao().insertAll(testPlants)
        testGardenPlantingId = gardenPlantingDao.insertGardenPlanting(testGardenPlanting)
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetGardenPlantings() {
        val gardenPlantings2 = GardenPlanting(
            testPlants[1].plantId,
            testCalendar,
            testCalendar
        ).also { it.gardenPlantingId = 2L }
        gardenPlantingDao.insertGardenPlanting(gardenPlantings2)
        assertThat(getValue(gardenPlantingDao.getGardenPlantings()).size, equalTo(2))
    }

    @Test
    fun testGatGardenPlanting() {
        assertThat(
            getValue(gardenPlantingDao.getGardenPlanting(testGardenPlantingId)),
            equalTo(testGardenPlanting)
        )
    }

    @Test
    fun testDeleteGardenPlanting() {
        val gardenPlanting2 = GardenPlanting(
            testPlants[1].plantId,
            testCalendar,
            testCalendar
        ).also { it.gardenPlantingId = 2 }
        gardenPlantingDao.insertGardenPlanting(gardenPlanting2)
        assertThat(getValue(gardenPlantingDao.getGardenPlantings()).size, equalTo(2))
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting2)
        assertThat(getValue(gardenPlantingDao.getGardenPlantings()).size, equalTo(1))
    }

    @Test
    fun testGetGardenPlantingForPlant_notFound() {
        assertNull(getValue(gardenPlantingDao.getGardenPlanting4Plant(testPlants[2].plantId)))
    }

    @Test
    fun testGetPlantAndGardenPlantings() {
        val plantAndGardenPlantings = getValue(gardenPlantingDao.getPlantAndGardenPlantings())
        assertThat(plantAndGardenPlantings.size, equalTo(3))

        assertThat(plantAndGardenPlantings[0].plant, equalTo(testPlant))
        assertThat(plantAndGardenPlantings[0].gardenPlantings.size, equalTo(1))
        assertThat(plantAndGardenPlantings[0].gardenPlantings[0], equalTo(testGardenPlanting))

        assertThat(plantAndGardenPlantings[1].gardenPlantings.size, equalTo(0))
        assertThat(plantAndGardenPlantings[2].gardenPlantings.size, equalTo(0))
    }
}