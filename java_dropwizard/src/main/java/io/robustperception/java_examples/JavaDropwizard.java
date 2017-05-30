package io.robustperception.java_examples;

import com.codahale.metrics.Counter;
import com.codahale.metrics.MetricRegistry;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.dropwizard.DropwizardExports;

import io.prometheus.client.exporter.MetricsServlet;
import io.prometheus.client.hotspot.DefaultExports;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;


public class JavaDropwizard {
  // Create registry for Dropwizard metrics.
  static final MetricRegistry metrics = new MetricRegistry();
  // Create a Dropwizard counter.
  static final Counter counter = metrics.counter("my_example_counter_total");

  public static void main( String[] args ) throws Exception {
      // Increment the counter.
      counter.inc();

      // Hook the Dropwizard registry into the Prometheus registry
      // via the DropwizardExports collector.
      CollectorRegistry.defaultRegistry.register(new DropwizardExports(metrics));


      // Expose Prometheus metrics.
      Server server = new Server(1234);
      ServletContextHandler context = new ServletContextHandler();
      context.setContextPath("/");
      server.setHandler(context);
      context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
      // Add metrics about CPU, JVM memory etc.
      DefaultExports.initialize();
      // Start the webserver.
      server.start();
      server.join();
  }
}
