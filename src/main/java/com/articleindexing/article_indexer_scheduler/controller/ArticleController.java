package com.articleindexing.article_indexer_scheduler.controller;

import com.articleindexing.article_indexer_scheduler.model.Article;
import com.articleindexing.article_indexer_scheduler.service.ArticleService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
    public Page<Article> getArticles(
            @RequestParam(required = false) final String author,
            @RequestParam(required = false) final String title,
            @RequestParam(required = false) final String month,
            @RequestParam(defaultValue = "0") final int page,
            @RequestParam(defaultValue = "5") final int size) {

        Pageable pageable = PageRequest.of(page, size);
        return articleService.getArticles(author, title, month, pageable);
    }

    /**
     * Deletes an article by its ID.
     * This method interacts with the ArticleService
     * to mark the article as deleted.
     *
     * @param id the ID of the article to delete.
     */
    @DeleteMapping("/{id}")
    public void deleteArticle(@PathVariable final Long id) {
        articleService.deleteArticle(id);
    }

    /**
     * Fetches articles from the external API and
     * stores them in the database.
     * This method ensures no duplicate articles are added.
     */
    @PostMapping("/fetch")
    public void fetchAndSaveArticles() {
        articleService.fetchAndSaveArticles();
    }
}
