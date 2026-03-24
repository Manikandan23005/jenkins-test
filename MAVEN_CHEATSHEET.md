# Maven (mvn) Quick Reference Cheat Sheet

## Basic Commands

### Project Lifecycle
```bash
mvn clean                    # Remove target directory
mvn compile                  # Compile source code
mvn test                     # Run unit tests
mvn package                  # Package compiled code into JAR/WAR
mvn install                  # Install package to local repository
mvn deploy                   # Deploy to remote repository
mvn verify                   # Run integration tests and plugins

# Combined commands
mvn clean compile            # Clean then compile
mvn clean test               # Clean then test
mvn clean package            # Clean then package (most common)
mvn clean install            # Clean then install locally
mvn clean verify             # Clean then full verification
mvn clean deploy             # Clean then deploy to remote
```

## Common Goals

### Compilation & Building
```bash
mvn compile                  # Compile main sources
mvn test-compile             # Compile main + test sources
mvn clean compile -q         # Quiet mode (less output)
mvn compile -DskipTests      # Compile, skip test compilation
```

### Testing
```bash
mvn test                     # Run all tests
mvn test -Dtest=TestClass   # Run specific test class
mvn test -Dtest=TestClass#testMethod  # Run specific test method
mvn test -Dtest=*IT         # Run integration tests only
mvn test -DskipTests         # Skip test execution during package
mvn test -Dmaven.test.skip=true  # Skip test compilation and execution
```

### Packaging & Deployment
```bash
mvn package                  # Create JAR/WAR
mvn jar:jar                  # Create JAR explicitly
mvn war:war                  # Create WAR
mvn install                  # Install to local repo (~/.m2/repository)
mvn deploy                   # Deploy to remote repository
```

### Documentation & Reporting
```bash
mvn javadoc:javadoc         # Generate JavaDoc
mvn site                     # Generate project site
mvn site:deploy              # Deploy site documentation
```

## Dependency Management

```bash
mvn dependency:tree          # Show dependency tree
mvn dependency:tree -Dverbose=true  # Verbose dependency tree
mvn dependency:analyze       # Analyze unused/undeclared dependencies
mvn dependency:list          # List all dependencies
mvn dependency:copy-dependencies  # Copy dependencies to lib folder
mvn dependency:purge-local-repository  # Clear local repo cache
mvn dependency:resolve       # Resolve all dependencies
mvn dependency:resolve-plugins  # Resolve plugin dependencies
mvn help:describe -Dplugin=groupId:artifactId:version
                             # Show plugin details
```

## Repository & Resolution

```bash
mvn clean install -U         # Update snapshots/releases
mvn clean install -o         # Offline mode (no external repos)
mvn clean install -X         # Debug mode (verbose)
mvn help:active-profiles     # Show active profiles
mvn help:effective-pom       # Show effective POM (with defaults)
mvn help:system              # Show system properties
```

## Version & Build Info

```bash
mvn --version                # Show Maven version
mvn -v                       # Short version info
mvn -version                 # Full version info with Java
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin
                             # Show plugin info
mvn buildnumber:create       # Generate build number
```

## Useful Options & Flags

```bash
-DskipTests                  # Skip running tests
-Dmaven.test.skip=true       # Skip test compilation + execution
-X, --debug                  # Debug output
-q, --quiet                  # Quiet output (minimal)
-e, --errors                 # Show stack traces
-U, --update-snapshots       # Update snapshot dependencies
-o, --offline                # Work offline (no remote repos)
-P profile1,profile2         # Activate profiles
-pl :module1,:module2        # Build specific modules (multi-module)
-am                          # Build modules and their dependencies
-amd                         # Build modules and dependents
-T 4                         # Parallel build (4 threads)
-DskipITs                    # Skip integration tests
-Dorg.slf4j.simpleLogger.defaultLogLevel=debug  # Change logging level
```

## Plugin-Specific Commands

### Java Compiler Plugin
```bash
mvn compile                  # Compile with default settings
mvn compiler:help            # Show compiler plugin help
mvn compiler:testCompile.help  # Help for test compile

# In pom.xml properties:
<maven.compiler.source>21</maven.compiler.source>
<maven.compiler.target>21</maven.compiler.target>
```

### Surefire (Testing)
```bash
mvn surefire:test           # Run tests with surefire
mvn surefire-report:report  # Generate test report
mvn surefire:help           # Show surefire help
```

### JAR Plugin
```bash
mvn jar:jar                 # Create JAR
mvn jar:test-jar           # Create test JAR
```

### Assembly Plugin
```bash
mvn assembly:single         # Create assembly (JAR with dependencies)
```

### Shade Plugin
```bash
mvn shade:shade             # Create shaded/fat JAR
```

### Clean Plugin
```bash
mvn clean:clean             # Remove target directory
mvn clean:help              # Show clean plugin help
```

### Javadoc Plugin
```bash
mvn javadoc:javadoc        # Generate JavaDoc
mvn javadoc:jar            # JAR JavaDoc
mvn javadoc:help           # Show help
```

### Source Plugin
```bash
mvn source:jar              # Create sources JAR
mvn source:jar-no-fork      # Without forking
```

### Failsafe (Integration Tests)
```bash
mvn failsafe:integration-test  # Run integration tests
mvn failsafe:verify            # Verify integration test results
```

## Multi-Module Projects

```bash
mvn clean install           # Build all modules
mvn clean install -pl module1  # Build specific module only
mvn clean install -pl module1,module2  # Build multiple modules
mvn clean install -am        # Build module + dependencies
mvn clean install -amd       # Build module + dependents
mvn -f path/to/pom.xml clean install  # Specify POM file
```

## Advanced Usage

### Parallel Builds
```bash
mvn -T 4 clean install      # Build with 4 threads
mvn -T 0.5C clean install   # Use 0.5 × CPU cores
mvn -T 1C clean install     # Use 1 × CPU cores
```

### Property Management
```bash
mvn clean install -Dversion=2.0.0  # Set custom version
mvn clean install -Dskip.unit.tests=true  # Custom property
mvn help:describe -Dplugin=org.apache.maven.plugins:maven-compiler-plugin:3.8.0
                             # Specific plugin version details
```

### Profiles
```bash
mvn clean install -P dev    # Activate 'dev' profile
mvn clean install -P dev,mysql  # Multiple profiles
mvn help:active-profiles    # List active profiles
mvn help:all-profiles       # Show all defined profiles
```

### Build Phases (in order)
```
validate      → validate project structure
compile       → compile source code
test          → run tests
package       → package compiled code
verify        → run checks/verify package
install       → install to local repository
deploy        → deploy to remote repository
```

### Useful Combinations

```bash
# Clean build everything
mvn clean install

# Quick build (skip tests)
mvn clean install -DskipTests

# Build with debug output
mvn clean install -X

# Build specific module in multi-module
mvn clean install -pl :mymodule

# Parallel build (faster)
mvn -T 4 clean install

# Update dependencies
mvn clean install -U

# Generate reports
mvn clean install site

# Offline build
mvn clean install -o

# Show dependency tree
mvn dependency:tree

# Find unused dependencies
mvn dependency:analyze

# Generate JavaDoc
mvn javadoc:javadoc

# Create executable JAR
mvn clean package shade:shade
```

## Troubleshooting Commands

```bash
mvn -X                      # Verbose/Debug output
mvn -e                      # Show full error traces
mvn -U                      # Update snapshots
mvn clean                   # Clean build files
mvn help:active-profiles    # Check active profiles
mvn help:effective-pom      # Show merged POM
mvn dependency:tree -Dverbose  # Detailed dependency tree
mvn -o clean install        # Offline mode (use cached)
mvn clean install -ntp      # No transfer progress (faster)
```

## Common Issues & Solutions

```bash
# Memory issues
export MAVEN_OPTS="-Xms512m -Xmx1024m"
mvn clean install

# Use different Java version
export JAVA_HOME=/path/to/java/version
mvn clean install

# Clear repository cache
mvn dependency:purge-local-repository
rm -rf ~/.m2/repository

# Rebuild from scratch
mvn clean install -U

# Skip GPG signing (for deploy)
mvn deploy -Dgpg.skip

# Skip tests during package
mvn package -DskipTests
```

## Maven Wrapper (mvnw)

```bash
./mvnw clean install        # Linux/Mac
mvnw.cmd clean install      # Windows
./mvnw --version            # Check wrapper version
```

## Best Practices

```bash
# ✅ Good practices
mvn clean verify            # Use verify for CI/CD
mvn -T 4 clean install      # Enable parallel builds
mvn help:effective-pom      # Check final POM configuration
mvn dependency:analyze      # Find unused dependencies

# ❌ Avoid
-DskipTests=true           # Use -DskipTests instead
mvn clean && mvn install   # Chain as: mvn clean install
rm -rf ~/.m2               # Clear entire cache; use purge instead
```

---

**For full documentation**, visit: https://maven.apache.org/docs/
