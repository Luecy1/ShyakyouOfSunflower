package com.github.luecy1.shyakyouofsunflower.workers

import android.content.Context
import android.util.JsonReader
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.github.luecy1.shyakyouofsunflower.utils.PLANT_DATA_FILENAME
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope

class SeedDatabaseWorker(
    context: Context,
    workerParams: WorkerParameters
) : CoroutineWorker(context, workerParams) {

    private val TAG by lazy { SeedDatabaseWorker::class.java.simpleName }
    override val coroutineContext = Dispatchers.IO

    override suspend fun doWork(): Result = coroutineScope {

        try {
            // applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
            //     JsonReader(inputStream.reader()).use { jsonReader ->
            //         // val plantType = object : TypeToken<List<Plant>>() {}.type
            //         // val plantList: List<Plant> = Gson().fromJson(jsonReader, plantType)
            //         // val database = AppDatabase.getInstance(applicationContext)
            //
            //         Result.success()
            //     }
            // }

            Result.success()
        } catch (ex: Exception) {
            Log.e(TAG, "Error seeding database", ex)
            Result.failure()
        }
    }
}
