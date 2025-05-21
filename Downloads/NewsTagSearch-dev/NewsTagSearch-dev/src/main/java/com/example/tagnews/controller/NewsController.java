package com.example.tagnews.controller;

import com.example.tagnews.service.NewsSearchService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NewsController {

    private final NewsSearchService newsSearchService;

    public NewsController(NewsSearchService newsSearchService) {
        this.newsSearchService = newsSearchService;
    }

    // 🔍 네이버 뉴스 검색
    // 예: /api/naver/search?keyword=삼성&category=경제
    @GetMapping("/naver/search")
    public String searchNaverNews(
            @RequestParam String keyword,
            @RequestParam(required = false, defaultValue = "") String category) {
        return newsSearchService.searchNewsByCategory(keyword, category);
    }
}
