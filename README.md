# Android Contact Sync Project

## Overview

This Android application demonstrates a contact synchronization feature between a user's device and
a web server.

## Features

- Sync contacts between device and server
- Local storage of contacts using Room database
- MVVM architecture with ViewModel and LiveData
- Dependency injection with Hilt
- Kotlin Coroutines for asynchronous operations
- Unit tests for core components

## Project Structure

The project follows a clean architecture approach with the following main packages:

- `data`: Contains the implementation of data sources (local and remote) and the repository
- `domain`: Holds the business logic, use cases, and domain models
- `presentation`: Includes the UI components (activities, fragments) and ViewModels
- `di`: Contains dependency injection modules

## Building and Running

To build the app, run the following command:

```
./gradlew assembleDebug
```

To run the app on a connected device or emulator:

```
./gradlew installDebug
```

## Testing

The project includes unit tests for the ViewModel and Repository. To run the tests:

```
./gradlew test
```



## Testing

- Implement a SyncContacts utility which will ensure that the backend-server is in sync (up to date) with the client's contact book.
- For example, if there are 500 contacts in the user's contact book, the server should also have the same state associated with that user in its database.
- To access device contacts, a utility called DeviceContactProvider is provided.
- A network bridge ContactsNetworkBridge is provided to make network calls to sync contacts with the server.