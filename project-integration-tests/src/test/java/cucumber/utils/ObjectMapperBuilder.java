package cucumber.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

public class ObjectMapperBuilder {
  private Jackson2ObjectMapperBuilder builder;
  private boolean findAndRegisterModules;
  private boolean writeDatesAsTimestamps;
  private JsonInclude.Include serializationInclusion;

  public ObjectMapperBuilder() {
    this.findAndRegisterModules();
    this.writeDatesAsTimestamps(false);
    this.serializeNotNull();
  }

  public ObjectMapperBuilder json() {
    this.builder = Jackson2ObjectMapperBuilder.json();
    return this;
  }

  public ObjectMapperBuilder writeDatesAsTimestamps(boolean writeDatesAsTimestamps) {
    this.writeDatesAsTimestamps = writeDatesAsTimestamps;
    return this;
  }

  public ObjectMapperBuilder writeDatesAsTimestamps() {
    return this.writeDatesAsTimestamps(true);
  }

  public ObjectMapperBuilder findAndRegisterModules(boolean findAndRegisterModules) {
    this.findAndRegisterModules = findAndRegisterModules;
    return this;
  }

  public ObjectMapperBuilder serializationInclusion(JsonInclude.Include serializationInclusion) {
    this.serializationInclusion = serializationInclusion;
    return this;
  }

  public ObjectMapperBuilder serializeNotNull() {
    this.serializationInclusion(JsonInclude.Include.NON_NULL);
    return this;
  }

  public ObjectMapperBuilder findAndRegisterModules() {
    return this.findAndRegisterModules(true);
  }

  public ObjectMapper build() {
    this.preBuild(this.builder);
    ObjectMapper objectMapper = this.builder.build();
    if (this.findAndRegisterModules) {
      objectMapper = objectMapper.findAndRegisterModules();
    }

    objectMapper =
        objectMapper.configure(
            SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, this.writeDatesAsTimestamps);
    objectMapper.setSerializationInclusion(this.serializationInclusion);
    return objectMapper;
  }

  protected void preBuild(Jackson2ObjectMapperBuilder builder) {}
}
