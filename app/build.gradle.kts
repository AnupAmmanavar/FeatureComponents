plugins {
    id(BuildPlugins.androidApplication)
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

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    dataBinding {
        isEnabled = true
    }
}

dependencies {

    implementation(project(":features"))

    implementation(BuildPlugins.kotlinStdLib)
    implementation(Libs.appCompat)
    implementation(Libs.coreKtx)
    implementation(Libs.constraintLayout)

    // Coroutines
    implementation(Libs.coroutinesCore)
    implementation(Libs.coroutinesAndroid)

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
