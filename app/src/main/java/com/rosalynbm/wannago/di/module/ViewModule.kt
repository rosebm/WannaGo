package com.rosalynbm.wannago.di.module

import android.app.Application
import android.content.SharedPreferences
import com.rosalynbm.wannago.ui.place.PlaceViewModel
import com.rosalynbm.wannago.ui.login.LoginViewModel
import com.rosalynbm.wannago.ui.map.MapViewModel
import com.rosalynbm.wannago.ui.poilist.PoisListViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Collection of dependencies we are going to provide to the application
val viewModelModule = module {

    fun getSharedPrefs(androidApplication: Application): SharedPreferences {
        return  androidApplication.getSharedPreferences("WannaGoReminder",  android.content.Context.MODE_PRIVATE)
    }

    viewModel {
        LoginViewModel(app = get(), context = get(), sharedPref = get())
    }

    viewModel{
        MapViewModel(app = get(), dataSource = get())
    }

    viewModel {
        PoisListViewModel(application = get(), dataSource =  get())
    }

    viewModel {
        PlaceViewModel(application = get())
    }

    single {
        getSharedPrefs(androidApplication())
    }
}