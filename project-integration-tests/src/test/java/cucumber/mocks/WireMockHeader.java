package cucumber.mocks;

import lombok.Data;

@Data
public class WireMockHeader {
  private String serviceName;
  private String action;
  private String key;
  private String value;
}
