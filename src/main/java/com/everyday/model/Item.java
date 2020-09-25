package com.everyday.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Data
@ToString
@Getter
@Entity
@Table(name = "tb_item")
public class Item implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id", insertable = false, updatable = false)
    private int itemId;

    @Column(name = "board_id")
    private String boardId;

    @Column
    private String content;

    @Column
    private String star;

    @Column
    private String creator;

    @Column(name = "create_date")
    private String createDate;

    @Column(name = "update_date")
    private String updateDate;
}
