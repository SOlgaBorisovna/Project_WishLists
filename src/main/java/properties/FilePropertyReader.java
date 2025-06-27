package properties;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FilePropertyReader implements IPropertyReader {

    @Override
    public Map<String, String> getSettings() {
        Properties properties = new Properties();
        try {
            properties.load(Files.newInputStream(Paths.get(System.getProperty("user.dir") + "/src/main/resources/user.properties")));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        Map<String, String> settings = new HashMap<>();

        for(String key: properties.stringPropertyNames()) {
            settings.put(key, properties.getProperty(key));
        }

        return settings;
    }
}
