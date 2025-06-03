# Use Java 17 as base image
FROM eclipse-temurin:17-jdk-alpine

# Install Python and required packages
RUN apk add --no-cache python3 py3-pip && \
    pip3 install nltk

# Set working directory
WORKDIR /app

# Copy Maven wrapper and pom.xml
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .

# Copy source code
COPY src src

# Download NLTK data
RUN python3 -c "import nltk; nltk.download('vader_lexicon')"

# Build the application
RUN ./mvnw package -DskipTests

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "target/review-0.0.1-SNAPSHOT.jar"] 