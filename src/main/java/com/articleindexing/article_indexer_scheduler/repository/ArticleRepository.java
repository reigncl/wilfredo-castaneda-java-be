package com.articleindexing.article_indexer_scheduler.repository;

import com.articleindexing.article_indexer_scheduler.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Finds an article by its title.
     *
     * @param title the title of the article to search for
     * @return an Optional containing the article if found, otherwise empty
     */
    Optional<Article> findByTitle(String title);
}
