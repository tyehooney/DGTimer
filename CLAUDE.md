# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

DGTimer is an Android app for Dolce Gusto coffee machine users, providing extraction timers for various coffee capsules. The app is written in Kotlin and follows modern Android architecture patterns.

## Module Structure

The project follows **Android Clean Architecture** with a multi-module setup:

- **app**: Application entry point, contains only configuration and startup logic
- **ui**: Presentation layer containing Activities, ViewModels, Adapters, and UI-related utilities
- **domain**: Business logic layer with Use Cases, Repository interfaces, and Domain models
- **data**: Data layer handling Room database, Firebase integration, Repository implementations, and data mapping
- **common**: Shared utilities and resources used across modules

### Dependency Flow
```
app → ui → domain ← data → common
          ↓
        common
```

## Key Architecture Components

- **Clean Architecture**: Clear separation of concerns with UI → Domain ← Data layers
- **MVVM Architecture**: Uses AAC ViewModel with DataBinding for UI
- **Use Cases**: Business logic encapsulated in Use Case classes in domain layer
- **Repository Pattern**: Abstract data sources through Repository interfaces
- **Dependency Injection**: Hilt for DI throughout all modules
- **Database**: Room database for local data persistence with Entity-Domain mapping
- **Firebase**: Firestore for cloud data, Crashlytics for crash reporting, Analytics
- **Coroutines & Flow**: For asynchronous operations and reactive programming
- **Image Loading**: Glide for image loading and caching

## Common Development Commands

### Build & Run
```bash
# Clean build
./gradlew clean

# Build debug APK
./gradlew assembleDebug

# Build release APK (requires signing configuration in local.properties)
./gradlew assembleRelease

# Install debug build to connected device
./gradlew installDebug
```

### Testing
```bash
# Run unit tests
./gradlew test

# Run instrumented tests
./gradlew connectedAndroidTest
```

## Key Files & Locations

### UI Module (`ui/`)
- `src/main/java/com/tyehooney/ui/`
  - `DGTimerApp.kt` - Application class with Hilt setup
  - `activities/main/` - MainActivity and MainViewModel
  - `activities/timer/` - TimerActivity, TimerViewModel, Counter classes
  - `activities/settings/` - SettingsActivity and SettingsViewModel
  - `utils/` - UI utilities (Extensions, TimeUtils)
  - `widget/` - App widget implementation

### Domain Module (`domain/`)
- `src/main/java/com/tyehooney/domain/`
  - `model/` - Domain models (Capsule, NameInfo)
  - `repository/` - Repository interface (CapsuleRepository)
  - `usecase/` - Business logic (GetCapsulesUseCase, RefreshCapsulesUseCase, etc.)
  - `di/` - Domain layer DI modules

### Data Module (`data/`)
- `src/main/java/com/tyehooney/data/`
  - `Models.kt` - Room entities and domain mapping extensions
  - `CapsuleDatabase.kt` - Room database configuration
  - `CapsuleDao.kt` - Database access objects
  - `CapsuleRepositoryImpl.kt` - Repository implementation
  - `DGTimerPreferences.kt` - SharedPreferences wrapper
  - `Migrations.kt` - Database migrations

### Configuration
- `local.properties` - Contains signing configuration and API keys (not in version control)

## Build Configuration

- Uses Gradle version catalogs for dependency management (`gradle/*.versions.toml`)
- Minimum SDK: 26, Target SDK: 34
- Kotlin version: 1.7.10
- Uses view binding instead of findViewById
- ProGuard enabled for release builds

## Development Notes

### Module Dependencies
- **UI layer** depends on Domain layer through Use Cases, never directly on Data layer
- **Domain layer** is pure Kotlin with no Android dependencies (except for Android library setup for compatibility)
- **Data layer** implements Domain interfaces and handles all external data sources
- **Repository pattern** provides abstraction between Domain and Data layers

### Key Patterns
- **Use Case pattern**: Each business operation is encapsulated in a Use Case class
- **Entity-Domain mapping**: Room entities are mapped to Domain models using extension functions
- **Dependency Inversion**: UI layer depends on abstractions (Use Cases), not implementations

### Configuration Requirements
- The app requires a `local.properties` file with signing configuration and AdFit advertising ID
- Firebase configuration requires `google-services.json` in the app module
- The project includes Kakao AdFit SDK for advertising
- Database operations use Room with coroutines for async operations
- The app supports multiple languages through domain models