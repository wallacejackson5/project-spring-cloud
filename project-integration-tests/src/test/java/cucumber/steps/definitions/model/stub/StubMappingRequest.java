package cucumber.steps.definitions.model.stub;

import lombok.Data;

@Data
public class StubMappingRequest {
  private String method;
  private String url;
}
