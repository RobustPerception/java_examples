# Simple Prometheus Java Example

This is a simple example of how to export a Counter with Prometheus.

For more information see http://www.robustperception.io/instrumenting-java-with-prometheus/

## Running the Example

First, build the project:

```bash
mvn clean package
```

Then run the application:

```bash
mvn exec:java -Dexec.mainClass="io.prometheus.SimpleServer" -Dexec.args="1234"
```

Now you can access the metrics at http://localhost:1234/metrics

## Running the JAR

```bash
mvn clean package

java -jar target/java_simple-1.0-SNAPSHOT-jar-with-dependencies.jar 1234
```
