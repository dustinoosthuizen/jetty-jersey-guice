package com.webward.server;

import com.google.inject.servlet.GuiceFilter;
import com.webward.config.GuiceServletConfig;
import com.webward.servlet.EmptyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
public class Main {

    public static void main(String[] args) throws Exception {
//        EntityManager em = Persistence.createEntityManagerFactory("test1234").createEntityManager();
//
//        System.out.println(em);

        Server server = new Server(8080);
        ServletContextHandler root = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        root.addEventListener(new GuiceServletConfig());
        root.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        root.addServlet(EmptyServlet.class, "/*");
        server.start();
    }

}
