package com.articleindexing.article_indexer_scheduler.model;


import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity(name = "articles")
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Article {
    /**
     * Unique identifier for the article object.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Unique identifier for the article object.
     */
    @Column(unique = true)
    private String objectID;

    /**
     * Represents the title of the article.
     * This title serves as the main headline for the article.
     */
    private String title;

    /**
     * The author of the article.
     */
    private String author;

    /**
     * The url of the article.
     */
    private String url;

    /**
     * The date of the article.
     */
    private LocalDateTime createdAt;

    /**
     * Indicates if the article is deleted (soft delete).
     */
    @Column(nullable = false)
    private boolean isDeleted = false;

    /**
     * Marks the article as deleted.
     */
    public void markAsDeleted() {
        this.isDeleted = true;
    }
}
