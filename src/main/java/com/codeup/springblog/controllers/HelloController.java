package com.codeup.springblog.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class HelloController {

    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello from Spring!";
    }

    @RequestMapping(path ="/hello/{name}", method = RequestMethod.GET)
    @ResponseBody
    public String hellToYou(@PathVariable String name){
        return String.format("nice to meet you, %s!", name);
    }

    @GetMapping("/greet")
    @ResponseBody
    public String greet() {
        return "Greetings from Spring!";
    }

    @GetMapping("/greet/{name}")
    @ResponseBody
    public String greetToYou(@PathVariable String name) {
        return "Greetings from Spring! " + name;
    }

}
