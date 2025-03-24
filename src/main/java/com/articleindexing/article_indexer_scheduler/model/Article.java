package com.articleindexing.article_indexer_scheduler.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import java.time.LocalDateTime;

@Entity
@Table(name = "articles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    /**
     * Represents the unique identifier for the article.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Represents the title of the article.
     */
    private String title;

    /**
     * Represents the url of the article.
     */
    private String url;

    /**
     * Represents the author of the article.
     */
    private String author;

    /**
     * Indicates the publication date of the article.
     */
    @Column(name = "published_at")
    private LocalDateTime createdAt;
}

