package utils;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;

public class ConfigLoader {
    private static final DataConfig INSTANCE;

    static {
        Yaml yaml = new Yaml();
        try (InputStream in = ConfigLoader.class.getClassLoader()
                .getResourceAsStream("configuration.yaml")) {

            INSTANCE = yaml.loadAs(in, DataConfig.class);

        } catch (Exception e) {
            throw new RuntimeException("Failed to load YAML", e);
        }
    }

    public static DataConfig get() { return INSTANCE; }
}
