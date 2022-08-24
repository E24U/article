package ru.e24u.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.e24u.article.entity.Article;
import ru.e24u.article.repository.ArticleRepo;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepo articleRepo;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Article addArticle(@RequestBody Article article) {
        try {
            return articleRepo.save(article);
        } catch (Exception e) {
            throw new IllegalArgumentException("Проверьте правильность оформления статьи, "
                    + "возможно заголовок превышает 55 символов");
        }
    }

    @GetMapping()
    public List<Article> getArticle() {
        return articleRepo.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Article findById(@PathVariable long id) {
        try {
            return articleRepo.findById(id).get();
        } catch (Exception e) {
            throw new IllegalArgumentException("Данная статья не найдена.");
        }
    }
}