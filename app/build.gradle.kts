import org.jetbrains.kotlin.konan.properties.Properties

plugins {
    id(Plugins.androidApp)
    id(Plugins.googleServices)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.androidxNavSafeargs)
    kotlin(Plugins.kotlinAndroidExtensions)
    id("kotlin-android")
}

fun getPassword(): String  {
    var pass = ""
    val props = Properties()
    val propFile = file("../signing/apikey.properties")

    if (propFile.canRead()) {
        props.load(project.rootProject.file(propFile).inputStream())

        pass = props.getProperty("STORE_PASSWORD") ?: ""
    }

    return pass
}

android {
    compileSdkVersion(AppConfig.compileSdk)
    buildToolsVersion(AppConfig.buildToolsVersion)

    signingConfigs {
        create("release") {

            storeFile = file("../signing/wannago.keystore")
            storePassword = getPassword()
            keyAlias = "wannago_key"
            keyPassword = getPassword()

        }
    }

    defaultConfig {
        applicationId = "com.rosalynbm.wannago"
        minSdkVersion(AppConfig.minSdk)
        targetSdkVersion(AppConfig.targetSdk)
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
        resConfigs("en") //To limit the languages available from Firebase translations
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    viewBinding {
        android.buildFeatures.viewBinding = true
    }

    buildFeatures {
        dataBinding = true
    }

    packagingOptions {
        exclude("META-INF/notice.txt")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "Dependencies", "include" to listOf("*.jar"))))

    /*implementation platform('com.google.firebase:firebase-bom:26.6.0')
    // Add the dependency for the Firebase SDK for Google Analytics
    // When using the BoM, don't specify versions in Firebase dependencies
    implementation 'com.google.firebase:firebase-analytics-ktx'*/
    // App dependencies
    implementation(Dependencies.appCompact)
    implementation(Dependencies.legacySupport)
    implementation(Dependencies.annotations)

    //implementation(Dependencies.cardView)
    implementation(Dependencies.material)
    implementation(Dependencies.recyclerView)
    implementation(Dependencies.constraintLayout)
    implementation(Dependencies.coreKtx)

    implementation(Dependencies.gson)

    // Architecture Components
    //Navigation dependencies
    implementation(Dependencies.appCompact)
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.3.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1")
    implementation("androidx.recyclerview:recyclerview:1.1.0")
    implementation("com.google.android.gms:play-services-maps:17.0.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    kapt(Dependencies.lifecycleCompiler)
    implementation(Dependencies.lifecycleExt)
    implementation(Dependencies.lifecycleViewModel)
    implementation(Dependencies.lifecycleLivedata)
    implementation(Dependencies.navigationFragment)
    implementation(Dependencies.navigationUi)
    //implementation(Dependencies.espressoIdlingResource)

    //Room dependencies
    implementation(Dependencies.room)
    implementation(Dependencies.roomRuntime)
    kapt(Dependencies.roomCompiler)

    //Coroutines Dependencies
    implementation(Dependencies.coroutine)

    //Koin
    implementation(Dependencies.android)
    implementation(Dependencies.viewModel)

    implementation(Dependencies.firebaseUi)
    // Maps & Geofencing
    implementation(Dependencies.location)
    implementation(Dependencies.maps)
    implementation(Dependencies.timber)
    // Once https://issuetracker.google.com/127986458 is fixed this can be testImplementation
    //implementation(Dependencies.androidxFragmentTesting)
    //implementation(Dependencies.testCore)
    implementation(Dependencies.fragment)


 /*   // Dependencies for local unit tests
    testImplementation(Dependencies.junit)
    testImplementation(Dependencies.hamcrest)
    testImplementation(Dependencies.Androidx.aarchCoreTesting)
    testImplementation(Dependencies.Kotlin.coroutine)
    testImplementation(Dependencies.Kotlin.coroutineTest)
    testImplementation(Dependencies.Roboelectric.roboelectric)
    testImplementation(Dependencies.Google.truth)
    // Required if you want to use Mockito for unit tests
    testImplementation(Dependencies.mockitoCore)

    // AndroidX Test - JVM testing
    testImplementation(Dependencies.Androidx.testCoreKtx)
    testImplementation(Dependencies.Androidx.testJunit)
    testImplementation(Dependencies.Androidx.testRules)*/

    // AndroidX Test - Instrumented testing
 /*   androidTestImplementation(Dependencies.Androidx.testCoreKtx)
    androidTestImplementation(Dependencies.Androidx.testJunit)
    androidTestImplementation(Dependencies.Kotlin.coroutineTest)
    androidTestImplementation(Dependencies.Androidx.testRules)
    androidTestImplementation(Dependencies.Androidx.roomTesting)
    androidTestImplementation(Dependencies.Androidx.aarchCoreTesting)
    androidTestImplementation(Dependencies.Roboelectric.annotations)
    androidTestImplementation(Dependencies.Androidx.espressoCore)
    androidTestImplementation(Dependencies.Androidx.espressoContrib)
    androidTestImplementation(Dependencies.Androidx.espressoIntents)
    androidTestImplementation(Dependencies.Androidx.espressoIdlingConcurrent)
    androidTestImplementation(Dependencies.junit)*/

    //androidTestImplementation(Dependencies.mockitoAndroid)

   /* androidTestImplementation(Dependencies.mockitoCore)
    // Commented because I was getting this error: https://github.com/InsertKoinIO/koin/issues/287
    //androidTestImplementation(Dependencies.linkedinDexmakerMockito)
    androidTestImplementation(Dependencies.Koin.test) { exclude ("org.mockito", "mockito")}
*/
}
