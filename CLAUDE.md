# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

This is the official Twilio Java SDK (twilio-java), a Java library for interacting with Twilio's REST APIs. The SDK supports Java 8, 11, and 17, and uses Maven as its build system.

## Common Development Commands

### Building and Testing
```bash
# Install dependencies and build
mvn clean install

# Run all tests
mvn test

# Run a specific test class
mvn test -Dtest=ClassName

# Run a specific test method
mvn test -Dtest=ClassName#methodName

# Skip tests during build
mvn package -Dmaven.test.skip=true

# Run checkstyle
mvn checkstyle:check

# Generate Javadocs
mvn javadoc:javadoc

# Run cluster tests (requires environment variables)
mvn test -DTest="ClusterTest" -B
```

### Code Coverage
```bash
# Generate coverage report (enabled by default)
mvn test
# Report will be in target/site/jacoco/
```

## High-Level Architecture

### Package Structure

1. **`com.twilio`** - Core initialization and configuration
   - `Twilio.java` - Main entry point, manages global SDK configuration
   - `Domains.java` - Domain configuration for Twilio services

2. **`com.twilio.rest`** - Auto-generated REST API clients
   - Each Twilio product has its own subpackage (api, chat, messaging, etc.)
   - Files with OpenAPI Generator headers are auto-generated and should not be manually edited
   - Changes to these files require upstream API definition updates

3. **`com.twilio.http`** - HTTP client abstraction layer
   - `TwilioRestClient` - Main REST client implementation
   - `NetworkHttpClient` - Default HTTP client using Apache HttpClient 5
   - `ValidationClient` - PKCV authentication support
   - Supports custom HTTP client implementations

4. **`com.twilio.auth_strategy`** - Authentication strategies
   - `BasicAuthStrategy` - Traditional username/password auth
   - `TokenAuthStrategy` - OAuth/Bearer token authentication
   - `NoAuthStrategy` - For endpoints that don't require authentication

5. **`com.twilio.credential`** - Credential providers
   - `ClientCredentialProvider` - OAuth client credentials flow
   - `OrgClientCredentialProvider` - Organization-scoped credentials
   - Supports custom credential provider implementations

6. **`com.twilio.jwt`** - JWT generation for client-side SDKs
   - Access tokens for Video, Voice, Chat, etc.
   - Capability tokens for TaskRouter
   - Validation tokens for webhook request validation

7. **`com.twilio.twiml`** - TwiML response builders
   - Builder pattern for creating TwiML XML responses
   - Separate packages for voice, messaging, fax, and video

8. **`com.twilio.base`** - Base classes for resources
   - `Resource` - Base class for all API resources
   - `Creator`, `Fetcher`, `Reader`, `Updater`, `Deleter` - Operation base classes

### Key Architectural Patterns

1. **Resource Pattern**: Each API resource follows a consistent pattern with static methods for operations:
   - `Resource.creator()` - Create new resources
   - `Resource.fetcher()` - Fetch single resources
   - `Resource.reader()` - List/iterate resources
   - `Resource.updater()` - Update existing resources
   - `Resource.deleter()` - Delete resources

2. **Builder Pattern**: Used extensively for:
   - Creating API requests with optional parameters
   - Building TwiML responses
   - Configuring HTTP clients

3. **Singleton Pattern**: The `Twilio` class manages global configuration as a thread-safe singleton

4. **Async Support**: All resource operations support async variants using `CompletableFuture`

## Authentication Methods

The SDK supports multiple authentication methods:

1. **Basic Authentication** (default):
   ```java
   Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
   ```

2. **OAuth/Client Credentials**:
   ```java
   Twilio.init(new ClientCredentialProvider(CLIENT_ID, CLIENT_SECRET), ACCOUNT_SID);
   ```

3. **API Key Authentication**:
   ```java
   Twilio.init(API_KEY_SID, API_KEY_SECRET, ACCOUNT_SID);
   ```

4. **Custom Token Manager**: For advanced OAuth scenarios

## Important Notes

1. **Generated Code**: Files with the OpenAPI Generator header should not be manually edited. They are regenerated from API definitions.

2. **Environment Variables**: The SDK automatically reads:
   - `TWILIO_ACCOUNT_SID`
   - `TWILIO_AUTH_TOKEN`
   - `TWILIO_REGION`
   - `TWILIO_EDGE`

3. **Beta Features**: Classes/methods marked with `@Beta` annotation are subject to change

4. **Logging**: Uses SLF4J for logging. Configure via log4j2.xml or your preferred SLF4J implementation

5. **Error Handling**: All API errors throw `ApiException` or its subclasses

6. **Thread Safety**: The `Twilio` class and `TwilioRestClient` are thread-safe

## Testing Approach

- Unit tests use JUnit 4 and are located in `src/test/java`
- Mock HTTP responses using `com.squareup.okhttp3:mockwebserver`
- Cluster tests require actual Twilio credentials and test against live API
- Test files follow the naming pattern `*Test.java`
- Use `@Test` annotation for test methods

## Release Process

- Version follows modified Semantic Versioning (see VERSIONS.md)
- Releases are automated via GitHub Actions when tags are pushed
- Publishes to Maven Central via Sonatype
- Docker images are built and pushed to Docker Hub