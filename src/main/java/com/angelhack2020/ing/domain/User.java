package com.angelhack2020.ing.domain;

import lombok.Builder;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
@Builder
class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임한다. Mysql-Autoincrement
    private final Long id;
    private final String userId;

    private String rcmdProducts; //추천 상품 목록

}
