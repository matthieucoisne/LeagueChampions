object Config {
    const val compileSdk                      = 28
    const val targetSdk                       = 28
    const val minSdk                          = 21
    const val buildTools                      = "28.0.3"
}

object Versions {
    const val gradle_plugin_android           = "3.6.0-beta01"
    const val gradle_plugin_google_services   = "4.3.2"
    const val gradle_plugin_fabric            = "1.31.1"

    const val kotlin                          = "1.3.50"
    const val kotlin_coroutines               = "1.3.2"

    const val android_material                = "1.1.0-beta01"
    const val androidx                        = "1.0.0"
    const val androidx_activity               = "1.1.0-beta01"
    const val androidx_appCompat              = "1.1.0"
    const val androidx_constraintLayout       = "1.1.3"
    const val androidx_core                   = "1.2.0-beta01"
    const val androidx_fragment               = "1.2.0-beta01"
    const val androidx_lifecycle              = "2.2.0-beta01"
    const val androidx_lifecycle_savedState   = "1.0.0-beta01"
    const val androidx_navigation             = "2.2.0-beta01"
    const val androidx_recyclerview           = "1.1.0-beta05"
    const val androidx_room                   = "2.2.0"
    const val annotation                      = "1.1.0"
    const val crashlytics                     = "2.10.1"
    const val dagger                          = "2.24"
    const val glide                           = "4.10.0"
    const val gson                            = "2.8.6"
    const val leakcanary                      = "1.6.3"
    const val okio                            = "2.2.2"
    const val okhttp                          = "4.2.2"
    const val picasso                         = "2.5.2"
    const val picasso_okhttp                  = "1.1.0"
    const val retrofit                        = "2.6.2"
    const val timber                          = "4.7.1"

    // TESTING
    const val androidx_test                   = "1.3.0-alpha02"
    const val androidx_core_testing           = "2.1.0"
    const val espresso                        = "3.3.0-alpha02"
    const val hamcrest                        = "1.3"
    const val junit                           = "4.12"
    const val mockito                         = "2.25.0"
    const val robolectric                     = "4.3"
    const val assertj                         = "1.7.1" // Do not update. It is the last version that supports android. (see assertj for android - square)
}

object Dependencies {
    // KOTLIN
    val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    val kotlin_coroutines_core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlin_coroutines}"
    val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlin_coroutines}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"

    // ANDROIDX
    val androidx_activity_ktx = "androidx.activity:activity-ktx:${Versions.androidx_activity}"
    val androidx_appcompat = "androidx.appcompat:appcompat:${Versions.androidx_appCompat}"
    val androidx_constraintlayout = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_core_ktx = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_fragment = "androidx.fragment:fragment:${Versions.androidx_fragment}"
    val androidx_legacy_core_utils = "androidx.legacy:legacy-support-core-utils:${Versions.androidx}"
    val androidx_legacy_core_ui = "androidx.legacy:legacy-support-core-ui:${Versions.androidx}"
    val androidx_lifecycle_extentions = "androidx.lifecycle:lifecycle-extensions:${Versions.androidx_lifecycle}"
    val androidx_lifecycle_runtime_ktx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidx_lifecycle}"
    val androidx_lifecycle_viewmodel_ktx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_lifecycle}"
    val androidx_lifecycle_viewmodel_savedstate = "androidx.lifecycle:lifecycle-viewmodel-savedstate:${Versions.androidx_lifecycle_savedState}"
    val androidx_lifecycle_livedata_core_ktx = "androidx.lifecycle:lifecycle-livedata-core-ktx:${Versions.androidx_lifecycle}"
    val androidx_lifecycle_livedata_ktx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_lifecycle}"
    val androidx_lifecycle_common_java8 = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidx_lifecycle}"
    val androidx_navigation_runtime_ktx = "androidx.navigation:navigation-runtime-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_fragment_ktx = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_ui_ktx = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    val androidx_recyclerview = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    // MATERIAL
    val android_material = "com.google.android.material:material:${Versions.android_material}"

    // ROOM
    val androidx_room_runtime = "androidx.room:room-runtime:${Versions.androidx_room}"
    val androidx_room_ktx = "androidx.room:room-ktx:${Versions.androidx_room}"
    val androidx_room_compiler = "androidx.room:room-compiler:${Versions.androidx_room}"
    val androidx_room_testing = "androidx.room:room-testing:${Versions.androidx_room}"

    // DAGGER
    val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    val dagger_android = "com.google.dagger:dagger-android:${Versions.dagger}"
    val dagger_android_processor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    val dagger_android_support = "com.google.dagger:dagger-android-support:${Versions.dagger}"
    val dagger_compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"

    // GSON
    val gson = "com.google.code.gson:gson:${Versions.gson}"

    // OKIO
    val okio = "com.squareup.okio:okio:${Versions.okio}"

    // OKHTTP
    val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp}"
    val okhttp_logging_interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"

    // RETROFIT
    val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_converter_gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    val retrofit_mock = "com.squareup.retrofit2:retrofit-mock:${Versions.retrofit}"

    // PICASSO
    val picasso = "com.squareup.picasso:picasso:${Versions.picasso}"
    val picasso_okhttp_downloader = "com.jakewharton.picasso:picasso2-okhttp3-downloader:${Versions.picasso_okhttp}"

    // GLIDE
    val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
    val glide_okhttp_integration = "com.github.bumptech.glide:okhttp3-integration:${Versions.glide}"

    // TIMBER
    val timber = "com.jakewharton.timber:timber:${Versions.timber}"

    // CRASHLYTICS
    val crashlytics = "com.crashlytics.sdk.android:crashlytics:${Versions.crashlytics}"

    // LEAK CANARY
    val leakcanary = "com.squareup.leakcanary:leakcanary-android:${Versions.leakcanary}"


    // UNIT TESTS
    val junit = "junit:junit:${Versions.junit}"
    val assertj = "org.assertj:assertj-core:${Versions.assertj}"
    val hamcrest = "org.hamcrest:hamcrest-library:${Versions.hamcrest}"
    val androidx_arch_core_testing = "androidx.arch.core:core-testing:${Versions.androidx_core_testing}"

    val mockito_core = "org.mockito:mockito-core:${Versions.mockito}"
    //http://static.javadoc.io/org.mockito/mockito-core/2.22.0/org/mockito/Mockito.html#0.2
    val mockito_inline = "org.mockito:mockito-inline:${Versions.mockito}"

    val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"


    // INSTRUMENTATION TESTS
    val androidx_annotation = "androidx.annotation:annotation:${Versions.annotation}"
    val androidx_test_runner = "androidx.test:runner:${Versions.androidx_test}"
    val androidx_test_rules = "androidx.test:rules:${Versions.androidx_test}"
    val androidx_test_espresso_core = "androidx.test.espresso:espresso-core:${Versions.espresso}"
    val androidx_test_espresso_idling_resource = "androidx.test.espresso:espresso-idling-resource:${Versions.espresso}"
    val androidx_test_espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.espresso}"
}

object GradlePlugins {
    val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    val fabric = "io.fabric.tools:gradle:${Versions.gradle_plugin_fabric}"
    val android = "com.android.tools.build:gradle:${Versions.gradle_plugin_android}"
    val androidx_navigation_safe_args = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"
    val google_services =  "com.google.gms:google-services:${Versions.gradle_plugin_google_services}"
}
