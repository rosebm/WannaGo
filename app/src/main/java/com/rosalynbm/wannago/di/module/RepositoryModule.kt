package com.rosalynbm.wannago.di.module

import com.rosalynbm.wannago.data.local.LocalDB
import com.rosalynbm.wannago.data.local.PlacesLocalRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PlacesLocalRepository(get())
    }

    single {
        LocalDB.createPlacesDao(androidContext())
    }

}