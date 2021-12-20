package cucumber.utils;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsonLoader {
  private final ResourceLoader resourceLoader;
  private final ObjectMapper objectMapper;

  public JsonLoader(final Class<?> referenceClass) {
    this(referenceClass, (new ObjectMapperBuilder()).json().build());
  }

  public JsonLoader(final Class<?> referenceClass, final ObjectMapper objectMapper) {
    resourceLoader = new ResourceLoader(referenceClass);
    this.objectMapper = objectMapper;
  }

  public Object asJSONObjectOrJSONArray(final String resourcePath, final String fileName) {
    try {
      final String json = resourceLoader.asString(resourcePath, fileName);
      final JsonFactory f = new JsonFactory();
      final JsonParser parser = f.createParser(json);
      final JsonToken token = parser.nextToken();
      if (token.equals(JsonToken.START_ARRAY)) {
        return new JSONArray(json);
      } else {
        return new JSONObject(json);
      }
    } catch (final JSONException | IOException var3) {
      var3.printStackTrace();
      return null;
    }
  }

  public String asJSONString(final String resourcePath, final String fileName) {
    return resourceLoader.asString(resourcePath, fileName);
  }

  public JSONObject asJSONObject(final String stringContent) {
    try {
      return new JSONObject(stringContent);
    } catch (final JSONException var3) {
      var3.printStackTrace();
      return null;
    }
  }

  public JSONObject asJSONObject(final String resourcePath, final String fileName) {
    try {
      return new JSONObject(resourceLoader.asString(resourcePath, fileName));
    } catch (final JSONException var3) {
      var3.printStackTrace();
      return null;
    }
  }

  public JSONArray asJSONArray(final String resourcePath, final String fileName) {
    try {
      return new JSONArray(resourceLoader.asString(resourcePath, fileName));
    } catch (final JSONException var3) {
      var3.printStackTrace();
      return null;
    }
  }

  public static void replaceValue(final Object json, final String key, final Object newValue)
      throws JSONException {

    if (!(json instanceof JSONArray || json instanceof JSONObject)) {
      return;
    }

    final var jsonObject =
        json instanceof JSONArray ? (JSONObject) ((JSONArray) json).get(0) : (JSONObject) json;

    final Iterator<?> keys = jsonObject.keys();
    while (keys.hasNext()) {
      final String k = (String) keys.next();
      if (k.equals(key)) {
        jsonObject.put(key, newValue);
      } else if (k.equals(key.split("\\.")[0])) {
        final var value = jsonObject.opt(k);
        if (value instanceof JSONObject) {
          replaceValue(value, key.split("\\.")[1], newValue);
        }
      }
    }
  }
}
