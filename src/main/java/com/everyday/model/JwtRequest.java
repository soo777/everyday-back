package com.everyday.model;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Getter
public class JwtRequest implements Serializable {

    private String username;
    private String password;

}
