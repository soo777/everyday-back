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
@Table(name = "file")
public class File implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(insertable = false, updatable = false)
    private int fileKey;

    private int itemKey;

    private String path;

}
