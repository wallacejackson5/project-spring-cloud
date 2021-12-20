# Getting Started

This project was built with [Spring cloud](https://spring.io/projects/spring-cloud).

## Available Scripts

In the project directory, you can run:
- `make up`

## Additional information
- Sonar [http://localhost:7000/about](http://localhost:7000/about).
- Kibana [http://localhost:7005/app/home#/](http://localhost:7005/app/home#/).
- Spring Config Server [http://localhost:8000/project-config-server/configs/project-eureka-server.yml](http://localhost:8000/project-config-server/configs/project-eureka-server.yml).
  - name: user
  - password: bdec5578-6c9b-4ae8-9fd5-3e77ccd504ad
- Discovery Server [http://localhost:8001/project-eureka-server/](http://localhost:8001/project-eureka-server/).
  - name: user
  - password: bdec5578-6c9b-4ae8-9fd5-3e77ccd504ad
- Uaa Cloud Foundry [http://localhost:8002/uaa/login](http://localhost:8002/uaa/login).
  - name: user
  - password: a2f3a022-42d7-4a7c-a92f-5af3679d77d8
- Gateway Server [http://localhost:8005/login](http://localhost:8005/).
  - name: user
  - password: a2f3a022-42d7-4a7c-a92f-5af3679d77d8
- Project Tests 1 [http://localhost:8007/project-resources/actuator](http://localhost:8007/project-resources/actuator).
- Project Tests 2 [http://localhost:8008/project-tests/](http://localhost:8008/project-tests/)

## Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.2/maven-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-developing-web-applications)
* [Spring Boot DevTools](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#using-boot-devtools)
* [Spring Configuration Processor](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#configuration-metadata-annotation-processor)
* [Spring Security](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security)
* [OAuth2 Resource Server](https://docs.spring.io/spring-boot/docs/2.4.2/reference/htmlsingle/#boot-features-security-oauth2-server)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/docs/current/reference/html/#service-discovery-eureka-clients)

## Guides
The following guides illustrate how to use some features concretely:

* [Centralized Configuration](https://spring.io/guides/gs/centralized-configuration/)
* [Building a REST ful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
