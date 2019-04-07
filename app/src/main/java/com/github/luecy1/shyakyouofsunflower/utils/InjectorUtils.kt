package com.github.luecy1.shyakyouofsunflower.utils

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.github.luecy1.shyakyouofsunflower.data.AppDatabase
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.data.PlantRepository
import com.github.luecy1.shyakyouofsunflower.viewmodel.GardenPlantingListViewModelFactory
import com.github.luecy1.shyakyouofsunflower.viewmodel.PlantListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context): PlantRepository {
        return PlantRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).plantDao()
        )
    }

    private fun getGardenPlantingRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao()
        )
    }

    fun provideGardenPlantingListModelFactory(context: Context)
        : GardenPlantingListViewModelFactory {
        val repository = getGardenPlantingRepository(context)
        return GardenPlantingListViewModelFactory(repository)
    }

    fun providePlantListModelFactory(context: Context): ViewModelProvider.Factory? {
        val repository = getPlantRepository(context)
        return PlantListViewModelFactory(repository)
    }
}