package com.articleindexing.article_indexer_scheduler.repository;

import com.articleindexing.article_indexer_scheduler.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Checks if an article exists in the database by its objectID.
     *
     * @param objectID the unique identifier of the article.
     * @return true if the article exists, false otherwise.
     */
    boolean existsByObjectID(String objectID);
}

