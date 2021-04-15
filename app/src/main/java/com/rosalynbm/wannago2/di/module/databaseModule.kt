package com.rosalynbm.wannago2.di.module

import androidx.room.Room
import com.rosalynbm.wannago2.data.local.PoiDatabase
import com.rosalynbm.wannago2.util.Constants
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(androidApplication(), PoiDatabase::class.java,
            Constants.DATABASE_NAME).build()
    }

    single { get<PoiDatabase>().poiDao() }

    single { get<PoiDatabase>().placeDao() }

}