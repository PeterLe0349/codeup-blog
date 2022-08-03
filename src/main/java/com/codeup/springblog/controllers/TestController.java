package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TestController {

    @GetMapping("/test")
    public String test(Model model){
        model.addAttribute("post", new Post("title post","somethig"));
        return "testStuff";
    }
}
