# UserApplication
Android app (Kotlin + Jetpack Compose + Hilt + Coroutines + Retrofit) that shows a List screen of users and a Detail screen.

# Technologies used

  - Kotlin
  - Android SDK (minSdk 26 and maxSdk 36)
  - Jetpack Compose
  - Hilt (DI)
  - Retrofit + OkHttp
  - Kotlinx Coroutines
  - MockK + JUnit + Turbine (for Flow) for unit tests
  - Coil (for image loading in Compose).

# Improvements & Assumptions
  # Assumptions
    -API returns a relatively small list (<= 1000) suitable for in-memory list.
    -DTO fields are compatible; null-safety handled in mappers.
    
  # Possible improvements
    -Paging (Paging 3) for large lists.
    -Local caching (Room) + Repository merging remote + local.
    -More robust error UI with retry/backoff.
    -Integration/UI tests (Espresso/Compose testing).

## Demo
# Video



https://github.com/user-attachments/assets/b2618356-4025-42d1-8eaf-9b4935341f63


# Screenshots
<img width="1080" height="2520" alt="User List" src="https://github.com/user-attachments/assets/4ce56fb6-a3a3-405b-b3ec-ae8693c93612" />
<img width="1080" height="2520" alt="User Details" src="https://github.com/user-attachments/assets/36a707ab-619a-4107-9bc5-73276a07cac3" />



