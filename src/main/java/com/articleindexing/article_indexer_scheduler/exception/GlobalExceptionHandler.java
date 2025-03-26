package com.articleindexing.article_indexer_scheduler.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


/**
 * Global exception handler for managing exceptions
 * across the application.
 * <p>
 * This class provides centralized exception handling
 * and ensures that
 * appropriate HTTP responses are sent when an error occurs.
 */
@RestControllerAdvice
public final class GlobalExceptionHandler {

    /**
     * Handles cases where an article is not found.
     * <p>
     * This method captures {@link ArticleNotFoundException}
     * and returns an HTTP
     * status code of 404 (Not Found) along with the error message.
     *
     * @param ex the {@link ArticleNotFoundException}
     *           thrown when an article is not found
     * @return a {@link ResponseEntity} containing the
     * error message and HTTP status 404
     */
    @ExceptionHandler(ArticleNotFoundException.class)
    public ResponseEntity<String> handleArticleNotFoundException(
            final ArticleNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles cases where an invalid argument is provided.
     * <p>
     * This method captures {@link IllegalArgumentException}
     * and returns an HTTP
     * status code of 400 (Bad Request) along with the error message.
     *
     * @param ex the {@link IllegalArgumentException}
     *           thrown due to invalid arguments
     * @return a {@link ResponseEntity} containing the
     * error message and HTTP status 400
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String>
        handleIllegalArgumentException(final IllegalArgumentException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles generic exceptions that are not specifically
     * handled elsewhere.
     * <p>
     * This method captures all other {@link Exception}
     * instances and returns an
     * HTTP status code of 500 (Internal Server Error)
     * with a generic error message.
     *
     * @param ex the exception that was thrown
     * @return a {@link ResponseEntity} containing a generic error message
     *         and HTTP status 500
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(final Exception ex) {
        return new ResponseEntity<>(
                "An unexpected error occurred",
                        HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
