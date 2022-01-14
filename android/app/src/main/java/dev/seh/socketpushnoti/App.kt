package dev.seh.socketpushnoti;

import android.app.Application
import androidx.work.*
import dev.seh.socketpushnoti.service.SharedService
import dev.seh.socketpushnoti.service.WorkMangerEx
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

/**
 * @author : seungHo
 * @since : 2022-01-05
 * class : App.java
 * github : devaspirant0510
 * email : seungho020510@gmail.com
 * description :
 */
class App:Application() {
    private val backgroundCoroutineScope = CoroutineScope(Dispatchers.Default)
    companion object {
        lateinit var INSTANCE: App
            private set

        lateinit var sharedPreference:SharedService
    }
    private fun delayCreateWork(){
        backgroundCoroutineScope.launch {
            createWorkManager()
        }
    }

    override fun onCreate() {
        super.onCreate()
        INSTANCE = this
        sharedPreference = SharedService(this)
        CoroutineScope(Dispatchers.Default).launch {
            Timber.plant(Timber.DebugTree())
        }
        delayCreateWork()

    }
    private fun createWorkManager(){
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .setRequiresBatteryNotLow(true)
            .setRequiresCharging(true)
            .setRequiresStorageNotLow(true)
            .build()

        val oneTimeWorkRequest = OneTimeWorkRequestBuilder<WorkMangerEx>().
        setConstraints(constraints).build()

        WorkManager.getInstance(applicationContext).enqueueUniqueWork("aaa", ExistingWorkPolicy.KEEP, oneTimeWorkRequest)
    }
}