// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    val kotlin_version by extra("1.3.72")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath ("androidx.navigation:navigation-safe-args-gradle-plugin:2.3.3")
        classpath ("com.google.gms:google-services:${Versions.googleServices}")
        classpath("com.android.tools.build:gradle:${Versions.gradle}")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}")
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()  // Google's Maven repository
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
