package com.rosalynbm.wannago2.di.module

import com.rosalynbm.wannago2.repository.PlacesRepository
import com.rosalynbm.wannago2.repository.PoiRepository
import org.koin.dsl.module

val repositoryModule = module {
    single {
        PoiRepository(localDataSource = get())
    }

    single {
        PlacesRepository(remoteDataSource = get(), localDataSource = get())
    }

}