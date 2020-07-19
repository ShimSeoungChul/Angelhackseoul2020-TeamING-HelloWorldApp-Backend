package com.angelhack2020.ing.controller;

import com.angelhack2020.ing.domain.Product;
import com.angelhack2020.ing.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//홈화면:1.추천상품리스트2.신상품리스트
//상세화면:1.상품상세정

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    //@GetMapping("/{id}/{status}")
    @GetMapping("")
    private String list(@RequestParam(value = "init",required = false) String init,
                        @RequestParam(value = "category1",required = false) String category1,
                        @RequestParam(value = "category2",required = false) String category2,
                        @RequestParam(value = "id",required = false) Long id) {
        try {
            String result;
            if (init != null) {
                List<Product> recommendedProducts = productService.getProductsByStatus("nomal");
                List<Product> newProducts = productService.getProductsByStatus("new");

                if(recommendedProducts!=null && newProducts!=null){
                    System.out.println("recLength:"+recommendedProducts.size());
                    System.out.println("newProducts:"+newProducts.size());
                    ObjectMapper mapper = new ObjectMapper();
                    JSONObject jsonObject = new JSONObject();
                    JSONArray recJsonArray = new JSONArray();
                    JSONArray newJsonArray = new JSONArray();

                    for (Product product :recommendedProducts)//배열
                    {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("id", product.getId());
                        sObject.put("name", product.getName());
                        sObject.put("brand", product.getBrand());
                        sObject.put("category1", product.getCategory1());
                        sObject.put("category2", product.getCategory2());
                        sObject.put("status", product.getStatus());
                        sObject.put("price", product.getPrice());
                        sObject.put("goal", product.getGoal());
                        sObject.put("detailUrl", product.getDetailUrl());
                        sObject.put("picUrl", product.getPicUrl());
                        recJsonArray.put(sObject);
                    }

                    for (Product product :newProducts )//배열
                    {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("id", product.getId());
                        sObject.put("name", product.getName());
                        sObject.put("brand", product.getBrand());
                        sObject.put("category1", product.getCategory1());
                        sObject.put("category2", product.getCategory2());
                        sObject.put("status", product.getStatus());
                        sObject.put("price", product.getPrice());
                        sObject.put("goal", product.getGoal());
                        sObject.put("detailUrl", product.getDetailUrl());
                        sObject.put("picUrl", product.getPicUrl());
                        newJsonArray.put(sObject);
                    }

                    jsonObject.put("recommended",recJsonArray);
                    jsonObject.put("new",  newJsonArray);
                    result = jsonObject.toString().replaceAll("\n","");

                }else{
                    result = "FAIL";
                }
                return result;
            }


            if (category1 != null && category2 != null) {
                List<Product> productList = productService.getProductsByCategory(category1, category2);
                System.out.println("productList"+productList.size());
                System.out.println("category1"+category1);
                System.out.println("category2"+category2);
                if (productList == null) {
                    result = "FAIL";
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList);
                }
                return result;
            }


            if (id != null) {
                Product product = productService.getProducts(id);
                System.out.println("product:" + product.toString());
                if (product == null) {
                    result = "FAIL";
                } else {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("id", product.getId());
                    jsonObject.put("name", product.getName());
                    jsonObject.put("price", product.getPrice());
                    jsonObject.put("brand", product.getBrand());
                    jsonObject.put("goal", product.getGoal());
                    jsonObject.put("status",product.getStatus());
                    jsonObject.put("category1", product.getCategory1());
                    jsonObject.put("category2", product.getCategory2());
                    jsonObject.put("detailUrl", product.getDetailUrl());
                    jsonObject.put("picUrl", product.getPicUrl());

                    result = jsonObject.toString();
                }
                return result;
            }
            return "FAIL";
        } catch (Exception e) {
            e.printStackTrace();
            return "FAIl";
        }
    }

}
