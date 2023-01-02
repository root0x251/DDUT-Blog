package com.bortn.ddutBlog.entity.news;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "link_documents")
@Getter
@Setter
public class LinkDocumentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    private String link;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "full_news_entity_id")
    private FullNewsEntity fullNewsEntity;

    public LinkDocumentsEntity() {
    }

    public LinkDocumentsEntity(String link, FullNewsEntity fullNewsEntity) {
        this.link = link;
        this.fullNewsEntity = fullNewsEntity;
    }
}
