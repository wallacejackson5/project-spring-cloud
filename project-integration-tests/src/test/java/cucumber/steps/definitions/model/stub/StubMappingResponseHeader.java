package cucumber.steps.definitions.model.stub;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.testcontainers.shaded.com.google.common.net.HttpHeaders;

@Data
public class StubMappingResponseHeader {
  @JsonProperty(HttpHeaders.CONTENT_TYPE)
  private String contentType;
}
