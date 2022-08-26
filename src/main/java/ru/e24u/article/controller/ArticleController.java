package ru.e24u.article.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    public Article addArticle(@RequestBody Article article) {
        try {
            return articleRepo.save(article);
        } catch (Exception e) {
            throw new IllegalArgumentException("Проверьте правильность оформления статьи");
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

    @PutMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody Article article) {
        try {
            Article rsl = articleRepo.findById(article.getId()).get();
            rsl.setHeader(article.getHeader());
            rsl.setContent(article.getContent());
            articleRepo.save(rsl);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        try {
            Article article = new Article();
            article.setId(id);
            articleRepo.delete(article);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
