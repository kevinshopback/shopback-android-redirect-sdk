# ShopBack RedirectSDK Maven Repository

This is a GitHub Pages hosted Maven repository for the ShopBack RedirectSDK.

## Usage

Add this repository to your `build.gradle.kts`:

```kotlin
repositories {
    maven {
        url = uri("https://kevinshopback.github.io/shopback-redirect-sdk/maven-repo")
    }
}

dependencies {
    implementation("com.shopback:redirectsdk:0.1.0")
}
```

## Available Versions

- **0.1.0** - Latest release

## Repository Structure

This repository follows the standard Maven repository layout:

```
maven-repo/
└── com/shopback/redirectsdk/
    ├── maven-metadata.xml
    └── 0.1.0/
        ├── redirectsdk-0.1.0.aar
        ├── redirectsdk-0.1.0.pom
        ├── redirectsdk-0.1.0.aar.sha1
        ├── redirectsdk-0.1.0.aar.md5
        ├── redirectsdk-0.1.0.pom.sha1
        └── redirectsdk-0.1.0.pom.md5
```

## Dependencies

The SDK automatically resolves the following dependencies:

- Ktor Client (Core, Android, Serialization, Content Negotiation, Logging)
- Kotlinx Serialization JSON
- Kotlinx Coroutines (Core, Android)

No additional dependencies need to be added manually.