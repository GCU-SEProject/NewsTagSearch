package com.example.tagnews.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NewsSearchService {

    private final String clientId = "aXnzVsITq5ju_VQjpX4O";
    private final String clientSecret = "u50IHnLtKL";

    public String searchNewsByCategory(String keyword, String category) {
        if (keyword == null || keyword.isBlank()) {
            throw new IllegalArgumentException("keyword는 필수 입력 값입니다.");
        }

        String apiCategory = switch (category.toLowerCase()) {
            case "정치", "politics" -> "politics";
            case "경제", "economy" -> "economy";
            case "스포츠", "sports" -> "sports";
            case "it" -> "it";
            case "연예", "entertain" -> "entertain";
            default -> "";  // 전체 카테고리
        };

        String apiURL = "https://openapi.naver.com/v1/search/news.json?query=" + keyword;
        if (!apiCategory.isEmpty()) {
            apiURL += "&category=" + apiCategory;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.add("X-Naver-Client-Id", clientId);
        headers.add("X-Naver-Client-Secret", clientSecret);

        HttpEntity<String> entity = new HttpEntity<>(headers);
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(apiURL, HttpMethod.GET, entity, String.class);

            if (response.getStatusCode() == HttpStatus.OK) {
                return response.getBody();
            } else {
                return "API 호출 실패: " + response.getStatusCode();
            }
        } catch (Exception e) {
            return "API 호출 중 예외 발생: " + e.getMessage();
        }
    }
}