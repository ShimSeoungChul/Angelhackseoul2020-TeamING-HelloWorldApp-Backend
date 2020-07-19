package com.angelhack2020.ing.controller;

import com.angelhack2020.ing.domain.Event;
import com.angelhack2020.ing.domain.Product;
import com.angelhack2020.ing.domain.Review;
import com.angelhack2020.ing.service.EventService;
import com.angelhack2020.ing.service.ProductService;
import com.angelhack2020.ing.service.ReviewService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.xdevapi.JsonArray;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
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

    @Autowired
    private EventService eventService;

    @Autowired
    private ReviewService reviewService;

    //@GetMapping("/{id}/{status}")
    @GetMapping("")
    private String list(@RequestParam(value = "init",required = false) String init,
                        @RequestParam(value = "category1",required = false) String category1,
                        @RequestParam(value = "category2",required = false) String category2,
                        @RequestParam(value = "id",required = false) Long id,
                        @RequestParam(value = "userId",required = false) String userId) {
        try {
            String result;
            if (init != null) {
                List<Product> recommendedProducts = productService.getProductsByStatus("recommend");
                List<Product> newProducts = productService.getProductsByStatus("new");

                //메인화면 초기화
                if(recommendedProducts!=null && newProducts!=null){
                    System.out.println("recLength:"+recommendedProducts.size());
                    System.out.println("newProducts:"+newProducts.size());
                    ObjectMapper mapper = new ObjectMapper();
                    JSONArray jsonArray = new JSONArray();
                    JSONObject recJsonObject = new JSONObject();
                    JSONObject newJsonObject = new JSONObject();
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

                    recJsonObject.put("recommended",recJsonArray);
                    newJsonObject.put("new",  newJsonArray);
                    jsonArray.put(recJsonObject);
                    jsonArray.put(newJsonObject);
                    result = jsonArray.toString();

                }else{
                    result = "FAIL";
                }
                return result;
            }


            //세부 카테고리 페이지 가져오기
            if (category1 != null && category2 != null) {
                if(category1.equals("리빙")){category1="주방&리빙";};
                List<Product> productList = productService.getProductsByCategories(category1, category2);
                if (productList == null) {
                    result = "FAIL";
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList);
                }
                return result;
            }

            //전체 카테고리 페이지 가져오기
            if (category1 != null && category2 == null) {
                if(category1.equals("리빙")){category1="주방&리빙";};
                List<Product> productList = productService.getProductsByCategory(category1);
                if (productList == null) {
                    result = "FAIL";
                } else {
                    ObjectMapper mapper = new ObjectMapper();
                    result = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(productList);
                }
                return result;
            }


            //상세 페이지 가져오기
            if (id != null) {
                Product product = productService.getProducts(id);
                System.out.println("product:" + product.toString());
                if (product == null) {
                    result = "FAIL";
                } else {
                    //상품 클릭 이벤트 저장
                    Event save = Event.builder()
                            .event("click")
                            .productId((int)(long)product.getId())
                            .userId(userId)
                            .time(LocalDateTime.now())
                            .build();
                    Event saved = eventService.saveEvent(save);

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

                    List<Review> reviews = reviewService.getReviews(id);
                    if(reviews==null){
                        result = "FAIL";
                        return result;
                    }

                    JSONArray jsonArray = new JSONArray();
                    for (Review review :reviews )//배열
                    {
                        JSONObject sObject = new JSONObject();//배열 내에 들어갈 json
                        sObject.put("id", review.getId());
                        sObject.put("productId", review.getProductId());
                        sObject.put("userId", review.getUserId());
                        sObject.put("subject", review.getSubject());
                        sObject.put("content", review.getContent());
                        sObject.put("star", review.getStar());
                        jsonArray.put(sObject);
                    }

                    jsonObject.put("reviews",jsonArray);
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
