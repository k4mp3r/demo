package com.example.demo.domain.tavern;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.ward.Ward;

@Service
public class TavernService {

    @Autowired TavernRepository tavernRepository;

    public List<String> getTavernNames() {
        return tavernRepository.findAll()
            .stream()
            .map(Tavern::getName)
            .sorted()
            .collect(Collectors.toList());
    }

    public void createWard(String name, int rooms, Ward ward) {
        tavernRepository.save(Tavern.builder().name(name).rooms(rooms).ward(ward).build());
    }
}
