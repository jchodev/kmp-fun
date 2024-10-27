A simple MVVM Kotlin Multiplatform project - Get podcast and episode data from server 

* `/composeApp` is for code that will be shared across your Compose Multiplatform applications.
  It contains several subfolders:
  - `commonMain` is for code that’s common for all targets.
  - Other folders are for Kotlin code that will be compiled for only the platform indicated in the folder name.
    For example, if you want to use Apple’s CoreCrypto for the iOS part of your Kotlin app,
    `iosMain` would be the right folder for such calls.

* `/iosApp` contains iOS applications. Even if you’re sharing your UI with Compose Multiplatform, 
  you need this entry point for your iOS app. This is also where you should add SwiftUI code for your project.


Learn more about [Kotlin Multiplatform](https://www.jetbrains.com/help/kotlin-multiplatform-dev/get-started.html)…

## Project Structure:
It follows the basic project structure established by the official Android app  [sunflower](https://github.com/android/sunflower)

## Screen Flow:
<img width="647" alt="image" src="https://github.com/user-attachments/assets/21e2bf94-d2c4-4fd6-93b9-f018f08faf2d">

## Libraries 
| Library           | Used For                  | Remark |
|-------------------|---------------------------| ------ |
|[koin](https://insert-koin.io/docs/reference/koin-mp/kmp/)|DI| --- |
|[ktor](https://ktor.io/) |Network| --- |
|   [Coil](https://coil-kt.github.io/coil/)             | Image display           | ----| 
|   [ethauvin-urlencoder](https://github.com/ethauvin/urlencoder)             | Url encode           | ----| 
|   [valentinilk-shimmer](https://github.com/valentinilk/compose-shimmer)             | Display shimmer effect on compose           | ----| 

## Completed
1. Unit test - Episode List (ViewModel, repository, api)
2. Some UI test of compose level

## TODO
1. Implement [Firebase Crashlyics](https://firebase.google.com/docs/crashlytics)
2. Full testing (UI / unit test)
3. ...
