package lesson41;

import java.io.IOException;
import java.util.Properties;


public final class PropertesUtils {
    private static final Properties PROPERTIES = new Properties();

    static{
        loadProperties();
    }

    private static void loadProperties() {
        try (var inputStream = PropertesUtils.class.getClassLoader()
                .getResourceAsStream("app.properties")) {
            PROPERTIES.load(inputStream);
        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private PropertesUtils() {
    }
}
