package com.example.tagnews.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String tag;

    public News() {}

    public News(String title, String content, String tag) {
        this.title = title;
        this.content = content;
        this.tag = tag;
    }

    // getter / setter 생략 가능 (롬복 사용시)
}
