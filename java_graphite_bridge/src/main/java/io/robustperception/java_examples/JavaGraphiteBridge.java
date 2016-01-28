package io.robustperception.java_examples;

import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.bridge.Graphite;
import io.prometheus.client.hotspot.DefaultExports;

public class JavaGraphiteBridge {
  public static void main(String[] args) throws Exception {
      // Add metrics about CPU, JVM memory etc.
      DefaultExports.initialize();

      Graphite g = new Graphite("localhost", 2003);
      // Send to graphite every 60s.
      Thread thread = g.start(CollectorRegistry.defaultRegistry, 60);
      thread.join();  // Waits forever.
  }
}
