# Project description
[![CircleCI](https://circleci.com/gh/abalta/WASD-Modular.svg?style=shield&circle-token=8867ef8e7edeacd352f0090618f7c873e5346799)](https://circleci.com/gh/abalta/WASD-Modular)
[![Kotlin Version](https://img.shields.io/badge/Kotlin-1.3.61-blue.svg)](https://kotlinlang.org)
[![API](https://img.shields.io/badge/API-21%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=21)
[![Android Studio](https://img.shields.io/badge/Android%20Studio-3.6.1-informational.svg)](https://developer.android.com/studio)

This project is open source part for my ongoing development. I present modern Android application architecture that is modular with dynamic feature.

## Project characteristics

This project brings to table set of best practices, tools, and solutions:

* Modern architecture (feature modules, Clean Architecture, Model-View-ViewModel)
* [Android Jetpack](https://developer.android.com/jetpack)
* CI pipeline
* Dependency Injection with Koin
* Material design

## Tech-stack

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) + [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations
    * [Koin](https://insert-koin.io/) - dependency injection
    * [Retrofit](https://square.github.io/retrofit/) - networking
    * [Jetpack](https://developer.android.com/jetpack)
        * [Navigation](https://developer.android.com/topic/libraries/architecture/navigation/) - deal with whole in-app navigation
        * [LiveData](https://developer.android.com/topic/libraries/architecture/livedata) - notify views about database change
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way
        * [Room](https://developer.android.com/topic/libraries/architecture/room) - persistence library provides an abstraction layer over SQLite to allow for more robust database access while harnessing the full power of SQLite.
        * [Pagination](https://developer.android.com/topic/libraries/architecture/paging) - helps you load and display small chunks of data at a time. Loading partial data on demand reduces usage of network bandwidth and system resources.
    * [Stetho](http://facebook.github.io/stetho/) - application debugging tool
* Architecture
    * Clean Architecture (at module level)
    * MVVM Pattern
    * [Dynamic feature modules](https://developer.android.com/studio/projects/dynamic-delivery)
    * [Android Architecture components](https://developer.android.com/topic/libraries/architecture) ([ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel), [LiveData](https://developer.android.com/topic/libraries/architecture/livedata), [Navigation](https://developer.android.com/jetpack/androidx/releases/navigation), [SafeArgs](https://developer.android.com/guide/navigation/navigation-pass-data#Safe-args) plugin)
* Gradle
    * [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)

## Getting Started

This project uses IGDB Api for fetching datas. You need to get Api key for services the app uses.

-   IGDB: https://api-docs.igdb.com/#about

When you obtain the key, you can provide them to the app by putting the following in the `gradle.properties` project root file:

```properties
#IGDB API KEY
apiToken = <insert>
```

## Architecture

Each module has own Clean Architecture layers.

-   `:core` module only responsible for implement common dependencies, holds remote (Retrofit) and cache (Room) repositories.
-   `:onboarding` module is install-time delivery feature module, this module install when app is downloaded. (Uninstall this module is upcoming development progress.)
-   `:home` module is install-time delivery feature module, this module install when app is downloaded.
-   `:app` this is simple application module, this is responsible for navigate feature modules.

## Showroom

| Module  |  OnboardingFragments                | SelectPlatformFragment                                                       |
|-------|--------------------------------------------------------------------------|------------------------------------------------------------------------------|---------------------------------------------------------------------------|
| `:onboarding` | <img src="misc/onboarding_1.jpg" width="250"> | <img src="misc/onboarding_2.jpg" width="250"> | <img src="misc/onboarding_3.jpg" width="250"> | <img src="misc/select_platform.jpg" width="250">
| `:home`  | <img src="misc/home.jpg" width="250">  |