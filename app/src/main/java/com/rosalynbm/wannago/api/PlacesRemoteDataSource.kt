package com.rosalynbm.wannago.api

class PlacesRemoteDataSource { //}: RetrofitService {
    val retrofitService = RetrofitFactory.makeRetrofitService()

   /* override suspend fun getPlaces(): Response<List<Place>> {
        CoroutineScope(Dispatchers.IO).launch {
            val response = retrofitService.getPlaces()
            withContext(Dispatchers.Main) {
                try {
                    if (response.isSuccessful) {
                        //Do something with response e.g show to the UI.
                        response
                    } else {
                        Timber.e("Error: ${response.code()}")
                    }
                } catch (e: HttpException) {
                    Timber.e("Exception ${e.message}")
                } catch (e: Throwable) {
                    Timber.e("Ooops: Something else went wrong")
                }
            }
        }
    }*/

    /*  private val retrofitService: RetrofitService = buildApi()

      private fun buildApi(): RetrofitService {

          // Moshi converts JSON into Kotlin objects
          val moshi = Moshi.Builder()
              .add(KotlinJsonAdapterFactory())
              .build()

          return Retrofit.Builder()
              // For when a Json response cannot be parsed directly using Moshi.
              // Order matters, scalar must come first
              .addConverterFactory(ScalarsConverterFactory.create())
              // So Moshi annotations can work properly with Kotlin
              .addConverterFactory(MoshiConverterFactory.create(moshi))
              .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
              .baseUrl(Constants.BASE_URL)
              .build()
              .create(RetrofitService::class.java)
      }*/


}