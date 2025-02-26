plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
//    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.danikula.videocache.sample"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.danikula.videocache.sample"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    // 更新到 AndroidX 的支持库
    implementation("androidx.legacy:legacy-support-v4:1.0.0") // 替代 'com.android.support:support-v4'

    // 更新 AndroidAnnotations 到最新版本，确保兼容 AndroidX
    implementation("org.androidannotations:androidannotations-api:4.8.0")

    // 你的项目内部库
    implementation(project(":library"))

    // 用 ViewPager2 替代 ViewPagerIndicator，使用 TabLayout 来做页面指示器
    implementation("com.google.android.material:material:1.6.0") // Material 组件，包含 TabLayout 和 ViewPager2

    // 使用新的注解处理器
    annotationProcessor("org.androidannotations:androidannotations:4.8.0")
}
