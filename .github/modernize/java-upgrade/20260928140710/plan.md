# Upgrade Plan: quan-ly-nhan-vien (20260928140710)

- **Generated**: 2026-09-28 14:07
- **HEAD Branch**: main
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 11: not available (baseline will be skipped)
- JDK 25: **<TO_BE_INSTALLED>** (required by step 3)

**Build Tools**
- Maven: **<TO_BE_INSTALLED>** (wrapper not present; required to compile and test)
- Maven Wrapper: not present in repository root

## Guidelines

- Upgrade the application runtime to Java 25 while preserving existing functionality and deployment behavior.
- Keep the change set minimal and focused on the build/runtime configuration.
- Preserve the Jakarta Servlet implementation already in use and avoid refactoring unrelated code.
- Keep the application compatible with the current MySQL and Log4j dependencies.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260928140710
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25 (latest LTS)

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| ---------------------- | ------- | ---------------------- | --------------- |
| Java | 11 | 25 | User requested Java 25 upgrade |
| Maven | not installed in environment | 3.9+ | Required for Java 25 build validation; wrapper is absent |
| maven-compiler-plugin | 3.11.0 | 3.11.0 | Must compile with Java 25 source/target values |
| jakarta.servlet-api | 5.0.0 | 5.0.0 | Already on Jakarta namespace; no framework migration required |
| mysql-connector-j | 8.3.0 | 8.3.0 | Compatible with Java 25 |
| log4j-core | 2.17.2 | 2.17.2 | Compatible with Java 25 |
| JUnit | 4.13.2 | 4.13.2 | Compatible with Java 25 |

## Derived Upgrades

- Java 25 requires updating the project compiler settings from Java 11 to Java 25 in `pom.xml`.
- Maven must be installed at 3.9+ because the repo has no wrapper and the build environment currently has no Maven present.
- The project is a plain servlet/JDBC web app without Spring Boot or other framework-managed runtime, so no framework migration is required.
- The current codebase does not show Java-internal reflection or `javax.*` API usage, which reduces the amount of source-level migration work.

## Impact Analysis

### Subsection: Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
| ---- | ---------- | ------- | ------ | ------ | ------ |
| pom.xml | `maven.compiler.source` | 11 | upgrade | 25 | User requested Java 25 runtime |
| pom.xml | `maven.compiler.target` | 11 | upgrade | 25 | User requested Java 25 runtime |
| pom.xml | `maven-compiler-plugin` configuration | source/target=11 | upgrade | source/target=25 | Compiler must match target runtime |
| pom.xml | Maven build environment | not installed | install | 3.9+ | Required for Java 25 validation |

### Subsection: Source Code Changes

| File | Location | Current | Required Change | Reason |
| ---- | -------- | ------- | --------------- | ------ |
| Static source scan | `src/main/java/**` | no `sun.*`, `jdk.internal.*`, or `javax.*` patterns | no source rewrite required | No Java 25 breaking patterns were detected in the codebase |

### Subsection: Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
| ---- | ---------------- | ------- | --------------- | ------ |
| No application config file found | N/A | N/A | No config migration needed | This project uses servlet/JDBC settings and direct Java code |

### Subsection: CI/CD Changes

| File | Location | Current | Required Change |
| ---- | -------- | ------- | --------------- |
| No CI/CD files detected | repository root | N/A | No pipeline changes required in the checked-in project |

### Subsection: Risks & Warnings

- **Build environment is currently missing JDK and Maven**: The environment needs provisioning before verification can occur. **Mitigation**: install the required JDK 25 and Maven 3.9+ before running the compile/test loop.
- **No tests are present in the project**: `mvn test` may execute zero tests and still pass. **Mitigation**: use compile and test verification as the success gate, and record zero-test execution explicitly in the summary.
- **Deployment target runtime is not managed in-repo**: If the deployed servlet container is older than Java 25, runtime issues could still appear outside the Maven build. **Mitigation**: validate the app in a Java 25-capable servlet runtime during final verification.

## Upgrade Steps

- Step 1: Install Java 25 and Maven 3.9+
  - **Rationale**: The environment has no JDK or Maven installed, so there is no way to compile or test the project against the requested runtime.
  - **Changes to Make**: Install required JDK and Maven using the project toolchain requirements; no app source change yet.
  - **Verification**: `#appmod-list-jdks` and `#appmod-list-mavens`; expected result is JDK 25 and Maven available.

- Step 2: Setup Baseline
  - **Rationale**: The project’s current Java baseline is 11, but the base JDK is not installed in the environment; the baseline step is intentionally skipped to avoid a false failure.
  - **Changes to Make**: Skip validation because the Java 11 baseline toolchain is unavailable.
  - **Verification**: `status: "skipped"` with rationale captured in `progress.md` and summary.

- Step 3: Upgrade Maven Compiler Settings to Java 25
  - **Rationale**: The project compiles against Java 11 in `pom.xml`; this is the direct compatibility blocker for the target runtime.
  - **Changes to Make**: Apply all Dependency Changes in the build config and update compiler source/target settings to Java 25.
  - **Verification**: `mvn clean test-compile -q` with JDK 25; expected result is successful compilation.

- Step 4: Validate Compile and Test Execution on Java 25
  - **Rationale**: The final success criteria require a full compile/test check after the runtime upgrade.
  - **Changes to Make**: Fix any build errors or compatibility issues caused by the Java 25 toolchain.
  - **Verification**: `mvn clean test -q` with JDK 25; expected result is pass/fail status with all tests green or documented blockers.

- Step 5: Final Validation and Summary
  - **Rationale**: Confirm the upgrade meets the Java 25 target, record all build/test evidence, and summarize remaining risks.
  - **Changes to Make**: Resolve any final compatibility issues and generate the summary artifact.
  - **Verification**: Final `mvn clean test` run with Java 25, then summary generation.
