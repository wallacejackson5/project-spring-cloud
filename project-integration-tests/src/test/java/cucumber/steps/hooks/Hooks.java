package cucumber.steps.hooks;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.context.ServiceAutomatedTestContext;
import io.cucumber.java.Before;
import io.cucumber.java.DefaultDataTableCellTransformer;
import io.cucumber.java.DefaultDataTableEntryTransformer;
import io.cucumber.java.DefaultParameterTransformer;
import java.lang.reflect.Type;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Hooks {

  private final ObjectMapper objectMapper = new ObjectMapper();

  private ServiceAutomatedTestContext testContext() {
    return ServiceAutomatedTestContext.CONTEXT;
  }

  @DefaultParameterTransformer
  @DefaultDataTableEntryTransformer
  @DefaultDataTableCellTransformer
  public Object transformer(final Object fromValue, final Type toValueType) {
    return objectMapper.convertValue(fromValue, objectMapper.constructType(toValueType));
  }

  @Before
  public void reset() {
    log.info("=== Resetting Test Context ===");
    WireMock.reset();
    testContext().reset();
  }
}
