package com.articleindexing.article_indexer_scheduler.service;

import com.articleindexing.article_indexer_scheduler.client.ApiResponse;
import com.articleindexing.article_indexer_scheduler.model.Article;
import com.articleindexing.article_indexer_scheduler.repository.ArticleRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Service
public final class ArticleService {

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
     * Constructs an instance of ArticleService
     * with the required dependencies.
     * @param template the RestTemplate used for making HTTP requests.
     * @param articleRepo the repository for managing article persistence.
     */
    public ArticleService(final RestTemplate template,
                          final ArticleRepository articleRepo) {
        this.restTemplate = new RestTemplate();
        this.articleRepository = articleRepo;
    }

    /**
     * Fetches articles from the external API and saves them to the database.
     * It filters out duplicate articles based on their objectID before saving.
     * The API used is
     * "https://hn.algolia.com/api/v1/search_by_date?query=java".
     */
    public void fetchAndSaveArticles() {
        String url =
                "https://hn.algolia.com/api/v1/search_by_date?query=java";
        ApiResponse response = restTemplate
                .getForObject(url, ApiResponse.class);
        if ((response != null) && (response.getHits() != null)) {
            System.out.println("Hits from API: " + response.getHits());
            List<Article> articles = response.getHits().stream()
                    .filter(hit -> !articleRepository
                            .existsByObjectID(
                                    hit.getObjectID()
                            ))
                    .map(hit -> Article.builder()
                            .objectID(hit.getObjectID())
                            .title(hit.getTitle())
                            .author(hit.getAuthor())
                            .url(hit.getUrl())
                            .createdAt(hit.getCreatedAt())
                            .build())
                    .collect(Collectors.toList());
            articleRepository.saveAll(articles);
            System.out.println("Hits: " + response.getHits()); //just for test
        }
    }

    /**
     * Retrieves all articles from the database.
     * This method interacts with the ArticleRepository
     * to fetch all stored articles.
     * @return a list of Article objects stored in the database.
     */
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}

