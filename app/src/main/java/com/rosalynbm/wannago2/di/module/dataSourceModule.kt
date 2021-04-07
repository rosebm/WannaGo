package com.rosalynbm.wannago2.di.module

import com.rosalynbm.wannago2.api.PlaceRemoteDataSource
import com.rosalynbm.wannago2.api.RetrofitFactory
import com.rosalynbm.wannago2.data.PoiDataSource
import com.rosalynbm.wannago2.data.local.PoiLocalRepository
import org.koin.dsl.module

val dataSourceModule = module {
    val api = RetrofitFactory.makeRetrofitService()

    single {
        PoiLocalRepository(get()) as PoiDataSource
    }

    single {
        PlaceRemoteDataSource(api)
    }

}