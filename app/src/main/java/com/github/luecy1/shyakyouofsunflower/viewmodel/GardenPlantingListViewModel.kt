package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.data.PlantAndGardenPlantings

class GardenPlantingListViewModel(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val gardenPlantings = gardenPlantingRepository.getGardenPlantings()

    val plantAndGardenPlantings: LiveData<List<PlantAndGardenPlantings>> =
        Transformations.map(gardenPlantingRepository.getPlantAndGardenPlantings()) { plantings ->
            plantings.filter { it.gardenPlantings.isNotEmpty() }
        }
}
