plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("com.google.devtools.ksp")
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.lalabib.latihan.simpleproduct"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.lalabib.latihan.simpleproduct"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", "Password", "\"jrsw zluo khez wyya\"")
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
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true
    }

    android {
        packaging {
            resources.excludes.apply {
                add("META-INF/LICENSE.md")
                add("META-INF/*.properties")
                add("META-INF/AL2.0")
                add("META-INF/LGPL2.1")
                add("META-INF/NOTICE.md")
            }
        }
    }
}

dependencies {

    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    //Glide
    implementation("com.github.bumptech.glide:glide:4.15.1")

    // Circle imageview
    implementation("de.hdodenhof:circleimageview:3.1.0")

    // Room Database
    implementation("androidx.room:room-ktx:2.5.2")
    kapt("androidx.room:room-compiler:2.5.2")

    //Coroutine Flow
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.1")

    //LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.7.0")

    // Datastore
    implementation("androidx.datastore:datastore-preferences:1.0.0")

    //by viewModels
    implementation("androidx.activity:activity-ktx:1.8.2")

    //Dagger Hilt
    implementation("com.google.dagger:hilt-android:2.44")
    kapt("com.google.dagger:hilt-compiler:2.44")

    //mail
    implementation("com.sun.mail:android-mail:1.6.2")
    implementation("com.sun.mail:android-activation:1.6.2")
}