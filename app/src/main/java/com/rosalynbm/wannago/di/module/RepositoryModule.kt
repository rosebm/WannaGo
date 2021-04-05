package com.rosalynbm.wannago.di.module

import com.rosalynbm.wannago.data.local.LocalDB
import com.rosalynbm.wannago.data.local.PoiLocalRepository
import com.rosalynbm.wannago.repository.PlacesRepository
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