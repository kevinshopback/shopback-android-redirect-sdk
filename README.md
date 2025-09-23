# ShopBack RedirectSDK

ShopBack RedirectSDK for Android - A library for handling merchant-redirection.

## Installation

Add this repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://kevinshopback.github.io/shopback-redirect-sdk/maven-repo")
    }
}

dependencies {
    implementation("com.shopback:redirectsdk:0.2.0")
}
```

## Available Versions

- **0.2.0** - Latest release
- **0.1.0** - Previous release


## Features

- Handle redirects seamlessly

## Dependencies

The SDK automatically resolves the following dependencies:

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

## Usage

[Add usage documentation here]

## License

MIT License