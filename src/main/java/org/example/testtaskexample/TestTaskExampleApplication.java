package org.example.testtaskexample;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.example.testtaskexample.config.DataConfiguration;
import org.example.testtaskexample.config.SecurityConfiguration;
import org.example.testtaskexample.config.WebConfiguration;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;

import java.util.EnumSet;


public class TestTaskExampleApplication {

    public static void main(String[] args) throws Exception {
        run();
    }


    private static void run() throws Exception {
        Server server = new Server(8080);

        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class, SecurityConfiguration.class, DataConfiguration.class);


        ServletContextHandler handler = new ServletContextHandler();

        context.setServletContext(handler.getServletContext());
        context.refresh();

        handler.setErrorHandler(null);

        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");

        handler.addEventListener(new ContextLoaderListener(context));

        handler.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain", context)),
                "/*", EnumSet.allOf(DispatcherType.class));

        server.setHandler(handler);
        server.start();
        System.out.println("Server is running.");
        server.join();

    }
}