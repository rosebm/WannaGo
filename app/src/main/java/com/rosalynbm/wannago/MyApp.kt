package com.rosalynbm.wannago

import android.app.Application
import com.rosalynbm.wannago.di.module.viewModelModule
import com.rosalynbm.wannago.util.NetworkMonitor
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application()  {

    override fun onCreate() {
        super.onCreate()
        //  To start cheking the network state, we fire up the callback
        NetworkMonitor(this).startNetworkCallback()
        val preferences = this.getSharedPreferences("WannaGoReminder", 0)

        startKoin {
            androidContext(this@MyApp)
            modules(listOf(viewModelModule))
        }
    }
}