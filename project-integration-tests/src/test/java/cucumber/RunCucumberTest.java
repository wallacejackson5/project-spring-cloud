package cucumber;

import com.github.tomakehurst.wiremock.client.WireMock;
import cucumber.constants.CucumberConstants;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import java.io.File;
import java.util.TimeZone;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.runner.RunWith;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/resources/features"},
    glue = {
      "classpath:cucumber.steps.definitions",
      "classpath:cucumber.steps.hooks",
      "classpath:cucumber.mocks"
    },
    plugin = {"pretty", "json:target/cucumber.json"},
    snippets = CucumberOptions.SnippetType.CAMELCASE,
    tags = "not @ignore")
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RunCucumberTest {

  @ClassRule
  public static final DockerComposeContainer<?> environment =
      new DockerComposeContainer<>(new File("../project-integration-tests/docker-compose.yml"))
          .waitingFor(CucumberConstants.WIREMOCK_SERVICE_NAME, Wait.forHealthcheck())
          .withExposedService("wiremock_1", CucumberConstants.WIREMOCK_SERVICE_PORT)
          .withLocalCompose(true);

  @BeforeClass
  public static void buildUp() {
    log.info("=== Starting Automated Tests ===");
    TimeZone.setDefault(TimeZone.getTimeZone("UTC"));

    final String wiremockHost =
        environment.getServiceHost(
            CucumberConstants.WIREMOCK_SERVICE_NAME, CucumberConstants.WIREMOCK_SERVICE_PORT);
    WireMock.configureFor(wiremockHost, CucumberConstants.WIREMOCK_SERVICE_PORT);
  }

  @AfterClass
  public static void tearDown() {
    log.info("=== Automated Tests Finished ===");
  }
}
