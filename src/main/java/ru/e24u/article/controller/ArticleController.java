package ru.e24u.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.e24u.article.entity.Article;
import ru.e24u.article.repository.ArticleRepo;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/article")
public class ArticleController {

    private final ArticleRepo articleRepo;

    @PostMapping
    public ResponseEntity<Article> addArticle(@RequestBody Article article) {
        return new ResponseEntity<>(
                this.articleRepo.save(article),
                HttpStatus.CREATED);
    }

    @GetMapping()
    public List<Article> getArticle() {
        return articleRepo.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Article> findById(@PathVariable long id) {
        var article = this.articleRepo.findById(id);
        return new ResponseEntity<>(
                article.orElse(new Article()),
                article.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }
}