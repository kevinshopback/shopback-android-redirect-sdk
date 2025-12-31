# Overview

The ShopBack Android Redirect SDK enables seamless redirections for partner applications.

## Requirements

- **Android API Level**: 24+ (Android 7.0)

## Installation

Add this repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://kevinshopback.github.io/shopback-redirect-sdk/SDK")
    }
}

dependencies {
    implementation("com.shopback:redirectsdk:0.1.0")
}
```

## Available Versions

- **0.1.0** - Latest release

## Dependencies

- `io.ktor:ktor-client-core:2.3.12`
- `io.ktor:ktor-client-android:2.3.12`
- `io.ktor:ktor-serialization-kotlinx-json:2.3.12`
- `io.ktor:ktor-client-content-negotiation:2.3.12`
- `io.ktor:ktor-client-logging:2.3.12`
- `org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.3`
- `org.jetbrains.kotlinx:kotlinx-coroutines-core:1.8.1`
- `org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1`
- `org.jetbrains.kotlin:kotlin-stdlib:2.0.21`
- `androidx.core:core-ktx:1.17.0`

## Quick Start

### 1. Initialize the SDK

```kotlin
import com.shopback.redirectsdk.*

// Initialize with callback
RedirectSDK.init(
    context = this,
    domain = Domain.AU,  // Choose your region
    authorization = "jwt_token_here",
    userId = "user_id_here",
    listener = object : InitListener {
        override fun onInitSuccess() {
            // SDK ready to use
            Log.d("RedirectSDK", "Initialized successfully")
        }

        override fun onInitError(exception: InitialSDKException) {
            // Handle initialization error
            Log.e("RedirectSDK", "Init failed", exception)
        }
    }
)
```

### 2. Start Redirection

Once initialized, you can start partner redirections:

```kotlin
RedirectSDK.startRedirection(
    context = this,
    redirectId = "redirect_id_here",
    listener = object : RedirectionListener {
        override fun onRedirectSuccess(shoppingTripId: String) {
            // Redirection completed successfully
            // Use shoppingTripId to track the shopping trip
            Log.d("RedirectSDK", "Redirect successful, shoppingTripId: $shoppingTripId")
        }

        override fun onRedirectError(exception: RedirectionException) {
            // Handle redirection error
            Log.e("RedirectSDK", "Redirect failed", exception)
        }
    }
)
```

## Supported Domains

The SDK supports the following ShopBack domains:

| Domain Code | Region        |
|-------------|---------------|
| `SG`        | Singapore     |
| `MY`        | Malaysia      |
| `PH`        | Philippines   |
| `ID`        | Indonesia     |
| `TW`        | Taiwan        |
| `TH`        | Thailand      |
| `AU`        | Australia     |
| `VN`        | Vietnam       |
| `KR`        | South Korea   |
| `HK`        | Hong Kong     |
| `DE`        | Germany       |
| `NZ`        | New Zealand   |
| `US`        | United States |

## API Reference

### RedirectSDK

Main SDK entry point providing initialization and redirection functionality.

#### Methods

##### `init(context, domain, authorization, userId, listener?)`

Initializes the SDK with required parameters.

**Parameters:**
- `context: Context` - Android application context
- `domain: Domain` - Target ShopBack domain/region
- `authorization: String` - JWT authorization token
- `userId: String` - User identifier
- `listener: InitListener?` - Optional initialization callback

##### `startRedirection(context, redirectId, listener?)`

Starts a partner redirection process.

**Parameters:**
- `context: Context` - Android application context
- `redirectId: String` - Partner-specific redirect identifier
- `listener: RedirectionListener?` - Optional redirection callback

**Callback:**
- `onRedirectSuccess(shoppingTripId: String)` - Called when redirection succeeds, returns the shopping trip ID for tracking

### Interfaces

#### InitListener

Callback interface for SDK initialization events.

```kotlin
interface InitListener {
    fun onInitSuccess()
    fun onInitError(exception: InitialSDKException)
}
```

#### RedirectionListener

Callback interface for redirection events.

```kotlin
interface RedirectionListener {
    fun onRedirectSuccess(shoppingTripId: String)
    fun onRedirectError(exception: RedirectionException)
}
```

**Methods:**
- `onRedirectSuccess(shoppingTripId: String)` - Called when redirection completes successfully. The `shoppingTripId` parameter contains the unique identifier for the shopping trip, which can be used for tracking and analytics purposes.

#### Domain

Enum representing supported ShopBack regions.

```kotlin
enum class Domain {
    SG, MY, PH, ID, TW, TH, AU, VN, KR, HK, DE, NZ, US
}
```

## Error Handling

The SDK provides comprehensive error handling through callback listeners and a structured exception hierarchy. All exceptions include both an error code and a descriptive error message:

### Exception Hierarchy

| Exception Type          | Parent Class           | Description                          |
|-------------------------|------------------------|--------------------------------------|
| `RedirectSDKException`  | `Exception`            | Base sealed class for all SDK errors |
| `InitialSDKException`   | `RedirectSDKException` | Exception for initialization errors  |
| `RedirectionException`  | `RedirectSDKException` | Exception for redirection errors     |

### Error Codes

#### InitialSDKException Error Codes

| Code | Constant                     | Description                       |
|------|------------------------------|-----------------------------------|
| 1001 | `CODE_EMPTY_AUTHORIZATION`   | Authorization token cannot be empty |
| 1002 | `CODE_EMPTY_USER_ID`         | User ID cannot be empty           |
| 1003 | `CODE_INIT_FAILED`           | SDK initialization failed         |

#### RedirectionException Error Codes

| Code | Constant                 | Description                          |
|------|--------------------------|--------------------------------------|
| 2001 | `CODE_NOT_INITIALIZED`   | SDK not initialized                  |
| 2002 | `CODE_EMPTY_REDIRECT_ID` | Redirect ID cannot be empty          |
| 2003 | `CODE_INVALID_FORMAT`    | Redirect ID has invalid format       |
| 2004 | `CODE_INVALID_URL`       | Redirect URL has invalid format      |
| 2005 | `CODE_RESOLVE_FAILED`    | Resolve the redirect ID failed       |
| 2006 | `CODE_OPEN_FAILED`       | Open the redirect URL failed         |

### Error Handling Examples

```kotlin
// Handle initialization errors
override fun onInitError(exception: InitialSDKException) {
    when (exception.code) {
        InitialSDKException.CODE_EMPTY_AUTHORIZATION -> {
            Log.e("RedirectSDK", "Authorization token is required")
        }
        InitialSDKException.CODE_EMPTY_USER_ID -> {
            Log.e("RedirectSDK", "User ID is required")
        }
        InitialSDKException.CODE_INIT_FAILED -> {
            Log.e("RedirectSDK", "Initialization failed: ${exception.message}")
        }
    }
}

// Handle redirection success
override fun onRedirectSuccess(shoppingTripId: String) {
    Log.d("RedirectSDK", "Redirection successful! Shopping Trip ID: $shoppingTripId")
    // Use shoppingTripId for tracking, analytics, or sending back to your backend
}

// Handle redirection errors
override fun onRedirectError(exception: RedirectionException) {
    when (exception.code) {
        RedirectionException.CODE_NOT_INITIALIZED -> {
            Log.e("RedirectSDK", "Please initialize SDK first")
        }
        RedirectionException.CODE_EMPTY_REDIRECT_ID -> {
            Log.e("RedirectSDK", "Redirect ID is required")
        }
        RedirectionException.CODE_INVALID_FORMAT -> {
            Log.e("RedirectSDK", "Invalid redirect ID format")
        }
        RedirectionException.CODE_INVALID_URL -> {
            Log.e("RedirectSDK", "Invalid redirect URL format")
        }
        RedirectionException.CODE_RESOLVE_FAILED -> {
            Log.e("RedirectSDK", "Failed to resolve redirect: ${exception.message}")
        }
        RedirectionException.CODE_OPEN_FAILED -> {
            Log.e("RedirectSDK", "Failed to open redirect: ${exception.message}")
        }
    }
}
```

## Sample Application

A complete sample application is included in the `app` module. To run the sample:

1. Clone the repository
2. Open the project in Android Studio
3. Update the domain, JWT token and user ID in `MainActivity.kt`
4. Run the app on an Android device or emulator

## Changelog

### 0.1.0 - Latest release
- Initial release of ShopBack Android Redirect SDK
- Support for partner redirections across all ShopBack domains
- JWT-based authentication
- Comprehensive error handling
- Sample application included

## License

```
MIT License

Copyright (c) 2024 ShopBack

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```

## Support

For support, please contact:
- **Email**: tech@shopback.com
