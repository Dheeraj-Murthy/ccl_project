package com.meenigam.ccl_project_take_5.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class contextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("userid", null);
    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
