package com.webward.config;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;
import com.webward.servlet.GuiceServletModule;

/**
 * Example Guice Server configuration. Creates an Injector, and binds it to
 * whatever Modules we want. In this case, we use an anonymous Module, but other
 * modules are welcome as well.
 */
public class GuiceServletConfig extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector(new GuiceServletModule());
    }
}
