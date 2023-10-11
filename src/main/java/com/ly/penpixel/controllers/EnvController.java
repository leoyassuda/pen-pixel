package com.ly.penpixel.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/envs")
public class EnvController {

  @GetMapping
  public ResponseEntity<List<String>> findAll() {
    List<String> environmentVariables = new ArrayList<>();

    Map<String, String> envVariables = System.getenv();

    for (Map.Entry<String, String> entry : envVariables.entrySet()) {
      environmentVariables.add(entry.getKey() + "=" + entry.getValue());
    }

    return ResponseEntity.ok(environmentVariables);
  }
}
