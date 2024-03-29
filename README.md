# PaysInTech API Service

![CI](https://github.com/Tech-Salaries-India/TSBackend/workflows/CI/badge.svg?branch=main)

REST API service for a Tech Salaries.

## Technology / Tools used 🛠

- Kotlin: Programming language for development
- Ktor: Backend development framework
- Kotlinx.Coroutines: Kotlin's Multithreading library
- Kotlinx.serialization: Kotlin's serialization library
- PostgreSQL: For data storage
- Dagger: Dependency Injection Framework
- Liquibase: Migrations and DB change version control
- Kotest: Kotlin Testing library
- Mockk: Mocking library
- Spotless: Lint checker and formatter

## Overview of Codebase 📙

This project follows Hexagonal Architecture. It is a multi-module gradle project and includes below modules.

- `api`: Exposes public APIs. _Entrypoint of the application_.
- `core`: Core Business Logic
- `port:db` Single source of truth (data)
- `port:http` Source of data from HTTP layer
- `migration`: Handles database migrations and control

## Development Setup 🖥

### With Docker
1. Build project: `./gradlew installDist`
2. Run docker compose: `docker-compose up -d`

Docker setup includes API, DB and DB migrations. The url to access the API - http://localhost:8080/api -  for example [HealthAPI](http://localhost:8080/api/health)

### 🗄️ Database Setup

- Download and install the latest [PostgreSQL package](https://www.postgresql.org/download/) as per your system need.
- After successful installation, create database for this project.  
  _For e.g. create a database named `paysintech_dev`._

```sql
CREATE DATABASE paysintech_dev;
```

### ⚙️ Project Setup

- You will require the latest stable version of JetBrains IntelliJ Idea IDE to build and run the server application. You can install the latest version from [here](https://www.jetbrains.com/idea/).
- Import project in IntelliJ IDE.
- Use existing Gradle wrapper for syncing project.
- Build 🔨 the project.

### 🔥 Setup Firebase

Firebase setup is necessary for authentication in the application.
Without this step, application won't run.

- Go to [Firebase Console](https://console.firebase.google.com/) and create new project.
- Navigate to _Project Settings_ -> _Service Accounts_ -> _Select 'Firebase Admin SDK'_ -> _Click 'Generate Private Key'_ -> _Save generated private key_ -> Rename it to _`firebase-adminsdk-config.json`_
- Copy config file to `api/src/main/resources` directory.

### ✈️ Running the Application

- Set up environment variables for database credentials as following with valid values as per your setup.

```bash
export SECRET_KEY=ANY_RANDOM_SECRET

export DATABASE_NAME=paysintech_dev
export DATABASE_HOST=localhost
export DATABASE_PORT=5432
export DATABASE_USER=postgres
export DATABASE_PASSWORD=postgres
export FIREBASE_API_KEY=API_KEY
```

- Finally, run the Gradle command:

```bash
./gradlew :api:run
```

***OR***

Use IntelliJ’s run configuration to run the API server application.

### 🧪 Running test cases

#### Using Gradle command

Run the command

```bash
./gradlew test
```

This Gradle command will execute all tests present in project.


#### Using Kotest plugin

- Install Kotest plugin in IntelliJ.
- In Test class, on the left side of class name (after line number) you'll see GREEN Play button 🟢▶️.
  Just click on that and test will be executed.


## Guidelines

### For writing migrations

- Add SQL migration files in the directory `migration/src/main/resources/sql`
- Give name to the files in the format: `YYYY_MM_DD_N_<DESCRIPTION_OF_A_CHANGE>.sql`
- Check whether migrations are running successfully by executing `./gradlew :migration:run`
