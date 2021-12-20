package cucumber.steps.definitions.model.stub;

import lombok.Data;

@Data
public class StubMappingResponse {
  private String body;
  private Integer status;
  private StubMappingResponseHeader headers;
}
