package com.example.tagnews.controller;

import com.example.tagnews.entity.News;
import com.example.tagnews.repository.NewsRepository;
import com.example.tagnews.service.NewsSearchService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController {

    private final NewsRepository newsRepository;
    private final NewsSearchService newsSearchService;

    public NewsController(NewsRepository newsRepository, NewsSearchService newsSearchService) {
        this.newsRepository = newsRepository;
        this.newsSearchService = newsSearchService;
    }

    // DB에 저장된 모든 뉴스 가져오기
    @GetMapping
    public List<News> getAllNews() {
        return newsRepository.findAll();
    }

    // DB 내 태그로 뉴스 검색 (예: /news/search?tag=경제)
    @GetMapping("/search")
    public List<News> searchByTag(@RequestParam String tag) {
        return newsRepository.findByTag(tag);
    }

    // DB에 뉴스 저장
    @PostMapping
    public News createNews(@RequestBody News news) {
        return newsRepository.save(news);
    }

    // 네이버 뉴스 API에서 키워드 또는 카테고리로 뉴스 검색
    // 예: /news/search/naver?keyword=삼성 또는 /news/search/naver?category=정치
    @GetMapping("/search/naver")
    public String searchNaverNews(
            @RequestParam(required = false, defaultValue = "") String keyword,
            @RequestParam(required = false, defaultValue = "") String category) {

        if (keyword.isBlank() && category.isBlank()) {
            throw new IllegalArgumentException("keyword 또는 category 중 하나는 반드시 입력해야 합니다.");
        }

        return newsSearchService.searchNewsByCategory(keyword, category);
    }
}
