package com.angelhack2020.ing.service;

import com.angelhack2020.ing.dao.ReviewRepository;
import com.angelhack2020.ing.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {

    @Autowired
    ReviewRepository reviewRepository;

    public List<Review> getReviews(){
        return null;
    }

    public Review getReview(){
        return null;
    }

    public void saveReview(){

    }
}
