package cucumber.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class PropertiesLoader {
  private final ResourceLoader resourceLoader;
  private final ObjectMapper objectMapper;

  private static final String PATH_TEST_RESOURCES_FOLDER = "src/test/resources";

  public PropertiesLoader(Class<?> referenceClass) {
    this(referenceClass, (new ObjectMapperBuilder()).json().build());
  }

  public PropertiesLoader(Class<?> referenceClass, ObjectMapper objectMapper) {
    this.resourceLoader = new ResourceLoader(referenceClass);
    this.objectMapper = objectMapper;
  }

  public String getValueAsString(String fileName, String propertyName) {
    Properties properties = load(fileName);
    return properties.getProperty(propertyName);
  }

  private Properties load(String fileName) {
    Path workingDir = Path.of("", PATH_TEST_RESOURCES_FOLDER);
    Path file = workingDir.resolve(fileName);
    try {
      String content = Files.readString(file);
      Properties p = new Properties();
      p.load(new StringReader(content));
      return p;
    } catch (IOException var9) {
      throw new IllegalArgumentException(var9);
    }
  }
}
