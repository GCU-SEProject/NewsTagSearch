package com.example.demo.service;

import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class TagService {

    public List<String> getAvailableTags() {
        return Arrays.asList("정치", "경제", "스포츠", "IT", "연예");
    }
}
