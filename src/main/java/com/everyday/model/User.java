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
@Table(name = "tb_user")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_key", insertable = false, updatable = false)
    private int userKey;

    @Column(name = "user_id")
    private String userId;

    private String password;

    private String name;

    @Column(name = "create_date")
    private String createDate;
}
