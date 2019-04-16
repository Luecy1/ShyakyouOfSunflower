package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.data.PlantRepository

class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, gardenPlantingRepository, plantId) as T
    }
}