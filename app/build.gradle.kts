
plugins {
        id("com.android.application")
        id("org.jetbrains.kotlin.android")
        id("kotlin-kapt") // Cambiado a la forma más común de declarar el plugin Kotlin Kapt
        id("com.google.gms.google-services") version "4.4.1"
    }

    android {
        namespace = "com.example.traveltracker"
        compileSdk = 34

        defaultConfig {
            applicationId = "com.example.traveltracker"
            minSdk = 28
            targetSdk = 33
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
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
        kotlinOptions {
            jvmTarget = "1.8"
        }

    }

    dependencies {
        implementation("androidx.tracing:tracing:1.1.0")

        androidTestImplementation ("org.mockito:mockito-android:2.24.5")
        testImplementation("junit:junit:4.13.2")
        testImplementation("com.google.truth:truth:1.4.2")
        testImplementation("junit:junit:4.13.2")
        androidTestImplementation("androidx.test.ext:junit:1.2.0-alpha03")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0-alpha03")
        androidTestImplementation("androidx.fragment:fragment-testing:1.4.0")


        implementation("androidx.core:core-ktx:1.9.0")
        implementation("androidx.appcompat:appcompat:1.6.1")
        implementation("com.google.android.material:material:1.11.0")
        implementation("androidx.constraintlayout:constraintlayout:2.1.4")
        implementation("com.android.car.ui:car-ui-lib:2.6.0")
        implementation("androidx.navigation:navigation-fragment-ktx:2.7.6")
        implementation("androidx.navigation:navigation-ui-ktx:2.7.6")
        implementation("com.google.android.gms:play-services-maps:18.2.0")
        implementation("com.google.firebase:firebase-firestore-ktx:24.10.2")
        implementation("com.google.firebase:firebase-common-ktx:20.4.2")
        implementation ("com.google.android.libraries.places:places:3.3.0")
        implementation("com.google.android.material:material:1.4.0-alpha02")
        implementation ("androidx.recyclerview:recyclerview:1.2.0")
        implementation("io.coil-kt:coil:1.4.0")
        implementation ("com.github.bumptech.glide:glide:4.12.0")
        implementation("com.github.skydoves:colorpickerview:2.3.0")


        testImplementation("junit:junit:4.13.2")
        testImplementation("org.testng:testng:6.9.6")
        testImplementation("org.testng:testng:6.9.6")
        testImplementation("org.testng:testng:6.9.6")
        testImplementation("org.testng:testng:6.9.6")
        testImplementation("org.testng:testng:6.9.6")
        androidTestImplementation("androidx.test.ext:junit:1.2.0-alpha03")
        androidTestImplementation("androidx.test.espresso:espresso-core:3.6.0-alpha03")


        testImplementation ("org.junit.jupiter:junit-jupiter-api:5.9.0")
        testImplementation ("org.junit.jupiter:junit-jupiter-engine:5.9.0")
        testImplementation ("org.mockito:mockito-core:4.0.0")

        androidTestImplementation ("androidx.test:rules:1.6.0-alpha03")
        androidTestImplementation ("androidx.test:runner:1.4.0")

        val roomVersion = "2.6.1"

        // Dependencia Room
        implementation("androidx.room:room-runtime:$roomVersion")
        kapt("androidx.room:room-compiler:$roomVersion")

        // Dependencia Room-KTX para soporte de coroutines
        implementation("androidx.room:room-ktx:$roomVersion")

        implementation(platform("com.google.firebase:firebase-bom:32.7.2"))

    }