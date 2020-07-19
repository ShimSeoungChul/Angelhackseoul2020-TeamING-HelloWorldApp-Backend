package com.angelhack2020.ing.controller;

import com.angelhack2020.ing.domain.Review;
import com.angelhack2020.ing.service.ProductService;
import com.angelhack2020.ing.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService productService;

    @GetMapping("/{id}")
    private String detail(@PathVariable("id") long id){
        try{

            return "FAIL";
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }

    @GetMapping("")
    private String List(@PathVariable("id") long id){
        try{

            return "FAIL";
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }

}
