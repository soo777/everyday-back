package com.everyday.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@ToString
@Getter
@Entity
@Table(name = "comment")
public class Comment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int commentKey;

    private int itemKey;

    private String content;

    private int star;

    private String creator;

    private LocalDateTime createDate = LocalDateTime.now();

    private LocalDateTime updateDate = LocalDateTime.now();;

    private boolean status = true;

}
