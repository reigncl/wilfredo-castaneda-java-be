package com.articleindexing.article_indexer_scheduler.controller;

import com.articleindexing.article_indexer_scheduler.model.Article;
import com.articleindexing.article_indexer_scheduler.service.ArticleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

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

        this.articleService = service;
    }

    /**
     * Retrieves all articles available in the database.
     * It interacts with the ArticleService to fetch the articles.
     *
     * @return a list of Article objects retrieved from the database.
     */
    @GetMapping
    public List<Article> getAllArticles() {
        List<Article> articles = articleService.getAllArticles(); //test
        System.out.println("Retrieved articles: " + articles); //test
        return articleService.getAllArticles();
    }

    /**
     * Fetches articles from the external API and stores them in the database.
     * This method ensures no duplicate articles are added.
     */
    @PostMapping("/fetch")
    public void fetchAndSaveArticles() {
        List<Article> articles = articleService.getAllArticles(); //test
        System.out.println("Retrieved articles: " + articles); //test
        articleService.fetchAndSaveArticles();
    }
}
