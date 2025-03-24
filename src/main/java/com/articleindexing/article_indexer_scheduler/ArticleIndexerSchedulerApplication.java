/**
 * This package contains the ArticleIndexerScheduler Application.
 */
package com.articleindexing.article_indexer_scheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ArticleIndexerSchedulerApplication {
    /**
     *  Main application method.
     *  @param args
     * */
    public static void main(final String[] args) {
        SpringApplication.run(
            ArticleIndexerSchedulerApplication.class, args
        );
    }

    private void run(final String[] args) {
        SpringApplication.run(ArticleIndexerSchedulerApplication.class, args);
    }
}
