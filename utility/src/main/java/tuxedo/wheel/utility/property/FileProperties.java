package tuxedo.wheel.utility.property;

import lombok.NonNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

public class FileProperties extends BasicProperties {
    public FileProperties(@NonNull String file) throws IOException {
        super(new HashMap<>());
        try (InputStream is = new FileInputStream(file)) {
            java.util.Properties properties = new java.util.Properties();
            properties.load(is);
            properties.forEach((key, value) -> {
                if (key != null && value != null) {
                    source.put(key.toString(), value.toString());
                }
            });
        }
    }
}
