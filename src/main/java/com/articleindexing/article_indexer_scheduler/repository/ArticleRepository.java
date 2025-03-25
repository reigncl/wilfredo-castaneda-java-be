package com.articleindexing.article_indexer_scheduler.repository;

import com.articleindexing.article_indexer_scheduler.model.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    /**
     * Checks if an article exists in the database by its objectID.
     *
     * @param objectID the unique identifier of the article.
     * @return true if the article exists, false otherwise.
     */
    boolean existsByObjectID(String objectID);

    /**
     * Finds articles by the author's name.
     *
     * This method retrieves a list of articles filtered by the author's name.
     * Only articles that are not marked as deleted
     * (`isDeleted = false`) are included.
     *
     * @param author the name of the author to filter articles by.
     * @param pageable the pagination information including
     *                 page number and size.
     * @return a paginated list of articles
     * written by the specified author.
     */
    @Query("SELECT a FROM articles a WHERE "
            + "a.isDeleted = false AND a.author LIKE %:author%")
    Page<Article> findByAuthor(String author, Pageable pageable);

    /**
     * Retrieves a paginated list of articles that match the given
     * title pattern.
     * Only articles that are not marked as deleted will be returned.
     *
     * @param title    the title or title fragment to search for
     *                 (case-sensitive, using SQL LIKE)
     * @param pageable the pagination and sorting information
     * @return a page containing articles that match the title filter
     */
    @Query("SELECT a FROM articles a WHERE "
            + "a.isDeleted = false AND a.title LIKE %:title%")
    Page<Article> findByTitle(String title, Pageable pageable);

    /**
     * Finds articles by the name of the month using the 'createdAt' field.
     *
     * @param month the name of the month (e.g., January, February, etc.).
     * @param pageable the pagination information.
     * @return a paginated list of articles created in the specified month.
     */
    @Query("SELECT a FROM articles a WHERE "
            + "a.isDeleted = false AND "
            + "TO_CHAR(a.createdAt, 'Month') ILIKE %:month%")
    Page<Article> findByMonth(@Param("month") String month, Pageable pageable);

    /**
     * Retrieves a paginated list of all active (non-deleted) articles.
     *
     * @param pageable the pagination and sorting information
     * @return a page containing active articles
     */
    @Query("SELECT a FROM articles a WHERE a.isDeleted = false")
    Page<Article> findAllActive(Pageable pageable);
}

