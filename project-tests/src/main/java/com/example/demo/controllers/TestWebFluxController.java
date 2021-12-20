package com.example.demo.controllers;

import com.example.demo.entity.ResponseData;
import com.example.demo.constants.AuthorityConstants;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class TestWebFluxController {

    @GetMapping("/")
    @PreAuthorize(AuthorityConstants.RESOURCE_READ)
    public Mono<ResponseEntity<ResponseData>> resource() {
        final ResponseData response = new ResponseData();
        response.setMsg("ok");
        return Mono.create(s -> s.success(ResponseEntity.ok(response)));
    }
}