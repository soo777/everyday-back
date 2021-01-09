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
@Table(name = "boardList")
public class BoardList implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int index;

    private int boardKey;

    private int userKey;

    private String userId;
}
