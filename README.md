**<h1 align="center"> JeTaxi </h1>**

<p align="center">The JeTaxi application is sample based on CleanArchitecture - MVVM that fetchs data from the network via repository pattern and shows them by Android Jetpack Libraries</p>

<p align="center">
  <img src="https://media.giphy.com/media/grRXsPTjWFpJ6CWOKQ/giphy-downsized.gif" alt="animated" />
</p>

#### What Does JeTaxi consist of?

- [Kotlin](https://kotlinlang.org/) 
- [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) is used to asynchronous and non-blocking programming. 
- [Flow](https://kotlinlang.org/docs/flow.html) is asynchronous version of a Sequence.
- [Compose](https://developer.android.com/jetpack/compose) is a modern toolkit for building UI. (Single Activity and No Fragment)
- [Hilt](https://dagger.dev/hilt/) is dependency injection based on [Dagger 2](https://developer.android.com/training/dependency-injection/dagger-android).
- [Accompanist](https://github.com/google/accompanist) is a collection of extension libraries for Jetpack Compose applications.
- [Retrofit2](https://github.com/square/retrofit) REST APIs.
- [OkHttp3](https://github.com/square/okhttp) is used to implementing interceptor, logging web server.
- [kotlinx.serialization](https://kotlinlang.org/docs/serialization.html) is used to process of converting data used by an application to a format that can be transferred over a network or stored in a database or a file.
- [Google-Map](https://developers.google.com/maps) create real-world, real-time experiences with the latest Maps, Routes, and Places features from Google Maps Platform.
- [Screet Gradle Plugin](https://github.com/google/secrets-gradle-plugin) a Gradle plugin for providing your secrets securely to your Android project.
- [Chucker](https://github.com/ChuckerTeam/chucker) is inspection of HTTP(S) requests/responses
- [MockK](https://github.com/mockk/mockk) is a test framework.
- [Truth](https://github.com/google/truth) makes your test assertions and failure messages more readable.
- [MockWebServer](https://github.com/square/okhttp/tree/master/mockwebserver) A scriptable web server for testing HTTP clients

#### Architecture 

- CleanArchitecture - MVVM
[Android Architecture components](https://developer.android.com/topic/libraries/architecture)

#### Note 

You are going to need to get an API key so that you can show maps properly. </br>
Please follow the [instructions](https://developers.google.com/maps/documentation/android-sdk/get-api-key) and include it in the `local.properties` file as follows:

```
KEY={insert_the_key_here}
```

When restricting the Key to Android apps, use `com.tlgbltcn.jetaxi` as package name, and
`C2:C6:50:35:30:03:7B:05:62:34:1E:C2:48:3F:73:81:2B:68:C2:7D` as SHA-1 certificate fingerprint. 

#### To-Do in Future 

- More test case.
