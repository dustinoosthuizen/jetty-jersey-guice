package com.webward.test.integration.resources;


import com.google.inject.servlet.GuiceFilter;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.webward.dto.ItemDto;
import com.webward.servlet.EmptyServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.junit.*;
//import org.testng.annotations.Test;

import javax.servlet.DispatcherType;
import java.util.EnumSet;
import com.webward.test.integration.config.TestGuiceServletConfig;

/**
 * Created by dustinosthzn on 2014/11/16.
 */


public class TestItems {

    private  static Server server;

    @BeforeClass
    public  static void before() throws Exception
    {
        System.out.println("starting up server");
        server = new Server(9090);
        server.setStopAtShutdown(true);
        ServletContextHandler root = new ServletContextHandler(server, "/", ServletContextHandler.SESSIONS);
        root.addEventListener(new TestGuiceServletConfig());
        root.addFilter(GuiceFilter.class, "/*", EnumSet.of(DispatcherType.REQUEST));
        root.addServlet(EmptyServlet.class, "/*");
        server.start();
        System.out.println("test server started");
    }

    @Test
    public void testCustomObject()
    {
        Client client = Client.create();

        ItemDto createdItem = create(client);
        ItemDto queriedItem = query(client);
        Assert.assertEquals(createdItem.getName(),queriedItem.getName());
        delete( client, queriedItem.getId());

    }

    public ItemDto create(Client client)
    {
        System.out.println("create");
        ItemDto item = new ItemDto();
        item.setName("TestName");
        WebResource webResource = client.resource(getBaseURI()).path("item").path("create");
        ClientResponse response = webResource.accept("application/json").type("application/json")
                .post(ClientResponse.class,item);
        return response.getEntity(ItemDto.class);
    }

    public ItemDto query(Client client)
    {
        System.out.println("query");
        WebResource webResource = client.resource(getBaseURI()).path("item").path("getByName").path("TestName");
        ClientResponse response = webResource.accept("application/json")
                .get(ClientResponse.class);
        return response.getEntity(ItemDto.class);
    }

    public void delete(Client client, Integer id)
    {
        System.out.println("delete");
        WebResource webResource = client.resource(getBaseURI()).path("item").path("deleteById").path(id+"");
        ClientResponse response = webResource.accept("application/json")
                .delete(ClientResponse.class);


    }

    @AfterClass
    public static void after() throws Exception
    {
        System.out.println("shutting down server");
        server.stop();
        System.out.println("test server stopped");

    }

    public String getBaseURI()
    {
        return "http://localhost:9090";
    }

}
