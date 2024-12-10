package com.ivanhrabivchuk.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 2000)
    private String biography;

    @Column(name = "image_url")
    private String imageUrl;

    @OneToMany(mappedBy = "author")
    private List<Comic> comics;
}