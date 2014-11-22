package com.webward.test.integration.servlet;

import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import com.webward.resources.ItemResource;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;

import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.MessageBodyWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dustinosthzn on 2014/11/09.
 */
public class TestGuiceServletModule extends ServletModule {

    @Override
    protected void configureServlets() {
        install(new JpaPersistModule("testDB"));
        filter("/*").through(PersistFilter.class);
//        bind(MyInitializer.class);
        bind(ItemResource.class);

        bind(MessageBodyReader.class).to(JacksonJsonProvider.class);
        bind(MessageBodyWriter.class).to(JacksonJsonProvider.class);
        Map<String, String> initParams = new HashMap<String, String>();
        initParams.put("com.sun.jersey.config.feature.Trace",
                "true");
        serve("*").with(
                GuiceContainer.class,
                initParams);


    }
}
