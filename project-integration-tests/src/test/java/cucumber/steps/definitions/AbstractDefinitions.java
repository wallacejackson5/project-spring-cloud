package cucumber.steps.definitions;

import cucumber.constants.CucumberConstants;
import cucumber.context.ServiceAutomatedTestContext;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import java.util.List;

public abstract class AbstractDefinitions {
  protected static final String TRACE_ID = "qa-trace";
  protected static final String WIREMOCK_MAPPINGS_FOLDER = "/mocks/";
  protected static final String SERVICES_ENDPOINTS_PROPERTIES = "endpoints.properties";

  protected final ServiceAutomatedTestContext testContext() {
    return ServiceAutomatedTestContext.CONTEXT;
  }

  protected Headers mountHeaders() {
    final List<Header> listHeaders = testContext().getHeaders();
    listHeaders.add(new Header(CucumberConstants.HEADER_REQUEST_TRACE_ID, TRACE_ID));
    return new Headers(listHeaders);
  }
}
