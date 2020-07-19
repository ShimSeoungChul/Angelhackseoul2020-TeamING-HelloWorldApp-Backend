package com.angelhack2020.ing.domain;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity(name = "surveyResult")
@Builder
public class SurveyResult {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) //기본 키 생성을 데이터베이스에 위임한다. Mysql-Autoincrement
    private final Long id;

    private final Long questionNum; // 설문조사 질문 번호
    private final Long resultNum; //답변 번호
    private final String userId;
    private final LocalDateTime time; //답변 시간
}
