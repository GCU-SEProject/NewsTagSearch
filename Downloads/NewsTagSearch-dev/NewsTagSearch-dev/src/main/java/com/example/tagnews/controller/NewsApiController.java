package com.example.tagnews.controller;

import com.example.tagnews.service.NewsSearchService;  // 반드시 import 추가

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NewsApiController {

    private final NewsSearchService newsSearchService;

    public NewsApiController(NewsSearchService newsSearchService) {
        this.newsSearchService = newsSearchService;
    }

    // 네이버 뉴스 검색: keyword 필수, category 선택 (정치, 경제, 스포츠, IT, 연예)
    // 예: /api/naver/search?keyword=삼성&category=경제
    @GetMapping("/naver/search")
    public String searchNaverNews(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "") String category) {
        return newsSearchService.searchNewsByCategory(keyword, category);
    }
}
