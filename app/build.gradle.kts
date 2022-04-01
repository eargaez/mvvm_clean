plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.hilt)
}

android {
    compileSdk = AppConfig.compileSdk
    buildToolsVersion = "30.0.3"

    defaultConfig {
        applicationId = AppConfig.applicationId
        minSdk = AppConfig.minSdk
        targetSdk = AppConfig.targetSdk
        versionCode = AppConfig.versionCode
        versionName = AppConfig.versionName

        testInstrumentationRunner = AppConfig.androidTestInstrumentation
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    android.buildFeatures.viewBinding = true
    android.buildFeatures.dataBinding = true

    kapt { correctErrorTypes = true }

    flavorDimensions.add("flavorDimens")

    productFlavors {
        create("develop") {
            applicationIdSuffix = ".develop"
            buildConfigField("String", "HOST", Services.Develop.hostUrl)
        }
        create("production") {
            buildConfigField("String", "HOST", Services.Release.hostUrl)
        }
    }
}

dependencies {
    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))
    implementation(Dependencies.standardLibs)
    implementation(Dependencies.lifecycleLibs)
    implementation(Dependencies.activityFragmentKtxLibs)
    implementation(Dependencies.uILibs)
    implementation(Dependencies.navigationLibs)
    implementation(Dependencies.retrofitLibs)
    implementation(Dependencies.roomLibs)
    implementation(Dependencies.diLibs)
    implementation(Dependencies.coroutinesLibs)

    kapt(Dependencies.compilerLibs)

    testImplementation(TestDependencies.testLibraries)

    androidTestImplementation(AndroidTestDependencies.androidTestLibraries)
}
