package com.rosalynbm.wannago2.di.module

import com.rosalynbm.wannago2.data.local.LocalDB
import com.rosalynbm.wannago2.data.local.PoiLocalRepository
import com.rosalynbm.wannago2.repository.PlacesRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PoiLocalRepository(poiDao = get())
    }

    single {
        LocalDB.createPlacesDao(androidContext())
    }

    single {
        PlacesRepository(placeRemoteDataSource = get())
    }

}