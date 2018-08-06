package com.example.projectgatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;

import com.example.projectgatewayserver.filters.AddResponseHeaderFilter;
import com.example.projectgatewayserver.filters.ModifyResponseBodyFilter;
import com.example.projectgatewayserver.filters.ModifyResponseDataStreamFilter;
import com.example.projectgatewayserver.filters.PrefixRequestEntityFilter;
import com.example.projectgatewayserver.filters.QueryParamPortPreFilter;
import com.example.projectgatewayserver.filters.QueryParamServiceIdPreFilter;
import com.example.projectgatewayserver.filters.UppercaseRequestEntityFilter;

@EnableZuulProxy
@EnableEurekaClient
@SpringBootApplication
public class ProjectGatewayServerApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectGatewayServerApplication.class, args);
	}

	@Bean
	public AddResponseHeaderFilter addResponseHeaderFilter() {
		return new AddResponseHeaderFilter();
	}

	@Bean
	public ModifyResponseBodyFilter modifyResponseHeaderFilter() {
		return new ModifyResponseBodyFilter();
	}

	@Bean
	public ModifyResponseDataStreamFilter modifyResponseDataStreamFilter() {
		return new ModifyResponseDataStreamFilter();
	}

	@Bean
	public PrefixRequestEntityFilter prefixRequestEntityFilter() {
		return new PrefixRequestEntityFilter();
	}

	@Bean
	public QueryParamPortPreFilter queryParamPortPreFilter() {
		return new QueryParamPortPreFilter();
	}

	@Bean
	public QueryParamServiceIdPreFilter queryParamServiceIdPreFilter() {
		return new QueryParamServiceIdPreFilter();
	}

	@Bean
	public UppercaseRequestEntityFilter modifyRequestEntityFilter() {
		return new UppercaseRequestEntityFilter();
	}
}
