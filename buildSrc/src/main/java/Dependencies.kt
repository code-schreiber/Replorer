object Versions {
    const val gradle = "3.1.3"
    const val kotlin = "1.2.51"

    const val versionCode = 1
    const val versionName = "0.0.1"

    const val minSdkVersion = 19
    const val targetSdkVersion = 28
    const val compileSdkVersion = 28

    const val googleSupport = "27.1.1"

    const val mockitoKotlin = "1.5.0"
    const val kluent = "1.35"
}

object Dependencies {
    const val kotlin = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"

    const val appCompat = "com.android.support:appcompat-v7:${Versions.googleSupport}"
    const val recyclerView = "com.android.support:recyclerview-v7:${Versions.googleSupport}"
    const val cardView = "com.android.support:cardview-v7:${Versions.googleSupport}"

    const val mockitoKotlin = "com.nhaarman:mockito-kotlin-kt1.1:${Versions.mockitoKotlin}"
    const val kluent = "org.amshove.kluent:kluent-android:${Versions.kluent}"
}

object Plugins {
    const val gradle = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
}

object Projects {
    const val app = ":app"
}
