package com.everyday.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ToString
@Getter
@Entity
@Table(name = "item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int itemKey;

    private int boardKey;

    private String content;

    private int star;

    private String creator;

    private LocalDateTime createDate = LocalDateTime.now();;

    private LocalDateTime updateDate = LocalDateTime.now();;

    private boolean status = true;

    @OneToMany
    @JoinColumn(name = "itemKey", insertable =  false, updatable = false)
    private List<Comment> comment;

    @OneToMany
    @JoinColumn(name = "itemKey", insertable =  false, updatable = false)
    private List<Files> files;
}
