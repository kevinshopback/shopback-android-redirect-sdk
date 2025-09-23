# Overview

The ShopBack Android RedirectSDK enables seamless partner redirections for ShopBack applications across multiple regions.

## Requirements

- **Android API Level**: 24+ (Android 7.0)

## Installation

Add this repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://kevinshopback.github.io/shopback-redirect-sdk/maven-repo")
    }
}

dependencies {
    implementation("com.shopback:redirectsdk:0.2.1")
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

        override fun onInitError(throwable: Throwable) {
            // Handle initialization error
            Log.e("RedirectSDK", "Init failed", throwable)
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
        override fun onRedirectSuccess() {
            // Redirection completed successfully
            Log.d("RedirectSDK", "Redirect successful")
        }

        override fun onRedirectError(throwable: Throwable) {
            // Handle redirection error
            Log.e("RedirectSDK", "Redirect failed", throwable)
        }
    }
)
```

## Supported Domains

The SDK supports the following ShopBack domains:

| Domain Code | Region |
|-------------|--------|
| `SG` | Singapore |
| `MY` | Malaysia |
| `PH` | Philippines |
| `ID` | Indonesia |
| `TW` | Taiwan |
| `TH` | Thailand |
| `AU` | Australia |
| `VN` | Vietnam |
| `KR` | South Korea |
| `HK` | Hong Kong |
| `DE` | Germany |
| `NZ` | New Zealand |
| `US` | United States |

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

### Interfaces

#### InitListener

Callback interface for SDK initialization events.

```kotlin
interface InitListener {
    fun onInitSuccess()
    fun onInitError(throwable: Throwable)
}
```

#### RedirectionListener

Callback interface for redirection events.

```kotlin
interface RedirectionListener {
    fun onRedirectSuccess()
    fun onRedirectError(throwable: Throwable)
}
```

#### Domain

Enum representing supported ShopBack regions.

```kotlin
enum class Domain {
    SG, MY, PH, ID, TW, TH, AU, VN, KR, HK, DE, NZ, US
}
```

## Error Handling

The SDK provides comprehensive error handling through callback listeners:

```kotlin
// Common initialization errors
override fun onInitError(throwable: Throwable) {
    when (throwable.message) {
        "Authorization is not valid" -> {
            // Handle invalid JWT token
        }
        "UserId is not valid" -> {
            // Handle invalid user ID
        }
        else -> {
            // Handle other initialization errors
        }
    }
}

// Common redirection errors
override fun onRedirectError(throwable: Throwable) {
    when (throwable.message) {
        "Please init the RedirectSDK before redirection" -> {
            // SDK not initialized, call init() first
        }
        else -> {
            // Handle network or other redirection errors
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
