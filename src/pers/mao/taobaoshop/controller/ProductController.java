package pers.mao.taobaoshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import pers.mao.taobaoshop.service.ProductService;

@Controller
public class ProductController {
    @Autowired
    private ProductService productService;


}
