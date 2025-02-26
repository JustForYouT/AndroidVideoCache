plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-kapt")
//    id("org.jetbrains.kotlin.plugin.compose") version "2.0.0"
}

android {
    namespace = "com.danikula.proxycache.test"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.danikula.proxycache.test"
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

// Temporary workaround for Robolectric issue https://github.com/robolectric/robolectric/issues/2647
android.applicationVariants.all { variant ->
    // Ensure we handle productFlavors correctly, fall back to an empty string if no flavor
    val productFlavor = variant.productFlavors.firstOrNull()?.name?.capitalize() ?: ""
    val buildType = variant.buildType.name.capitalize()

    // Ensure that the task exists before adding dependencies
    val compileTestTaskName = "compile${productFlavor}${buildType}UnitTestSources"
    val mergeAssetsTaskName = "merge${productFlavor}${buildType}Assets"

    tasks.named(compileTestTaskName).configure {
        dependsOn(tasks.named(mergeAssetsTaskName))  // Ensure correct task dependency
    }
}


// Display test progress
tasks.withType<Test> {
    testLogging {
        // Set options for log level LIFECYCLE
        events("passed", "skipped", "failed", "standardOut")
        showExceptions = true
        exceptionFormat = TestExceptionFormat.FULL
        showCauses = true
        showStackTraces = true

        // Set options for log level DEBUG and INFO
        debug {
            events("started", "passed", "skipped", "failed", "standardOut", "standardError")
            exceptionFormat = TestExceptionFormat.FULL
        }
        info {
            events = debug.events
            exceptionFormat = debug.exceptionFormat
        }

        afterSuite { desc, result ->
            if (desc.parent == null) { // will match the outermost suite
                val output = "Results: ${result.resultType} (${result.testCount} tests, ${result.successfulTestCount} successes, ${result.failedTestCount} failures, ${result.skippedTestCount} skipped)"
                val startItem = "|  "
                val endItem = "  |"
                val repeatLength = startItem.length + output.length + endItem.length
                println("\n" + "-".repeat(repeatLength) + "\n$startItem$output$endItem\n" + "-".repeat(repeatLength))
            }
        }
    }
}


dependencies {
    implementation(project(":library"))  // 更新为 implementation 依赖

    testImplementation("org.slf4j:slf4j-simple:1.7.21")
    testImplementation("junit:junit:4.12")
    testImplementation("org.robolectric:robolectric:3.3.2")
    testImplementation("com.squareup:fest-android:1.0.0")
    testImplementation("com.google.guava:guava-jdk5:17.0")
    testImplementation("com.danikula:android-garden:2.1.4") {
        exclude(group = "com.google.android")
    }
    testImplementation("org.mockito:mockito-all:1.9.5")
}

