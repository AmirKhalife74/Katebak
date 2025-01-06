plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("androidx.navigation.safeargs.kotlin")
    id("kotlin-kapt")
    id ("dagger.hilt.android.plugin")
}

android {
    namespace = "com.example.katebak"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.katebak"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    buildFeatures {
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)

    //retrifit
    implementation (libs.retrofit)

    //Dependency Injection
    implementation (libs.hilt.android)
    kapt (libs.hilt.compiler)

    //noinspection KaptUsageInsteadOfKsp
    kapt (libs.moshi.kotlin.codegen)
    implementation (libs.converter.moshi)
    implementation (libs.moshi.kotlin)

    //Ok Http
    implementation (libs.okhttp)

    implementation(libs.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)

    //Gson
    implementation (libs.gson)



    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}