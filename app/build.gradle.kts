plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.androidx.navigation.safeargs)
    id("kotlin-kapt") // Necesario para que Room funcione con las entidades y los DAO
}

android {
    namespace = "com.example.violetavibes"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.violetavibes"
        minSdk = 23
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        viewBinding = true // Para poder acceder a las vistas sin usar findViewById
    }
}

kotlin {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_11)
    }
}

dependencies {
    // 🔹 Dependencias básicas de Android
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.fragment)
    implementation(libs.material)

    val nav_version = "2.7.7"

// 🔹 Navegación entre fragments (con XML)
    implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
    implementation("androidx.navigation:navigation-ui-ktx:$nav_version")


// 🟣 Base de datos Room (para guardar info local)
    val room_version = "2.8.3" // La versión estable más reciente. [1]

    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")


    val coroutines_version = "1.9.0" // Versión estable y reciente

// 🟡 Coroutines (para tareas en segundo plano con Room)
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutines_version")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutines_version")


    // ⚙ Fix de compatibilidad con Kotlin (evita errores de metadata)
    implementation("org.jetbrains.kotlinx:kotlinx-metadata-jvm:0.7.0")
    implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.22")

    // 🔹 Librerías para testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}