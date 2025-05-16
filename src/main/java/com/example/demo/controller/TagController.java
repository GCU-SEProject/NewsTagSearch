package com.example.demo.controller;

import com.example.demo.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class TagController {

    @Autowired
    private TagService tagService;

    @GetMapping("/tags")
    public List<String> getTags() {
        return tagService.getAvailableTags();
    }
}
