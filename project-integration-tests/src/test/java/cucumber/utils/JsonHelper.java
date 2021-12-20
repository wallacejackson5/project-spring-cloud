package cucumber.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonHelper {

  public static String convertToString(final Object classToBeConverted) {
    String jsonMessage = null;
    try {
      jsonMessage = new ObjectMapper().writer().writeValueAsString(classToBeConverted);
    } catch (JsonProcessingException e) {
      log.warn("Error on parse message {}", e.getLocalizedMessage());
    }

    return jsonMessage;
  }

  public static <T> T convertToObject(final String jsonString, final Class<T> theClass) {
    T result = null;
    try {
      result = new ObjectMapper().readValue(jsonString, theClass);
    } catch (IllegalArgumentException | JsonProcessingException e) {
      log.warn("Parse do not working with {}", e.getLocalizedMessage());
    }
    return result;
  }
}
