package ru.e24u.article.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.e24u.article.entity.Article;
import ru.e24u.article.repository.ArticleRepo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class ArticleControllerTest {

    @MockBean
    private ArticleRepo articleRepo;

    @Autowired
    private ArticleController controller;

    @Test()
    public void whenCreateArticleThenTrue() {
        ArticleController articleController = new ArticleController(articleRepo);
        Article article = new Article();
        article.setTitle("2");
        article.setContent("2");
        articleController.addArticle(article);
        Mockito.verify(articleRepo, Mockito.times(1)).save(article);
    }

    @Test()
    public void addArticleIfNotFoundThenException() {
        Article article = new Article();
        article.setTitle("2");
        article.setContent("2");
        Mockito.when(articleRepo.save(article)).thenThrow(IllegalArgumentException.class);
    }

    @Test
    public void whenException() {
        Article article = new Article();
        article.setTitle("2");
        article.setContent("2");

        Mockito.when(articleRepo.save(article)).thenThrow(IllegalArgumentException.class);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> controller.addArticle(article));
        assertEquals("Проверьте правильность оформления статьи", exception.getMessage());
    }
}