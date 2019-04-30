package com.github.luecy1.shyakyouofsunflower.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4
import com.github.luecy1.shyakyouofsunflower.utils.getValue
import org.hamcrest.Matchers.equalTo
import org.junit.After
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PlantDaoTest {

    private lateinit var database: AppDatabase
    private lateinit var plantDao: PlantDao
    private val plantA = Plant("1", "A", "", 1, 1, "")
    private val plantB = Plant("2", "B", "", 1, 1, "")
    private val plantC = Plant("3", "C", "", 2, 2, "")

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()


    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        plantDao = database.plantDao()

        plantDao.insertAll(listOf(plantB, plantC, plantA))
    }

    @After
    fun closeDb() {
        database.close()
    }

    @Test
    fun testGetPlants() {
        val plantList = getValue(plantDao.getPlants())
        assertThat(plantList.size, equalTo(3))

        assertThat(plantList[0], equalTo(plantA))
        assertThat(plantList[1], equalTo(plantB))
        assertThat(plantList[2], equalTo(plantC))
    }


}