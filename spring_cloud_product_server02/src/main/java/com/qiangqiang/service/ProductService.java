package com.qiangqiang.service;

import com.qiangqiang.entity.Product;

import java.util.List;

//@FeignClient(value = "product-server-impl")
public interface ProductService {
    List<Product> getProduct();
}
