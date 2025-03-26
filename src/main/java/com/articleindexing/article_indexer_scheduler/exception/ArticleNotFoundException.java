package com.articleindexing.article_indexer_scheduler.exception;

public class ArticleNotFoundException extends RuntimeException {

    // Constructor with a custom message
    public ArticleNotFoundException(String message) {
        super(message);
    }

    // Constructor with a custom message and the cause
    public ArticleNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}

