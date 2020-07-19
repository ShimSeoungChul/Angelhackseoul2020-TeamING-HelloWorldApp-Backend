package com.angelhack2020.ing.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "event")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
 public class Event {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임한다. Mysql-Autoincrement
    private  int id;

    private  String userId;
    private  int productId;
    private  String event; //클릭 or 구매 등
    private  int weight; //가중치
    private  LocalDateTime time; //이벤트 실 시간
}
