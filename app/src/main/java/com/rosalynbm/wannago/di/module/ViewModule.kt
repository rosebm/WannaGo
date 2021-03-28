package com.rosalynbm.wannago.di.module

import android.app.Application
import android.content.SharedPreferences
import com.rosalynbm.wannago.ui.LoginViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

// Collection of dependencies we are going to provide to the application
val viewModelModule = module {

    fun getSharedPrefs(androidApplication: Application): SharedPreferences {
        return  androidApplication.getSharedPreferences("WannaGoReminder",  android.content.Context.MODE_PRIVATE)
    }

    viewModel {
        LoginViewModel(get(), get(), get())
    }

    single {
        getSharedPrefs(androidApplication())
    }
}