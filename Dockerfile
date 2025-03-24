# Using a light image of JDK 17 for compilation
FROM eclipse-temurin:17-jdk AS builder

# Set the working directory inside the container
WORKDIR /app

# Copy maven files and source code to optimize cache
COPY mvnw ./
COPY .mvn .mvn
COPY pom.xml ./
COPY src ./src

# Give execution permission to Maven Wrapper
RUN chmod +x mvnw

# Build the project no executing tests
RUN ./mvnw clean package -DskipTests

# Extract generated Jar file
RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

# New image based on OpenJDK 17 for production
FROM eclipse-temurin:17-jre

# Configuration to reduce the memory use on containers
ENV JAVA_OPTS="-XX:+UseContainerSupport -XX:MaxRAMPercentage=75"

# Directory path inside the Container
WORKDIR /app

# Copy the application from the built image
COPY --from=builder /app/target/*.jar article-indexer-scheduler.jar

# Exposing the Application Port
EXPOSE 9090

# Execution of the app with optimized parameters.
ENTRYPOINT ["java", "-jar", "article-indexer-scheduler.jar"]