# Java Singleton Pattern - Complete Guide

A comprehensive Java project demonstrating four different Singleton design pattern implementations, each with trade-offs around thread safety, performance, and correctness. Includes unit tests with concurrent access validation.

## Implementations

### 1. Basic Singleton (Eager Initialization)

```java
BasicSingleton.getInstance();
```

- Instance created at class-loading time (`static` field initializer)
- Simple but wastes resources if the instance is never used
- Not truly lazy; the null-check in `getInstance()` is redundant since the field is already initialized

### 2. Lazy Singleton (Synchronized Method)

```java
LazySingleton.getInstance();
```

- Defers creation until the first call to `getInstance()`
- Thread-safe via `synchronized` on the entire method
- Straightforward but incurs synchronization overhead on every call, even after initialization

### 3. Thread-Safe Singleton (Double-Checked Locking)

```java
ThreadSafeSingleton.getInstance();
```

- Lazy initialization with minimal synchronization overhead
- Uses `volatile` + double-checked locking to avoid synchronizing after the instance exists
- Protects against cloning (`clone()` throws `UnsupportedOperationException`) and serialization attacks (`readResolve()` returns the existing instance)

### 4. Bill Pugh Singleton (Initialization-on-Demand Holder)

```java
BillPughSingleton.getInstance();
```

- Leverages the JVM's class-loading guarantee: the inner static class is loaded only when `getInstance()` is first called
- Thread-safe without explicit synchronization
- Widely considered the best classic approach (pre-enum)

## Comparison

| Variant | Lazy? | Thread-Safe? | Serialization-Safe? | Clone-Safe? | Sync Overhead |
|---------|-------|-------------|---------------------|-------------|---------------|
| Basic (Eager) | No | Yes | No | No | None |
| Lazy (Synchronized) | Yes | Yes | No | No | Every call |
| Thread-Safe (DCL) | Yes | Yes | Yes | Yes | First call only |
| Bill Pugh (Holder) | Yes | Yes | No | No | None |

## Project Structure

```
src/
 main/java/com/singleton/
   BasicSingleton.java
   LazySingleton.java
   ThreadSafeSingleton.java
   BillPughSingleton.java
 test/java/com/singleton/
   BasicSingletonTest.java
   LazySingletonTest.java
   ThreadSafeSingletonTest.java
   BillPughSingletonTest.java
```

## Prerequisites

- Java 17+
- Maven 3.8+

## Build & Test

```bash
mvn clean install
```

Run tests only:

```bash
mvn test
```

## Tests

Each implementation has a corresponding JUnit 5 test class verifying:

- **Non-null** - `getInstance()` never returns null
- **Identity** - repeated calls return the same object reference

The `ThreadSafeSingleton` and `BillPugh` tests additionally validate correctness under **concurrent access** using 32 threads with a `CountDownLatch` barrier to maximize contention.
