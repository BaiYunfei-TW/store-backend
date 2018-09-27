package cn.yfbai.shopbackend.controller;

import cn.yfbai.shopbackend.entity.Product;
import cn.yfbai.shopbackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.ServiceUnavailableException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Value("${wouldFailed:false}")
    private boolean wouldFailed;

    @GetMapping
    public List<Product> get(HttpServletRequest request, HttpServletResponse response) throws ServiceUnavailableException {
        response.addHeader("x-server-name", request.getLocalName() + ":" + request.getLocalPort());
        if(wouldFailed){
            throw new ServiceUnavailableException("manually break down the service");
        }
        return productService.getProducts();
    }

}
