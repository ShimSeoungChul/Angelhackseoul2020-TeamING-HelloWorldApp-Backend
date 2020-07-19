package com.angelhack2020.ing.dao;

import com.angelhack2020.ing.domain.Product;
import com.angelhack2020.ing.domain.Review;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ReviewRepository extends CrudRepository<Review, Long> {
    ArrayList<Review> findAllByProductId(Long productId);
}
