package com.rosalynbm.wannago.di.module

import com.rosalynbm.wannago.data.PoiDataSource
import com.rosalynbm.wannago.data.local.PoiLocalRepository
import org.koin.dsl.module

val dataSourceModule = module {

    single {
        PoiLocalRepository(get()) as PoiDataSource
    }
}