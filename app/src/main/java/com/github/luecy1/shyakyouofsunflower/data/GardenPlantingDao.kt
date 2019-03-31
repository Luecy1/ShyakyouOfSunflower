package com.github.luecy1.shyakyouofsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId ")
    fun getGardenPlanting(gardenPlantingId: Long): LiveData<GardenPlanting>

    @Query("SELECT * FROM garden_plantings WHERE plant_id = :plantId")
    fun getGardenPlanting4Plant(plantId: String): LiveData<GardenPlanting>

    @Transaction
    @Query("SELECT * FROM plants")
    fun getPlantAndGardenPlantings(): LiveData<List<PlantAndGardenPlantings>>

}