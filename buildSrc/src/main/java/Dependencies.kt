/**
 * Dependency coordinates.
 */
object Dependencies {

    const val annotations = "androidx.annotation:annotation:${Versions.androidXAnnotations}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coil = "io.coil-kt:coil:${Versions.coilKt}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraint}"
    // Android KTX for framework API
    const val coreKtx = "androidx.core:core-ktx:${Versions.androidXCoreKtx}"
    //Coroutines Dependencies
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // Retrofit Coroutines Support
    const val coroutineAdapter =  "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutineAdapter}"
    const val firebaseUi = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUi}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    const val gson = "com.google.code.gson:gson:${Versions.googleGson}"
    //Koin
    const val koinAndroid = "org.koin:koin-android:${Versions.koin}"
    const val koinTest = "org.koin:koin-test:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.androidXLegacySupport}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archLifecycle}"
    const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archLifecycle}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
    //Maps & Geofencing
    const val location = "com.google.android.gms:play-services-location:${Versions.playServices}"
    const val maps = "com.google.android.gms:play-services-maps:${Versions.playServices}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidXNavigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.androidXNavigation}"
    const val okhttpInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttpInterceptor}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitConverterMoshi = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    //Room dependencies
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"

}