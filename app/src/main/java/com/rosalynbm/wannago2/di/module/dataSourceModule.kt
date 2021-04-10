package com.rosalynbm.wannago2.di.module

import com.rosalynbm.wannago2.api.PlaceRemoteDataSource
import com.rosalynbm.wannago2.api.RetrofitFactory
import com.rosalynbm.wannago2.data.local.PlaceLocalDataSource
import com.rosalynbm.wannago2.data.local.PoiLocalDataSource
import org.koin.dsl.module

val dataSourceModule = module {
    val api = RetrofitFactory.makeRetrofitService()

  /*  single {
        PoiRepository(get()) as PoiDataSource
    }*/

    single {
        PlaceRemoteDataSource(api)
    }

    single {
        PlaceLocalDataSource(dao = get())
    }

    single {
        PoiLocalDataSource(poiDao = get())
    }

 /*  single {
        LocalDB.createPlacesDao(androidContext())
    }*/

}