package com.rosalynbm.wannago.di.module

import android.app.Application
import android.content.SharedPreferences
import com.rosalynbm.wannago.ui.LoginViewModel
import com.rosalynbm.wannago.ui.MapViewModel
import com.rosalynbm.wannago.ui.placelist.PlacesListViewModel
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

    single{
        MapViewModel(app = get(), dataSource = get())
    }

    single {
        PlacesListViewModel(application = get(), dataSource =  get())
    }

    single {
        getSharedPrefs(androidApplication())
    }
}