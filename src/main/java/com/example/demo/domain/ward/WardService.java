package com.example.demo.domain.ward;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardService {

    @Autowired WardRepository wardRepository;

    public List<String> getWardNames() {
        return wardRepository.findAll()
            .stream()
            .map(Ward::getName)
            .sorted()
            .collect(Collectors.toList());
    }

    public void createWard(String name) {
        wardRepository.save(Ward.builder().name(name).build());
    }
}
