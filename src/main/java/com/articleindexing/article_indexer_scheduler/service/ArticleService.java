package com.articleindexing.article_indexer_scheduler.service;

import com.articleindexing.article_indexer_scheduler.model.Article;
import java.util.List;

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
     *
     * @return a list of Article objects containing
     * the details of all stored articles.
     */
    List<Article> getAllArticles();
}

