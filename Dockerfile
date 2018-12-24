# Use OpenJDK as parent
FROM openjdk:8

# Set the working directory to /app
WORKDIR /app

# Copy the jar into the container at /app
COPY target/pizzola-0.1.0.jar /app
COPY target/classes/lib /app/lib

# Run app.py when the container launches
CMD ["java", "-cp", "pizzola-0.1.0.jar:lib/*", "io.axonif.pizzola.MainKt"]
