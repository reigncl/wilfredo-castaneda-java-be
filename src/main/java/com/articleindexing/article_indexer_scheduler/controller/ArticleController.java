package com.articleindexing.article_indexer_scheduler.controller;

import com.articleindexing.article_indexer_scheduler.exception.ArticleNotFoundException;
import com.articleindexing.article_indexer_scheduler.model.Article;
import com.articleindexing.article_indexer_scheduler.service.ArticleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@SuppressWarnings("EI_EXPOSE_REP2")
@RestController
@RequestMapping("/api/articles")
public final class ArticleController {

    private static final Logger logger = LoggerFactory.getLogger(ArticleController.class);

    /**
     * Service layer for managing articles.
     */
    private final ArticleService articleService;

    /**
     * Constructs an instance of
     * ArticleController
     * with the required dependencies.
     *
     * @param service the service layer used to manage
     * article-related operations.
     */
    public ArticleController(final ArticleService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        //this.articleService = service;
        this.articleService = service;
    }

    /**
     * Retrieves articles dynamically based on
     * filters (author, title, or month).
     *
     * @param author the name of the author to filter articles by.
     * @param title the title of the articles to search for.
     * @param month the name of the month to filter articles by.
     * @param page the page number for pagination (default = 0).
     * @param size the page size for pagination (default = 5).
     * @return a paginated list of articles matching the filters.
     */
    @GetMapping("/search")
    public ResponseEntity<Page<Article>> getArticles(
            @RequestParam(required = false) final String author,
            @RequestParam(required = false) final String title,
            @RequestParam(required = false) final String month,
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size) {

        logger.info("Received request to search articles with " +
                "author: {}, title: {}, month: {}, page: {}, size: {}",
                    author, title, month, page, size);

        Pageable pageable = PageRequest
                .of(Math.max(page, 0), Math.max(size, 1));
        Page<Article> articles = articleService.getArticles(
                author, title, month, pageable
        );

        logger.info("Found {} articles matching the criteria.",
                articles.getTotalElements());
        return new ResponseEntity<>(articles, HttpStatus.OK);
    }

    /**
     * Deletes an article by its ID.
     * This method interacts with the ArticleService
     * to mark the article as deleted.
     *
     * @param id the ID of the article to delete.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) {
        logger.info("Received request to delete article with ID: {}", id);
        if (!articleService.deleteArticle(id)) {
            throw new ArticleNotFoundException("Article with ID "
                    + id + " not found");
        }
        logger.info("Article with ID {} deleted successfully.", id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Fetches articles from the external API and
     * stores them in the database.
     * This method ensures no duplicate articles are added.
     */
    @PostMapping("/fetch")
    public ResponseEntity<Void> fetchAndSaveArticles() {
        logger.info("Fetching articles from " +
                "Algolia API and saving them.");
        articleService.fetchAndSaveArticles();
        logger.info("Articles fetched and saved successfully.");
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
