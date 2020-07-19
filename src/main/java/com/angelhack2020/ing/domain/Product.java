package com.angelhack2020.ing.domain;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Product {
     @Id
     @GeneratedValue(strategy= GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임한다. Mysql-Autoincrement
     private  Long id;
     private  String name;
     private  String brand;
     private  String category1;
     private  String category2;
     private  String status; //제품의 상태(일반[nomal], 신제품[new] 등)
     private  Long price;
     private  String goal; //제품이 어떤 주제의 착한 상품인지 설명
     private  String detailUrl; //제품 상세설명사진 링크
     private  String picUrl; //제품사진 링크
}
