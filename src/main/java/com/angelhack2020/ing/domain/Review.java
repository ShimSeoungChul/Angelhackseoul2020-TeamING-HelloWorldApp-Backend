package com.angelhack2020.ing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "review")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Review {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임한다. Mysql-Autoincrement
    private  Long id;
    private  Long productId;
    private  String userId;
    private  String subject;
    private  String content;
    private  int star;
}
