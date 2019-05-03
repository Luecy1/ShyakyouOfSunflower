package com.github.luecy1.shyakyouofsunflower.worker

import android.content.Context
import androidx.work.WorkManager
import org.junit.Before
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class SeedDatabaseWorkerTest {

    private lateinit var context: Context
    private lateinit var workManager: WorkManager

    @Before
    fun setup() {
//        context = ApplicationProvider
        //ApplicationProviderが解決できないため断念
    }
}