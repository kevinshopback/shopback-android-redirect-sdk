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
    implementation("com.shopback:redirectsdk:0.2.0")
}
```

## Available Versions

- **0.2.0** - Latest release
- **0.1.0** - Previous release

## Repository Structure

This repository follows the standard Maven repository layout:

```
maven-repo/
└── com/shopback/redirectsdk/
    ├── maven-metadata.xml
    ├── 0.1.0/
    │   ├── redirectsdk-0.1.0.aar
    │   └── redirectsdk-0.1.0.pom
    └── 0.2.0/
        ├── maven-metadata.xml
        ├── redirectsdk-0.2.0.aar
        └── redirectsdk-0.2.0.pom
```
