plugins {
    id(BuildPlugins.androidLibrary)
    id(BuildPlugins.kotlinAndroid)
    id(BuildPlugins.kotlinAndroidExt)
    id(BuildPlugins.kotlinKapt)
}


android {
    compileSdkVersion(App.compileSdkVersion)

    defaultConfig {
        minSdkVersion(App.minSdkVersion)
        targetSdkVersion(App.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {
    implementation(BuildPlugins.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.coreKtx)
    implementation(Libs.constraintLayout)

    // Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

    implementation("androidx.recyclerview:recyclerview:1.1.0")

    // Epoxy
    implementation(Libs.epoxy)
    kapt(Libs.epoxyProcessor)
    implementation(Libs.epoxyDataBinding)

    // JVM tests
    testImplementation(TestLibs.junit)

    // UI Test
    androidTestImplementation(InstrumentationTestLibs.junit)
    androidTestImplementation(InstrumentationTestLibs.espressoCore)


}
