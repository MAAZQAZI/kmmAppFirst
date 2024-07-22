plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinx.serialization)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    sourceSets {
        commonMain.dependencies {

            implementation(libs.bundles.network)
            implementation(libs.bundles.serialization)
            implementation(libs.bundles.di)
            implementation(libs.bundles.common)
            implementation(libs.kmm.integrity)
            api(libs.kmm.async)


            // Ktor serialization
         }
        commonTest.dependencies {
            implementation(libs.kotlin.test)

        }
        androidMain.dependencies {
            implementation(libs.bundles.di)
            implementation("io.insert-koin:koin-androidx-compose:3.5.3")
            implementation(libs.bundles.common)




        }
        iosMain.dependencies {
            implementation(libs.bundles.di)

        }
}
}

android {
    namespace = "com.example.kmmappfirst"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
