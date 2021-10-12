#### Stage 1: Build the application
FROM openjdk:11-jdk as build

# Set the current working directory inside the image
WORKDIR /app

# Copy maven executable to the image
COPY mvnw .
COPY .mvn .mvn

# Copy the pom.xml file
COPY pom.xml .

# Build all the dependencies in preparation to go offline. 
# This is a separate step so the dependencies will be cached unless 
# the pom.xml file has changed.
RUN ./mvnw dependency:go-offline -B

# Copy the project source
COPY src src

# Package the application
RUN ./mvnw package -DskipTests
#RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.war)

#### Stage 2: A minimal docker image with command to run the app 
FROM openjdk:11-jre AS customers-api

ARG DEPENDENCY=/app/target/dependency

# Copy project dependencies from the build stage
COPY --from=build /app/target/*.jar /opt/app/app.jar

ENV JAVA_OPTS="-Xdebug -Xrunjdwp:transport=dt_socket,address=8000,server=y,suspend=n"
ENV DB_HOST="customers-db"
ENV MYSQL_ROOT_PASSWORD="customersapp251"

ENTRYPOINT exec java $JAVA_OPTS -jar /opt/app/app.jar
