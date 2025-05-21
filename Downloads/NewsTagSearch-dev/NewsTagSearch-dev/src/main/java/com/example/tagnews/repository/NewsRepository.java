package com.example.tagnews.repository;

import com.example.tagnews.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface NewsRepository extends JpaRepository<News, Long> {
    List<News> findByTag(String tag);
}
