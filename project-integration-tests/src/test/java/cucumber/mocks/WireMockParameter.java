package cucumber.mocks;

import lombok.Data;

@Data
public class WireMockParameter {
  private String serviceName;
  private String action;
  private Integer responseStatus;
  private String referenceResponseName;
  private String queryParameters;
  private String bodyPatternExpression;
  private String bodyPatternEqualsTo;
  private String serviceEndpoint;
  private Object responseJsonBody;
}
