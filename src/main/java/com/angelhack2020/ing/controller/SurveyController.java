package com.angelhack2020.ing.controller;

import com.angelhack2020.ing.domain.SurveyResult;
import com.angelhack2020.ing.service.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/surveys")
class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping("/results")
    private List<SurveyResult> resultsList() {
        return null;
    }

    //설문조사 결과 입력
    @PostMapping("/results")
    private String resultsCreate(HttpServletRequest request, HttpServletResponse response) {
        String result = "SUCC";
        return result;
    }



}
