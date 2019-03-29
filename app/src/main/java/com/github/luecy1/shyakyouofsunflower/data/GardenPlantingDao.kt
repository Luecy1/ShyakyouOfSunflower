package com.github.luecy1.shyakyouofsunflower.data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query

@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT * FROM garden_plantings WHERE id = :gardenPlantingId ")
    fun getGardenPlanting(gardenPlantingId: Long): LiveData<GardenPlanting>
}