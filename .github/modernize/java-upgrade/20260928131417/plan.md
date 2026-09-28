# Upgrade Plan: quan-ly-nhan-vien (20260928131417)

- **Generated**: 2026-09-28 13:14:17
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 17.0.12: C:\Program Files\Java\jdk-17\bin (available, legacy toolchain)
- JDK 17.0.18: C:\Users\Admin\.jdks\ms-17.0.18\bin (available)
- JDK 17.0.19: C:\Users\Admin\.jdks\ms-17.0.19\bin (available)
- JDK 23: C:\Program Files\Java\jdk-23\bin (available, intermediate toolchain)
- JDK 25: **<TO_BE_INSTALLED>** (required by Step 1 for final target)
- JDK 11: not available (baseline will be skipped)

**Build Tools**
- Maven: **<TO_BE_INSTALLED>** (required by Step 1; this project has no Maven wrapper)

## Guidelines

- Upgrade the Java runtime to the latest LTS release: Java 25.
- Prefer a minimal build-only migration; this project already targets Jakarta Servlet 5 and does not rely on removed JDK internals.
- Keep compatibility with the existing web app packaging and runtime configuration.
- Run the required compile/test verification after each meaningful upgrade step.

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260928131417
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java 25

## Technology Stack

| Technology/Dependency | Current | Min Compatible Version | Why Incompatible |
| ---------------------- | ------- | ---------------------- | --------------- |
| Java | 11 | 25 | User requested latest LTS target |
| Maven | not installed | 3.9.0+ | Required for Java 25 toolchain and build execution |
| maven-compiler-plugin | 3.11.0 | 3.11.0+ | Java 25 supports newer compiler versions; plugin should be modernized to a current release |
| jakarta.servlet | 5.0.0 | 5.0.0 | Already compatible with modern Java runtimes |
| log4j-api / log4j-core | 2.17.2 | 2.17.2+ | Current release remains compatible with Java 25 |
| junit | 4.13.2 | 4.13.2 | Test framework is still compatible with JDK 25; verification is needed |

## Derived Upgrades

- Java 25 target requires installing a Java 25 JDK and a modern Maven build tool.
- Maven 3.9+ is the recommended baseline for Java 25; Maven 3.8.x is EOL and should be avoided.
- The project already uses Jakarta Servlet 5.0, so no container-namespace migration is needed.
- maven-compiler-plugin should be updated to a current release compatible with Java 25; the source/target values should be aligned to 25.
- No Spring Boot or framework migration is required because the project is a plain Servlet/JSP web application without a framework upgrade requirement.

## Impact Analysis

### Subsection: Dependency Changes

| File | Dependency | Current | Action | Target | Reason |
|------|------------|---------|--------|--------|--------|
| pom.xml | maven.compiler.source | 11 | upgrade | 25 | User requested Java 25 runtime |
| pom.xml | maven.compiler.target | 11 | upgrade | 25 | User requested Java 25 runtime |
| pom.xml | maven-compiler-plugin | 3.11.0 | upgrade | 3.14.0 | Better compatibility with modern JDK toolchains |
| pom.xml | jakarta.servlet-api | 5.0.0 | keep | 5.0.0 | Already compatible; no migration needed |

### Subsection: Source Code Changes

| File | Location | Current | Required Change | Reason |
|------|----------|---------|----------------|--------|
| None | N/A | N/A | No source-level code rewrite required | Project does not use removed JDK internals or framework APIs incompatible with Java 25 |

### Subsection: Configuration Changes

| File | Property/Setting | Current | Required Change | Reason |
|------|------------------|---------|----------------|--------|
| None | N/A | N/A | No application.properties or YAML changes required | Servlet/JSP app uses standard config and no framework property migration |

### Subsection: CI/CD Changes

| File | Location | Current | Required Change |
|------|----------|---------|----------------|
| None | N/A | N/A | No CI/CD files detected with hardcoded Java 11 runtime references |

### Subsection: Risks & Warnings

- **Toolchain availability**: Maven is not installed and the current project does not include a wrapper. **Mitigation**: Install Maven 3.9+ and Java 25 in Step 1 before build verification.
- **No direct baseline available**: JDK 11 is not installed in the system environment, so a pre-upgrade baseline cannot be run. **Mitigation**: Record the skip and proceed with a clean Java 25 compile/test validation after the upgrade.
- **Dependency drift risk**: The project uses `mysql-connector-j` 8.3.0 and Log4j 2.17.2, which are compatible with modern Java but should be revalidated under Java 25 during runtime checks. **Mitigation**: Run the full Maven test target after the build changes; fix any compatibility issues immediately.

## Upgrade Steps

- Step 1: Install required Java 25 and Maven toolchain
  - **Rationale**: The project currently has no Maven installation and no Java 25 runtime. A valid build/test toolchain is required before compilation can be verified.
  - **Changes to Make**: Install JDK 25 and Maven 3.9+; confirm both are available in the environment for the build.
  - **Verification**: Use the provided JDK and Maven installation checks; expected result is all required toolchain entries available.

- Step 2: Configure the project for Java 25
  - **Rationale**: The Java source and target levels must be aligned to the target JDK; this is the direct runtime upgrade for the application.
  - **Changes to Make**: Apply all Dependency Changes in the pom.xml build configuration and update the compiler plugin to a modern Java 25-compatible version.
  - **Verification**: Run `mvn clean test-compile -q` using JDK 25; expected result is successful compilation of main and test sources.

- Step 3: Verify the upgraded project under Java 25
  - **Rationale**: The app is a lightweight Servlet/JSP project, so the main verification risk is runtime compatibility rather than framework-specific migration.
  - **Changes to Make**: Run the full Maven test lifecycle after compile success and fix any classpath or compatibility issues surfaced by the Java 25 toolchain.
  - **Verification**: Run `mvn clean test -q` using JDK 25; expected result is a clean pass or a documented test failure that is fixed before completion.

- Step 4: CVE validation and dependency check
  - **Rationale**: Security validation is required for the upgraded dependency set and direct dependencies.
  - **Changes to Make**: Extract direct dependencies, scan for CVEs, and apply minimal version upgrades if needed.
  - **Verification**: Run `mvn dependency:list -DexcludeTransitive=true` and the CVE validation tool; expected result is no unpatched direct dependency vulnerabilities or a documented remediation.

- Step 5: Final Validation
  - **Rationale**: This is the final gates step to confirm the project builds and all tests pass under Java 25.
  - **Changes to Make**: Resolve any remaining issues from the prior steps and validate the final project state.
  - **Verification**: Run `mvn clean test -q` on JDK 25; expected result is 100% pass rate or a documented and fixed exception.
