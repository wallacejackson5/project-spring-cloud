package cucumber.steps.definitions;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.hamcrest.Matchers.*;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.matching.RequestPatternBuilder;
import cucumber.RunCucumberTest;
import cucumber.constants.CucumberConstants;
import cucumber.mocks.WireMockHeader;
import cucumber.mocks.WireMockParameter;
import cucumber.mocks.WireMockUtils;
import cucumber.steps.definitions.model.stub.StubMapping;
import cucumber.steps.definitions.model.stub.StubMappingRequest;
import cucumber.steps.definitions.model.stub.StubMappingResponse;
import cucumber.steps.definitions.model.stub.StubMappingResponseHeader;
import cucumber.utils.JsonHelper;
import cucumber.utils.JsonLoader;
import cucumber.utils.PropertiesLoader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.http.HttpStatus;
import org.json.JSONObject;
import org.springframework.http.MediaType;

@Slf4j
public class CommonStepDefinitions extends AbstractDefinitions {

  public static final String JSON_EXTENSION = ".json";
  private static final String NULL = "null";
  private static final String URL_MATCHER = ".*";

  private final JsonLoader jsonLoader = new JsonLoader(CommonStepDefinitions.class);
  private final PropertiesLoader propertiesLoader =
      new PropertiesLoader(CommonStepDefinitions.class);

  @Given("I have a {string} authentication token")
  public void iHaveAnAuthenticationToken(final String typeToken) {
    final var authorization =
        typeToken.equalsIgnoreCase("valid")
            ? CucumberConstants.MOCKED_AUTHORIZATION
            : "invalidAuthorization";
    final var headers = testContext().getHeaders();
    headers.add(new Header(CucumberConstants.HEADER_REQUEST_AUTHORIZATION, authorization));
  }

  @Given("^the following integrated services will have the corresponding responses:$")
  public void theFollowingIntegratedServicesWillHaveTheCorrespondingGenericResponses(
      final List<WireMockParameter> params) {
    for (final var parameter : params) {
      final var serviceName = parameter.getServiceName().toLowerCase();
      final var subfolder = WIREMOCK_MAPPINGS_FOLDER.concat(serviceName);

      final var responseJson =
          jsonLoader.asJSONObjectOrJSONArray(subfolder, getMockFileName(parameter));

      parameter.setResponseJsonBody(responseJson);
      parameter.setServiceEndpoint(getServiceEndpoint(serviceName, parameter));

      if (ObjectUtils.isEmpty(parameter.getResponseStatus())) {
        parameter.setResponseStatus(HttpStatus.SC_OK);
      }

      createMockRequest(parameter);
    }
  }

  private String getMockFileName(WireMockParameter parameter) {
    final var serviceName = parameter.getServiceName().toLowerCase();
    var responseFileName =
        String.join(
            "-",
            serviceName,
            parameter.getAction().toLowerCase(),
            parameter.getReferenceResponseName().toLowerCase());
    return responseFileName.concat(JSON_EXTENSION);
  }

  private String getServiceEndpoint(final String serviceName, WireMockParameter parameter) {
    var serviceEndpoint =
        propertiesLoader.getValueAsString(SERVICES_ENDPOINTS_PROPERTIES, serviceName);
    if (StringUtils.isNotEmpty(parameter.getQueryParameters())) {
      serviceEndpoint = serviceEndpoint.concat("?").concat(parameter.getQueryParameters());
    }
    return serviceEndpoint;
  }

  private void createMockRequest(final WireMockParameter params) {
    logCurl("POST", "http://localhost:9090/__admin/mappings", null, getStubMapping(params));
    switch (params.getAction().toUpperCase()) {
      case "POST":
        WireMockUtils.createMockPostRequest(params);
        break;
      case "PUT":
        WireMockUtils.createMockPutRequest(params);
        break;
      default:
        WireMockUtils.createMockGetRequest(params);
    }
  }

  private String getStubMapping(final WireMockParameter params) {
    final var stubRequest = new StubMappingRequest();
    stubRequest.setUrl(params.getServiceEndpoint());
    stubRequest.setMethod(params.getAction());

    final var stubResponseHeader = new StubMappingResponseHeader();
    stubResponseHeader.setContentType(MediaType.APPLICATION_JSON_VALUE);

    final var stubResponse = new StubMappingResponse();
    stubResponse.setHeaders(stubResponseHeader);
    stubResponse.setStatus(params.getResponseStatus());
    stubResponse.setBody(params.getResponseJsonBody().toString());

    final var stub = new StubMapping();
    stub.setRequest(stubRequest);
    stub.setResponse(stubResponse);

    return JsonHelper.convertToString(stub);
  }

  @Given("I am on country {string}")
  public void iAmOnCountry(String country) {
    final var headers = testContext().getHeaders();
    headers.add(new Header(CucumberConstants.HEADER_REQUEST_COUNTRY, country));
  }

  @Given("^I have a request with the following generic data:$")
  public void iHaveACartRequestWithTheFollowingData(final List<Object> objectRequests) {
    final var request = objectRequests.get(0);
    final var payload = new JSONObject(new Gson().toJson(request));
    testContext().setPayload(payload.toString());
  }

  @When("I make a {string} request to {string}")
  public void iMakeARequestTo(final String action, final String endpoint) {
    final var json = testContext().getPayload();
    final var headers = mountHeaders();
    final var request = getRequestSpecification();

    Response response;
    switch (action.toUpperCase()) {
      case "POST":
        response = request.when().post(endpoint);
        break;
      case "PUT":
        response = request.when().put(endpoint);
        break;
      case "DELETE":
        response = request.when().delete(endpoint);
        break;
      default:
        response = request.when().get(endpoint);
    }
    testContext().setResponse(response);
    logCurl(action, String.join("/", "http://localhost:8080", endpoint), headers, json);
    response.prettyPrint();
  }

  private RequestSpecification getRequestSpecification() {
    final var json = testContext().getPayload();
    final var headers = mountHeaders();

    final var request =
        RestAssured.given().contentType(ContentType.JSON).headers(headers).body(json);
    setRequestHost(request);
    testContext().setRequest(request);
    return testContext().getRequest();
  }

  private void setRequestHost(final RequestSpecification request) {
    final var apiHost =
        RunCucumberTest.environment.getServiceHost(
            CucumberConstants.SERVICE_NAME, CucumberConstants.SERVICE_PORT);
    request.baseUri("http://" + apiHost + ":" + CucumberConstants.SERVICE_PORT);
  }

  @Then("^I should get a (\\d+) response code$")
  public void iShouldGetAResponseCode(final int statusCode) {
    final var json = testContext().getResponse().then().statusCode(statusCode);
    testContext().setValidatableResponse(json);
  }

  @Then("^I should see the following data in the response body$")
  public void iShouldSeeTheFollowingDataInTheResponseBody(
      final Map<String, String> responseFields) {
    final var json = testContext().getValidatableResponse();
    for (final Map.Entry<String, String> field : responseFields.entrySet()) {
      switch (field.getValue()) {
        case "true":
        case "false":
          json.body(field.getKey(), equalTo(BooleanUtils.toBooleanObject(field.getValue())));
          break;
        case "null":
          json.body(field.getKey(), nullValue());
          break;
        case "emptyList":
          json.body(field.getKey(), anyOf(nullValue(), empty()));
          break;
        default:
          try {
            final var num = NumberUtils.createNumber(field.getValue());
            json.body(field.getKey(), equalTo(num));
          } catch (final NumberFormatException ex) {
            json.body(field.getKey(), comparesEqualTo(field.getValue()));
          }
      }
    }
  }

  @Then("the following service should receive the respective headers:")
  public void theServiceShouldReceiveTheRespectiveHeaders(final List<WireMockHeader> parameters) {
    parameters.forEach(
        header ->
            verifyHeader(
                header.getServiceName(), header.getAction(), header.getKey(), header.getValue()));
  }

  private void verifyHeader(
      final String serviceName, final String action, final String key, final String value) {
    if (NULL.equals(value)) {
      verify(getRequestPatternBuilder(serviceName, action).withoutHeader(key));
    } else {
      verify(
          getRequestPatternBuilder(serviceName, action).withHeader(key, WireMock.equalTo(value)));
    }
  }

  private RequestPatternBuilder getRequestPatternBuilder(
      final String service, final String action) {
    final var serviceEndpoint =
        propertiesLoader.getValueAsString(SERVICES_ENDPOINTS_PROPERTIES, service.toLowerCase())
            + URL_MATCHER;

    final RequestPatternBuilder requestPatternBuilder;
    switch (action.toUpperCase()) {
      case "POST":
        requestPatternBuilder = postRequestedFor(urlMatching(serviceEndpoint));
        break;
      case "PUT":
        requestPatternBuilder = putRequestedFor(urlMatching(serviceEndpoint));
        break;
      default:
        requestPatternBuilder = getRequestedFor(urlMatching(serviceEndpoint));
    }
    return requestPatternBuilder;
  }

  private void logCurl(
      final String action, final String url, final Headers headers, final String body) {
    if (log.isDebugEnabled()) {
      String headersCurl = StringUtils.EMPTY;
      if (headers != null) {
        headersCurl =
            headers.asList().stream()
                .map(
                    header ->
                        "--header '"
                            .concat(header.getName())
                            .concat(": ")
                            .concat(header.getValue())
                            .concat("'"))
                .collect(Collectors.joining(StringUtils.SPACE));
      }
      log.debug(
          String.format(CucumberConstants.LOG_REQUEST_FORMAT, action, url, headersCurl, body));
    }
  }
}
