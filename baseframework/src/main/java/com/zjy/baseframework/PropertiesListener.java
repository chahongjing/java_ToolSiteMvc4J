package com.zjy.baseframework;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

/**
 * Application Lifecycle Listener implementation class PropertiesListener
 */
public class PropertiesListener implements ServletContextListener {

    /**
     * Default constructor.
     */
    public PropertiesListener() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
    }

    /**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent arg0) {
        // TODO Auto-generated method stub
        InputStream is = getClass().getClassLoader().getResourceAsStream("/application.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
            for (Map.Entry<Object, Object> kp : properties.entrySet()) {
                String key = (String) kp.getKey();
                String value = (String) kp.getValue();

                PropertiesHelper.getInstance().setProperties(key, value);
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

}
