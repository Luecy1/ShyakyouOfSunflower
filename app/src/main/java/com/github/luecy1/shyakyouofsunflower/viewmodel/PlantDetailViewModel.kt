package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository
import com.github.luecy1.shyakyouofsunflower.data.Plant
import com.github.luecy1.shyakyouofsunflower.data.PlantRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class PlantDetailViewModel(
    plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    private val plantId: String
) : ViewModel() {

    val isPlanted: LiveData<Boolean>
    val plant: LiveData<Plant>

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

    init {

        val gardenPlantingForPlant = gardenPlantingRepository.getGardenPlantingForPlant(plantId)
        isPlanted = Transformations.map(gardenPlantingForPlant) { it != null }

        plant = plantRepository.getPlant(plantId)
    }

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

}