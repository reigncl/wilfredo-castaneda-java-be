package com.articleindexing.article_indexer_scheduler.scheduler;

import com.articleindexing.article_indexer_scheduler.service.ArticleService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public final class ArticleScheduler {

    /**
     * Service responsible for handling the logic of articles,
     * including fetching them from external APIs and saving them
     * to the database.
     */
    private final ArticleService articleService;

    /**
     * Scheduler responsible for periodically fetching and storing articles.
     *
     * @param service the service used to fetch and save articles
     */
    @SuppressWarnings("EI_EXPOSE_REP2")
    public ArticleScheduler(final ArticleService service) {
        if (service == null) {
            throw new IllegalArgumentException("Service cannot be null");
        }
        this.articleService = service;
    }

    /**
     * Scheduled task that fetches articles from an external API
     * and saves them to the database at fixed intervals.
     * <p>
     * The interval for execution is dynamically configured using the
     * {@code scheduler.fixed-rate} property in the application's
     * configuration file.
     * </p>
     *
     * @see ArticleService#fetchAndSaveArticles()
     */
    @Scheduled(fixedRateString = "#{schedulerProperties.fixedRate}")
    public void fetchAndSaveArticlesTask() {
        articleService.fetchAndSaveArticles();
    }
}
