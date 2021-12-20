package cucumber.mocks;

import com.github.tomakehurst.wiremock.client.MappingBuilder;
import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class WireMockUtils {

  public static void createMockPostRequest(WireMockParameter params) {
    final MappingBuilder mappingBuilder = WireMock.post(params.getServiceEndpoint());
    if (StringUtils.isNotEmpty(params.getBodyPatternExpression())) {
      String[] bodyPatterns = params.getBodyPatternExpression().split("=");
      String expression = bodyPatterns[0];
      final String equalTo = bodyPatterns[1];
      mappingBuilder.withRequestBody(
          WireMock.matchingJsonPath(expression, WireMock.equalTo(equalTo)));
    }

    WireMock.stubFor(mappingBuilder.willReturn(getResponse(params)));
  }

  public static void createMockPutRequest(WireMockParameter params) {
    WireMock.stubFor(WireMock.put(params.getServiceEndpoint()).willReturn(getResponse(params)));
  }

  public static void createMockGetRequest(WireMockParameter params) {
    WireMock.stubFor(WireMock.get(params.getServiceEndpoint()).willReturn(getResponse(params)));
  }

  private static ResponseDefinitionBuilder getResponse(WireMockParameter params) {
    return WireMock.aResponse()
        .withStatus(params.getResponseStatus())
        .withHeader("content-type", MediaType.APPLICATION_JSON_VALUE)
        .withBody(params.getResponseJsonBody().toString());
  }
}
