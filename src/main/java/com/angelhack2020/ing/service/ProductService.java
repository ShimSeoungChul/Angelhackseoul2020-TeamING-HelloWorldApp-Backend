package com.angelhack2020.ing.service;

import com.angelhack2020.ing.dao.ProductRepository;
import com.angelhack2020.ing.domain.Product;
import com.angelhack2020.ing.domain.SurveyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public Product getProducts(Long id){
        Product product = productRepository.findById(id).orElse(null);
        return  product;
    }

    public List<Product> getProductsByCategories(String category1, String category2){
        List<Product> products = productRepository.findAllByCategory1AndCategory2(category1,category2);
        return  products;
    }

    public List<Product> getProductsByCategory(String category1){
        List<Product> products = productRepository.findAllByCategory1(category1);
        return  products;
    }

    public List<Product> getProductsByStatus(String status) {
        List<Product> products = productRepository.findAllByStatus(status);
        return products;
    }

}
