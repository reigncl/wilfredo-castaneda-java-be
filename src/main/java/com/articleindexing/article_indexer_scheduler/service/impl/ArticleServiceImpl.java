package com.articleindexing.article_indexer_scheduler.service.impl;

import com.articleindexing.article_indexer_scheduler.client.ApiResponse;
import com.articleindexing.article_indexer_scheduler.model.Article;
import com.articleindexing.article_indexer_scheduler.repository.ArticleRepository;
import com.articleindexing.article_indexer_scheduler.service.ArticleService;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ArticleServiceImpl implements ArticleService {

    /**
     * Utility to perform RESTful HTTP requests.
     */
    private final RestTemplate restTemplate;

    /**
     * Repository for managing article persistence operations.
     * Used to save and retrieve articles from the database.
     */
    private final ArticleRepository articleRepository;

    /**
     * Constructs an instance of ArticleServiceImpl
     * with the required dependencies.
     *
     * @param restTemplateBuilder the builder used to configure
     *                            and create a RestTemplate.
     * @param repository the repository for managing article
     *                          persistence operations.
     */
    public ArticleServiceImpl(final RestTemplateBuilder restTemplateBuilder,
                              final ArticleRepository repository) {
        if (restTemplateBuilder == null) {
            throw new IllegalArgumentException("RestTemplate cannot be null");
        }
        if (repository == null) {
            throw new IllegalArgumentException(
                    "ArticleRepository cannot be null"
            );
        }
        this.restTemplate = restTemplateBuilder.build();
        this.articleRepository = repository;
    }

    /**
     * Fetches articles from the external API and
     * saves them to the database.
     * Filters out duplicate articles based on their objectID
     * before saving.
     * The API endpoint used is:
     * "https://hn.algolia.com/api/v1/search_by_date?query=java".
     */
    @Override
    public void fetchAndSaveArticles() {
        String url =
                "https://hn.algolia.com/api/v1/search_by_date?query=java";
        ApiResponse response = restTemplate
                .getForObject(url, ApiResponse.class);

        if ((response != null) && (response.getHits() != null)) {
            System.out.println("Hits from API: " + response.getHits());
            List<Article> articles = response.getHits().stream()
                    .filter(hit -> !articleRepository
                            .existsByObjectID(hit.getObjectID()))
                    .map(hit -> Article.builder()
                            .objectID(hit.getObjectID())
                            .title(hit.getTitle())
                            .author(hit.getAuthor())
                            .url(hit.getUrl())
                            .createdAt(hit.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());
            articleRepository.saveAll(articles);
            System.out.println("Saved Articles: " + articles.size());
        }
    }

    /**
     * Retrieves all articles from the database.
     * This method interacts with the ArticleRepository to
     * fetch all stored articles.
     *
     * @return a list of Article objects stored in the database.
     */
    @Override
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}

