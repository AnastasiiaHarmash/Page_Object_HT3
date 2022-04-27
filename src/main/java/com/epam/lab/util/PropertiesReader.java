package com.epam.lab.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;

public class PropertiesReader {
    final static Logger logger = Logger.getLogger(PropertiesReader.class.getName());
    Properties property = new Properties();

    public PropertiesReader() {
        FileInputStream fis;
        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);
            fis.close();
        } catch (IOException e) {
            logger.info("Properties file is not exist!");
            System.err.println("ERROR: Properties file is not exist!");
        }
    }

    public String getUrl() {
        String URL = property.getProperty("URL");
        return URL;
    }

    public String getDriverName() {
        String driverName = property.getProperty("CHROME_DRIVER_NAME");
        return driverName;
    }

    public String getDriverLocation(){
        String driverLocation = property.getProperty("CHROME_DRIVER_LOCATION");
        return driverLocation;
    }
}
