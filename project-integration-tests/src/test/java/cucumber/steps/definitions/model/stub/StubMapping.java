package cucumber.steps.definitions.model.stub;

import lombok.Data;

@Data
public class StubMapping {
  private StubMappingRequest request;
  private StubMappingResponse response;
}
