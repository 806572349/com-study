package com.study.client.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;

/**
 * create by Nemo
 * 2018/5/20  13:57
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private String id;

    private String name;

    private int age;
}
