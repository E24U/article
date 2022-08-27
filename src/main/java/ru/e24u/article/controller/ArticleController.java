package ru.e24u.article.controller;

import lombok.RequiredArgsConstructor;
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

    @GetMapping("/")
    public Article findById(@RequestParam() long id) {
        try {
            return articleRepo.findById(id).get();
        } catch (Exception e) {
            throw new IllegalArgumentException("Данная статья не найдена.");
        }
    }

    @PutMapping("/")
    public void update(@RequestParam() long id, @RequestBody Article article) {
        try {
            Article rsl = articleRepo.findById(id).get();
            rsl.setHeader(article.getHeader());
            rsl.setContent(article.getContent());
            articleRepo.save(rsl);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    @DeleteMapping("/")
    public void delete(@RequestParam() long id) {
        try {
            Article article = new Article();
            article.setId(id);
            articleRepo.delete(article);
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}