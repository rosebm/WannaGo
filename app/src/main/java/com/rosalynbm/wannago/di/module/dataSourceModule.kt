package com.rosalynbm.wannago.di.module

import com.rosalynbm.wannago.data.PlaceDataSource
import com.rosalynbm.wannago.data.local.PlacesLocalRepository
import org.koin.dsl.module

val dataSourceModule = module {

    single {
        PlacesLocalRepository(get()) as PlaceDataSource
    }
}