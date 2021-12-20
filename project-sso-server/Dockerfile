# Start with a base image containing Java runtime
FROM tomcat:9.0.21-jdk8-adoptopenjdk-hotspot as dev

#Config UAA
COPY ./resources/cloudfoundry-identity-uaa-4.30.0.war $CATALINA_HOME/webapps/uaa.war
ENV CLOUD_FOUNDRY_CONFIG_PATH=$CATALINA_HOME/temp/uaa
RUN mkdir -p $CLOUD_FOUNDRY_CONFIG_PATH
COPY resources/uaa.yml $CLOUD_FOUNDRY_CONFIG_PATH

# Configure Tomcat
COPY resources/tomcat-users.xml $CATALINA_HOME/conf
COPY resources/server.xml $CATALINA_HOME/conf
COPY resources/manager.xml $CATALINA_HOME/webapps/manager/META-INF/context.xml
COPY resources/host-manager.xml $CATALINA_HOME/webapps/host-manager/META-INF/context.xml

# Run the jar file
COPY ./docker-entrypoint.sh /
RUN chmod 100 /docker-entrypoint.sh

ENTRYPOINT ["/docker-entrypoint.sh"]