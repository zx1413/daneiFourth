package cn.dszx.listen;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class Mylisten implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("this is start");
    }
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("this is destroy");
    }
}
