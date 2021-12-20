package cucumber.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ResourceLoader {
  public static final String SRC_TEST_RESOURCES = "src/test/resources";
  private final Class<?> referenceClass;

  public ResourceLoader(Class<?> referenceClass) {
    this.referenceClass = referenceClass;
  }

  public String asString(String resourcePath, String fileName) {

    Path workingDir = Path.of(SRC_TEST_RESOURCES, resourcePath);
    Path file = workingDir.resolve(fileName);
    try {
      return Files.readString(file);
    } catch (IOException var9) {
      throw new IllegalArgumentException(var9);
    }
  }
}
