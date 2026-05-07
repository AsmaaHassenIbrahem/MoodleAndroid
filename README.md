# Moodle Native Mobile Assignment

This repository contains the solution for the Native Mobile Developer technical assignment.

The project is implemented for both platforms:

- `android/`: native Android app
- `ios/`: native iOS app

## Assignment Scope

The app covers three main flows:

1. Courses screen
   - Fetch enrolled courses using `core_enrol_get_users_courses`
   - Show course name, progress, and image
2. Course details screen
   - Fetch course sections using `core_course_get_contents`
   - Show section titles
3. Grades screen
   - Fetch grades using `gradereport_user_get_grade_items`

## API Configuration

Base URL:

`https://moodle.itcorner.qzz.io`

REST endpoint:

`/webservice/rest/server.php`

Authentication options:

- Login endpoint: `/login/token.php`
- Demo username: `student1`
- Demo password: `Demo@12345`
- Pre-generated token is also supported

## Android

### Technologies

- Kotlin
- Jetpack Compose
- MVVM
- Koin
- Retrofit
- Moshi
- Kotlin Coroutines
- Coil
- Navigation Compose

### Structure

```text
android/
  app/src/main/java/io/qzz/moodlecandidate/
    appNavigation/
    di/
    feature/
      courses/
      courseDetails/
      grades/
    shared/
      config/
      constants/
      network/
      ui/
```

### Notes

- The Android app is organized by feature
- Each feature owns its own API interface, DTOs, repository, model, screen, and view model
- Shared concerns such as constants, UI state, and config are grouped under `shared/`
- Dependency injection is handled with Koin

### Setup

1. Open `android/` in Android Studio
2. Let Gradle sync
3. Run the `MoodleAndroid` app on an emulator or device
