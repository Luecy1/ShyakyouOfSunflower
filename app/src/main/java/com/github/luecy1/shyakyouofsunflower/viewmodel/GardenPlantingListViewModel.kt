package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.lifecycle.ViewModel
import com.github.luecy1.shyakyouofsunflower.data.GardenPlantingRepository

class GardenPlantingListViewModel(
    gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val gardenPlantings = gardenPlantingRepository
}
