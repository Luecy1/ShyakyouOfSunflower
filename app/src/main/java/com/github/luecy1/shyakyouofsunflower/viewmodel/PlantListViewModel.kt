package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.github.luecy1.shyakyouofsunflower.data.Plant
import com.github.luecy1.shyakyouofsunflower.data.PlantRepository

class PlantListViewModel internal constructor(plantRepository: PlantRepository) : ViewModel() {

    private val growZoneNumber = MutableLiveData<Int>().apply { value = NO_GROW_ZONE }

    val plants: LiveData<List<Plant>> = Transformations.switchMap(growZoneNumber) {
        if (it == NO_GROW_ZONE) {
            plantRepository.getPlants()
        } else {
            plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        growZoneNumber.value = num
    }

    fun clearGrowZoneNumber() {
        growZoneNumber.value = NO_GROW_ZONE
    }

    fun isFiltered() = growZoneNumber.value != NO_GROW_ZONE

    companion object {
        private const val NO_GROW_ZONE = -1
    }
}