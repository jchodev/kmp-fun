# Podcast App

A cross-platform mobile application for browsing and bookmarking podcasts, built with Kotlin Multiplatform.

## Features
- Browse curated podcast listings
- View detailed podcast information and episodes
- Bookmark favorite podcasts
- Cross-platform support (iOS & Android)

## Technical Architecture

### Core Technology Stack
- **Kotlin Multiplatform (KMP)** for shared codebase
- **MVVM Architecture Pattern**
- **Repository Pattern** for data management
- **Jetpack Compose** for UI

### Key Libraries

| Category | Library | Purpose |
|----------|----------|---------|
| Dependency Injection | [Koin](https://insert-koin.io/docs/reference/koin-mp/kmp/) | Modular DI framework |
| Networking | [Ktor](https://ktor.io/) | API communication |
| Image Loading | [Coil](https://coil-kt.github.io/coil/) | Efficient image loading/caching |
| Local Storage | [Room](https://developer.android.com/kotlin/multiplatform/room) | SQLite database wrapper |
| UI Components | [Compose Shimmer](https://github.com/valentinilk/compose-shimmer) | Loading state animations |
| Utilities | [URL Encoder](https://github.com/ethauvin/urlencoder) | URL string handling |
| Testing   | [Mokkery](https://github.com/lupuuss/Mokkery) | mock object / interface |

## Project Structure

    src/
    ├── ...  
    ├── composeApp               
    │   ├── androidMain         # Android-specific code
    │   ├── iosMain             # iOS-specific code
    │   └── commonMain          # Shared KMP code
    └── ...

### Architecture Overview
- Based on the [Android Sunflower](https://github.com/android/sunflower) reference architecture
- Basic MVVM Architecture Diagram
<img width="549" alt="image" src="https://github.com/user-attachments/assets/6df63676-b473-4cb3-9e4f-ebb20f697030">


## Data Management

### API Integration
- Endpoint: https://the-podcasts.fly.dev/
- Features:
  - Podcast listings
  - Episode details
- Implementation: Ktor HTTP Client

### Local Storage
- Technology: Room Database
- Schema:
  - `Favourite` table (podcast bookmarks)
  - Related entity relationships

## Navigation Flow
![image](https://github.com/user-attachments/assets/b7c6fda9-2f01-4935-b9e9-8b697a744b94)


## Development Setup

1. Clone repository
2. Open in Android Studio/Fleet
3. Sync Gradle dependencies
4. Build and run

## Testing Status

### Implemented Tests
- Unit tests for Episode List:
  - ViewModel
  - Repository
  - API layer
- iOS Compose UI tests
```bash
./gradlew :composeApp:iosSimulatorArm64Test
```

## Roadmap
### Planned Improvements
1. Firebase Crashlytics integration
2. Comprehensive test coverage
3. Audio playback functionality

## Diagram (using https://www.mermaidchart.com/)
### Sequence Diagram of fetch pocast list from server
![image](https://github.com/user-attachments/assets/722f2dfc-ce54-4590-abd1-a212819d1642)

### Sequence Diagram of save / remove `Favourite` podcast 
![image](https://github.com/user-attachments/assets/8858a558-85d2-4713-b24b-bc2e9ff3eb1e)


