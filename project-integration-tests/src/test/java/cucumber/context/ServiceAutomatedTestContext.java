package cucumber.context;

import static java.lang.ThreadLocal.withInitial;

import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ServiceAutomatedTestContext {
  CONTEXT;

  private static final String HEADERS = "HEADERS";
  private static final String PAYLOAD = "PAYLOAD";
  private static final String REQUEST = "REQUEST";
  private static final String RESPONSE = "RESPONSE";
  private static final String JSON = "VALID_RESPONSE";
  private final ThreadLocal<Map<String, Object>> testContexts = withInitial(HashMap::new);

  @SuppressWarnings("unchecked")
  public <T> T get(String name) {
    return (T) testContexts.get().get(name);
  }

  public <T> void set(String name, T object) {
    testContexts.get().put(name, object);
  }

  public RequestSpecification getRequest() {
    return get(REQUEST);
  }

  public void setRequest(RequestSpecification request) {
    set(REQUEST, request);
  }

  public Response getResponse() {
    return get(RESPONSE);
  }

  public void setResponse(Response response) {
    set(RESPONSE, response);
  }

  public ValidatableResponse getValidatableResponse() {
    return get(JSON);
  }

  public void setValidatableResponse(ValidatableResponse json) {
    set(JSON, json);
  }

  public String getPayload() {
    return get(PAYLOAD);
  }

  public <T> void setPayload(T object) {
    set(PAYLOAD, object);
  }

  public void reset() {
    testContexts.get().clear();
    setHeader(new ArrayList<>());
  }

  public List<Header> getHeaders() {
    return get(HEADERS);
  }

  public <T> void setHeader(T object) {
    set(HEADERS, object);
  }
}
