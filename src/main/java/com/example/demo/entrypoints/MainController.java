package com.example.demo.entrypoints;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.MainConfig;
import com.example.demo.domain.tavern.TavernService;
import com.example.demo.domain.ward.WardService;
import com.fasterxml.jackson.databind.node.TextNode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MainController {

    @Autowired MainConfig mainConfig;
    @Autowired WardService wardService;
    @Autowired TavernService tavernService;

    @GetMapping(value = "/")
    @ResponseStatus(value = HttpStatus.OK)
    public String index() {
        log.info("Ello.");
        return "Welcome to " + mainConfig.getName().get(0);
    }

    @GetMapping(value = "/ward", produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<String> getWards() {
        return wardService.getWardNames();
    }

    @PostMapping(value = "/ward", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity<Void> createWard(@RequestBody TextNode name) {
        log.info("Create ward " + name);
        wardService.createWard(name.asText());

        URI location = UriComponentsBuilder
        .fromUriString("/")
        .path("/ward")
        .build()
        .toUri();
        
        return ResponseEntity.created(location).build();
    }
}
