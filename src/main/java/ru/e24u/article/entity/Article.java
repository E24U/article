package ru.e24u.article.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Table(name = "article")
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Article {
     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String header;
     private String content;

}
