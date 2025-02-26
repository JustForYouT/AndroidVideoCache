plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
}
val versionName = "1.0.0"
val versionCode = 1
android {
    namespace = "com.danikula.videocache"
    compileSdk = 34
    buildFeatures {
        buildConfig = true  // Enable the BuildConfig feature
    }
    defaultConfig {
        minSdk = 24
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
        buildConfigField("String","VERSION_NAME",'"' + versionName + '"')
        buildConfigField("int","VERSION_CODE",versionCode.toString())
    }

//    buildTypes {
//        release {
//            isMinifyEnabled = false
//            proguardFiles(
//                    getDefaultProguardFile("proguard-android-optimize.txt"),
//                    "proguard-rules.pro"
//            )
//        }
//    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

//idea {
//    module {
//        downloadJavadoc = true
//        downloadSources = true
//    }
//}

dependencies {
    implementation("org.slf4j:slf4j-android:1.7.21")
}

//bintray {
//    userOrg = "alexeydanilov"
//    publish = true
//    packageConfig {
//        repo = "maven"
//        name = "videocache"
//        version {
//            name = android.defaultConfig.versionName
//            description = "Cache support for android VideoView"
//            website = "https://github.com/danikula/AndroidVideoCache"
//        }
//        licenses = listOf("Apache-2.0")
//        vcsUrl = "https://github.com/danikula/AndroidVideoCache.git"
//    }
//}
