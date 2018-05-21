package com.study.routerfunction.repo.entity;

import lombok.Data;
import org.hibernate.validator.constraints.Range;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;

/**
 * create by Nemo
 * 2018/5/20  13:57
 */
@Data
@Document(collection = "user")
public class User {
    @Id
    private String id;

    @NotBlank
    private String name;

    @Range(min = 10)
    private int age;
}
