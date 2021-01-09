package com.qiangqiang.service;

import com.qiangqiang.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
//@FeignClient(value = "product-server-impl")
public interface ProductService {
    List<Product> getProduct();
}
