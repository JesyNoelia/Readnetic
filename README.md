# Readnetic
An Android application implemented using the MVVM pattern, Retrofit2, Dagger Hilt, LiveData, ViewModel, Coroutines, Room, Navigation Components, Data Binding and some other libraries from the [Android Jetpack](https://developer.android.com/jetpack) . Readnetic fetches data from the [NYT API](https://developer.nytimes.com/apis)

<p align="left"><a><img src="https://github.com/JesyNoelia/Readnetic/blob/master/home.png" width="400"></a></p>
<p align="right"><a><img src="https://github.com/JesyNoelia/Readnetic/blob/master/detail.png" width="400"></a></p>

## Aditional Functionality
The application allows QR scanning to search for books
<p align="center"><a><img src="https://github.com/JesyNoelia/Readnetic/blob/master/scaner.png" width="400"></a></p>

## Example codes
Here you will find some sample codes to test the functionality of the application
<p align="center"><a><img src="https://github.com/JesyNoelia/Readnetic/blob/master/qrexamples.png" width="700"></a></p>

## Architecture
The architecture of this application relies and complies with the following points below:
* A single-activity architecture, using the [Navigation Components](https://developer.android.com/guide/navigation) to manage fragment operations.
* Pattern [Model-View-ViewModel](https://en.wikipedia.org/wiki/Model%E2%80%93view%E2%80%93viewmodel)(MVVM) which facilitates a separation of development of the graphical user interface.
* [Android architecture components](https://developer.android.com/topic/libraries/architecture/) which help to keep the application robust, testable, and maintainable.

<p align="center"><a><img src="https://github.com/JesyNoelia/Readnetic/blob/master/mvvm.png" width="700"></a></p>

## Technologies used:
* [Retrofit](https://square.github.io/retrofit/) a REST Client for Android which makes it relatively easy to retrieve and upload JSON (or other structured data) via a REST based webservice.
* [Dagger Hilt](https://dagger.dev/hilt/) for dependency injection.
* [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) to store and manage UI-related data in a lifecycle conscious way.
* [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) to handle data in a lifecycle-aware fashion.
* [Navigation Component](https://developer.android.com/guide/navigation) to handle all navigations and also passing of data between destinations.
* [Material Design](https://material.io/develop/android/docs/getting-started/) an adaptable system of guidelines, components, and tools that support the best practices of user interface design.
* [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) used to manage the local storage i.e. `writing to and reading from the database`. Coroutines help in managing background threads and reduces the need for callbacks.
* [Data Binding](https://developer.android.com/topic/libraries/data-binding/) to declaratively bind UI components in layouts to data sources.
* [Room](https://developer.android.com/topic/libraries/architecture/room) persistence library which provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
* [Paging Library](https://developer.android.com/topic/libraries/architecture/paging) helps you load and display small chunks of data at a time.
* [Android KTX](https://developer.android.com/kotlin/ktx) which helps to write more concise, idiomatic Kotlin code.

## Unit Testing Frameworks
Unit Tests verify the interactions of viewmodels between repositories and REST api requests.
- [JUnit4](https://github.com/junit-team/junit4) - a simple framework to write repeatable tests.
- [Mockk](https://github.com/mockk/mockk) - a technique to make testing code readable and maintainable.

## Installation
Readnectic requires a minimum API level of 26.

## Content Credits
All copyrights of the contents, concepts, and phrases that are used in this open-source project belong to [NYT API](https://developer.nytimes.com/apis).

# License
```xml
Designed and developed by Andrés Carrasco y Jesica Noelia Cepero Pediconi.
