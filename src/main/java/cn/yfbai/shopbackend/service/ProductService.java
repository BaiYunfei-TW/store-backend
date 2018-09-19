package cn.yfbai.shopbackend.service;

import cn.yfbai.shopbackend.entity.Product;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductService {
    public List<Product> getProducts() {
        return Arrays.asList(
                new Product("可乐", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/1"),
                new Product("雪碧", "瓶", BigDecimal.valueOf(4.5), 10, "/api/img/2")
        );
    }
}
