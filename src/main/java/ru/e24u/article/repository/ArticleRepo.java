package ru.e24u.article.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.e24u.article.entity.Article;

public interface ArticleRepo extends JpaRepository<Article, Long> {
}
