package com.articleindexing.article_indexer_scheduler.repository;

import com.articleindexing.article_indexer_scheduler.model.Article;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    private Article article1;
    private Article article2;

    @BeforeEach
    void setUp() {
        // Configurar datos iniciales para las pruebas
        article1 = new Article();
        article1.setObjectID("123");
        article1.setTitle("Java Basics");
        article1.setAuthor("John Doe");
        article1.setCreatedAt(LocalDateTime.now());
        article1.setDeleted(false);

        article2 = new Article();
        article2.setObjectID("456");
        article2.setTitle("Advanced Spring");
        article2.setAuthor("Jane Smith");
        article2.setCreatedAt(LocalDateTime.now().minusDays(30));
        article2.setDeleted(false);

        articleRepository.save(article1);
        articleRepository.save(article2);
    }

    @Test
    void testExistsByObjectID() {
        // Verificar que el artículo existe por objectID
        boolean exists = articleRepository.existsByObjectID("123");
        assertTrue(exists);

        // Verificar que un objectID inexistente no existe
        boolean notExists = articleRepository.existsByObjectID("789");
        assertFalse(notExists);
    }

    @Test
    void testFindByAuthor() {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 5);

        // Ejecutar la consulta
        Page<Article> result = articleRepository.findByAuthor("John Doe", pageable);

        // Validaciones
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("John Doe", result.getContent().get(0).getAuthor());
    }

    @Test
    void testFindByTitle() {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 5);

        // Ejecutar la consulta
        Page<Article> result = articleRepository.findByTitle("Java Basics", pageable);

        // Validaciones
        assertNotNull(result);
        assertEquals(1, result.getTotalElements());
        assertEquals("Java Basics", result.getContent().get(0).getTitle());
    }

    @Test
    void testFindByMonth() {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 5);

        // Ejecutar la consulta
        Page<Article> result = articleRepository.findByMonth("January", pageable); // Simula la fecha basada en `createdAt`

        // Validaciones
        assertNotNull(result);
        assertEquals(0, result.getTotalElements()); // Cambia según el setup
    }

    @Test
    void testFindAllActive() {
        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 5);

        // Ejecutar la consulta
        Page<Article> result = articleRepository.findAllActive(pageable);

        // Validaciones
        assertNotNull(result);
        assertEquals(2, result.getTotalElements()); // Debe incluir los artículos activos
    }

    @Test
    void testSoftDeleteArticle() {
        // Marcar un artículo como eliminado
        article1.setDeleted(true);
        articleRepository.save(article1);

        // Configurar la paginación
        Pageable pageable = PageRequest.of(0, 5);

        // Verificar que solo los no eliminados aparecen
        Page<Article> result = articleRepository.findAllActive(pageable);

        // Validaciones
        assertEquals(1, result.getTotalElements()); // Solo un artículo debe estar activo
    }
}