package com.angelhack2020.ing.dao;

import com.angelhack2020.ing.domain.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface ProductRepository extends CrudRepository<Product, Long> {
    ArrayList<Product> findAllByCategory1AndCategory2(String category1, String category2);
    ArrayList<Product> findAllByStatus(String status);

}
