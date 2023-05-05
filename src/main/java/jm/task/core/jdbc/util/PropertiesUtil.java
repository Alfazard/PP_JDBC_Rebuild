package jm.task.core.jdbc.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author Alfazard on 04.05.2023
 */
public final class PropertiesUtil {
    private static final Properties PROPERTIES = new Properties();

    private PropertiesUtil() {
    }

    static {
        LoadProperties();
    }

    private static void LoadProperties() {
        try (InputStream inputStream = PropertiesUtil.class
                .getClassLoader()
                .getResourceAsStream("application.properties")
        ) {
            PROPERTIES.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String get(String key) {
        return PROPERTIES.getProperty(key);
    }
}
