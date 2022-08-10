package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.Product;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.ProductRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ProductController {

    private final ProductRepository productDao;

    public ProductController(ProductRepository productDao) {
        this.productDao = productDao;
    }

    @GetMapping("/products")
    public String showProducts(Model model){
        model.addAttribute("products", productDao.findAll());
        return "products/index";
    }

    @GetMapping("/products/create")
    public String createProduct(Model model) {
        model.addAttribute("product", new Product());
//        model.addAttribute("users", userDao.findAll());
//        model.addAttribute("tags", tagDao.findA
//        ll());
        return "/products/create";
    }


    @PostMapping("/products/create")
    public String createProductPart2(@ModelAttribute Product product, @RequestParam(name="name") String name, @RequestParam(name="price") String price){
//        post.setUser(userDao.getById(userid));
//        post.setTags(new ArrayList<>());
//        post.getTags().add(tagDao.getById(tagid));
        Product product2 = new Product(name, price);
        productDao.save(product2);
//        productDao.save(product);
//        emailService.prepareAndSend(post,post.getTitle(), post.getBody());
        return "redirect:/products";

    }
}
