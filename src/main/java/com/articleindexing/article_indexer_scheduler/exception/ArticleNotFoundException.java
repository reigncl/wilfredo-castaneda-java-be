package com.articleindexing.article_indexer_scheduler.exception;

/**
 * Custom exception thrown when an article is not found.
 */
public class ArticleNotFoundException extends RuntimeException {

    /**
     * Constructor with a custom message.
     *
     * @param message the detail message explaining the exception.
     */
    public ArticleNotFoundException(final String message) {
        super(message);
    }

    /**
     * Constructs a new ArticleNotFoundException
     * with the specified detail message and cause.
     *
     * @param message the detail message explaining the exception
     * @param cause the cause of the exception (can be null)
     */
    public ArticleNotFoundException(final String message,
                                    final Throwable cause) {
        super(message, cause);
    }
}

