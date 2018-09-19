package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Product;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @GetMapping
    public List<Product> get() {
        return Arrays.asList(
                new Product("可乐", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/1"),
                new Product("可乐", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/1")
        );
    }

}
