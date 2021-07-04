package com.example.springecommerce.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "images")
@Getter
@Setter
@NoArgsConstructor
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
}
