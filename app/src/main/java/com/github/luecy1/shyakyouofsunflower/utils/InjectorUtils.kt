package com.github.luecy1.shyakyouofsunflower.utils

import android.content.Context
import com.github.luecy1.shyakyouofsunflower.data.AppDatabase
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.viewmodel.GardenPlantingListViewModelFactory

object InjectorUtils {

    private fun getPlantRepository(context: Context) {
        // TODO
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
}