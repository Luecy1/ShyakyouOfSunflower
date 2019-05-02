package com.github.luecy1.shyakyouofsunflower.viewmodels

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.InstrumentationRegistry
import com.github.luecy1.shyakyouofsunflower.data.AppDatabase
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.data.PlantRepository
import com.github.luecy1.shyakyouofsunflower.utils.getValue
import com.github.luecy1.shyakyouofsunflower.utils.testPlant
import com.github.luecy1.shyakyouofsunflower.viewmodel.PlantDetailViewModel
import org.junit.After
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PlantDetailViewModelTest {

    private lateinit var appDatabase: AppDatabase
    private lateinit var viewModel: PlantDetailViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getTargetContext()
        appDatabase = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()

        val plantRepo = PlantRepository.getInstance(appDatabase.plantDao())
        val gardenPlantingRepo = GardenPlantingRepository.getInstance(
            appDatabase.gardenPlantingDao()
        )
        viewModel = PlantDetailViewModel(plantRepo, gardenPlantingRepo, testPlant.plantId)
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    @Test
    @Throws(InterruptedException::class)
    fun testDefaultValues() {
        assertFalse(getValue(viewModel.isPlanted))
    }
}