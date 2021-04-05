package com.rosalynbm.wannago.di.module

import com.rosalynbm.wannago.api.PlaceRemoteDataSource
import com.rosalynbm.wannago.api.RetrofitFactory
import com.rosalynbm.wannago.api.RetrofitService
import com.rosalynbm.wannago.data.PoiDataSource
import com.rosalynbm.wannago.data.local.PoiLocalRepository
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