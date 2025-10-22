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
        // Создание сервера Jetty
        Server server = new Server(8080);

        // Создание Spring-контекста и регистрация конфигурационных классов
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class, SecurityConfiguration.class, DataConfiguration.class);

        // Создание обработчика для Jetty и привязка Spring-контекст к ServletContext
        ServletContextHandler handler = new ServletContextHandler();
        context.setServletContext(handler.getServletContext());
        context.refresh();

        // Настройка обработчика
        handler.setErrorHandler(null);
        handler.setContextPath("/");
        handler.addServlet(new ServletHolder(new DispatcherServlet(context)), "/*");
        handler.addEventListener(new ContextLoaderListener(context));

        // Подключение фильтра Spring Security
        handler.addFilter(new FilterHolder(new DelegatingFilterProxy("springSecurityFilterChain", context)),
                "/*", EnumSet.allOf(DispatcherType.class));

        // Привязка обработчика запросов к Jetty
        server.setHandler(handler);
        server.start();
        System.out.println("Server is running.");
        server.join();
    }
}