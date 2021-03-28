/**
 * Dependency coordinates.
 */
object Dependencies {

    const val annotations = "androidx.annotation:annotation:${Versions.androidXAnnotations}"
    //ros const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlin_version"
    // Android KTX for framework API
    const val coreKtx = "androidx.core:core-ktx:${Versions.androidXCoreKtx}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraint}"
    const val fragment = "androidx.fragment:fragment:${Versions.fragment}"
    //Maps & Geofencing
    const val location = "com.google.android.gms:play-services-location:${Versions.playServices}"
    const val maps = "com.google.android.gms:play-services-maps:${Versions.playServices}"
    const val timber = "com.jakewharton.timber:timber:${Versions.timber}"
    const val legacySupport = "androidx.legacy:legacy-support-v4:${Versions.androidXLegacySupport}"
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.archLifecycle}"
    const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.archLifecycle}"
    const val lifecycleExt = "androidx.lifecycle:lifecycle-extensions:${Versions.archLifecycle}"
    const val lifecycleCompiler = "androidx.lifecycle:lifecycle-compiler:${Versions.archLifecycle}"
    const val gson = "com.google.code.gson:gson:${Versions.googleGson}"
    //Room dependencies
    const val room = "androidx.room:room-ktx:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val firebaseUi = "com.firebaseui:firebase-ui-auth:${Versions.firebaseUi}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"


    const val navigationFragment = "androidx.navigation:navigation-fragment-ktx:${Versions.androidXNavigation}"
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Versions.androidXNavigation}"
    //Koin
    const val android = "org.koin:koin-android:${Versions.koin}"
    const val test = "org.koin:koin-test:${Versions.koin}"
    const val viewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    //Coroutines Dependencies
    const val coroutine = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

}