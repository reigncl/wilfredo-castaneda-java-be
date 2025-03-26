package com.articleindexing.article_indexer_scheduler.service;

import com.articleindexing.article_indexer_scheduler.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Service interface for managing articles.
 * <p>
 * Provides methods for fetching articles, retrieving stored articles,
 * and performing operations such as pagination, filtering, and deletion.
 */
public interface ArticleService {
    /**
     * Fetches articles from an external API
     * and saves them to the database.
     * It filters out duplicate articles based on
     * their object ID.
     */
    void fetchAndSaveArticles();

    /**
     * Retrieves all articles stored in the database.
     * @return a list of Article objects containing
     * the details of all stored articles.
     */
    List<Article> getAllArticles();

    /**
     * Retrieves articles with pagination, filtering, and search options.
     *
     * @param author the author's name to filter articles (optional).
     * @param title the title to filter articles (optional).
     * @param month the month to search articles based on 'createdAt'
     *              field (optional).
     * @param pageable the pagination information.
     * @return a paginated list of articles matching the filters.
     */
    Page<Article> getArticles(String author, String title,
                              String month, Pageable pageable);

    /**
     * Marks an article as deleted to avoid it reappearing
     * in future results.
     * <p>
     * This method ensures soft deletion of the article
     * and prevents its retrieval
     * in subsequent queries.
     *
     * @param id the ID of the article to delete
     * @return {@code true} if the article was successfully marked
     * as deleted, {@code false} otherwise
     */
    boolean deleteArticle(Long id);
}

