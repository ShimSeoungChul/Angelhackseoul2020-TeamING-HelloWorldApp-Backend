package com.angelhack2020.ing.controller;

import com.angelhack2020.ing.domain.Review;
import com.angelhack2020.ing.service.ProductService;
import com.angelhack2020.ing.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("")
    private String List(@RequestParam(value = "productId") Long productId){
        String result = "FAIL";
        try{
            List<Review> reviews = reviewService.getReviews(productId);

            if(reviews == null){
                result = "FAIL";
                return result;
            }

            JsonArray jsonArray = new JsonArray();
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reviews);

        }catch (Exception e){
            e.printStackTrace();
            result = "FAIL";
        }
        return result;
    }

    @PostMapping("")
    private String create(@RequestParam(value = "productId") Long productId,
                          @RequestParam(value = "userId") String userId,
                          @RequestParam(value = "subject") String subject,
                          @RequestParam(value = "content") String content,
                          @RequestParam(value = "star") int star){
        String result;
        try{
            Review review = Review.builder()
                    .productId(productId)
                    .userId(userId)
                    .subject(subject)
                    .content(content)
                    .star(star)
                    .build();

            Review saved = reviewService.saveReview(review);
            if(saved==null){
                result= "FAIL";
                return result;
            }

            List<Review> reviews = reviewService.getReviews(productId);

            if(reviews == null){
                result = "FAIL";
                return result;
            }

            JsonArray jsonArray = new JsonArray();
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(reviews);

            return result;
        }catch (Exception e){
            e.printStackTrace();
            return "FAIL";
        }
    }

}
