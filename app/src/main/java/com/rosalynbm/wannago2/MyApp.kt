package com.rosalynbm.wannago2

import android.app.Application
import com.rosalynbm.wannago2.di.module.*
import com.rosalynbm.wannago2.util.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        //  To start checking the network state, we fire up the callback
        NetworkMonitor(this).startNetworkCallback()
        val preferences = this.getSharedPreferences("WannaGo", 0)

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(viewModelModule, repositoryModule, dataSourceModule, databaseModule))
        }
    }
}