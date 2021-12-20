package com.example.demo.controller;

import com.example.demo.constants.AuthorityConstants;
import com.example.demo.entity.ResponseData;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestSpringMvcController {

  @GetMapping("/")
  @PreAuthorize(AuthorityConstants.RESOURCE_READ)
  public ResponseEntity<ResponseData> resource() {
    final ResponseData response = new ResponseData();
    response.setMsg("ok");
    return ResponseEntity.ok(response);

  }

}