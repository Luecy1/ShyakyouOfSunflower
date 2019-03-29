package com.github.luecy1.shyakyouofsunflower.viewmodel

import androidx.databinding.ObservableField
import androidx.databinding.ObservableInt
import androidx.lifecycle.ViewModel
import com.github.luecy1.shyakyouofsunflower.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat
import java.util.*

class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) : ViewModel() {

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]
    private val dateFormat by lazy { SimpleDateFormat("MMM d , yyyy", Locale.US) }

    val waterDateString = ObservableField<String>(dateFormat.format(gardenPlanting.lastWateringDate.time))
    val wateringInterval = ObservableInt(plant.wateringInterval)
    val imageUrl = ObservableField<String>(plant.imageUrl)
    val plantName = ObservableField<String>(plant.name)
    val plantDateString = ObservableField<String>(dateFormat.format(gardenPlanting.plantDate.time))
}