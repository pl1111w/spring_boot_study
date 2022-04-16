package pl1111w.webservlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @title: pl1111w
 * @description:
 * @author: Kris
 * @date 2022/4/14 11:33
 */
@WebListener
@Slf4j
public class MyListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Servlet ServerInfo:  ,{}", sce.getServletContext().getServerInfo());
        ServletContextListener.super.contextInitialized(sce);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);
        log.info("context Destroyed....");
    }
}
