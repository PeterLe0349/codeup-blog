package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/show")
    public String show(Model model) {
        List<Post> posts = new ArrayList<>();
        posts.add(new Post("first", "some first stuff"));
        posts.add(new Post("second", "some second stuff"));
//        System.out.println(title);
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
        model.addAttribute("posts", posts);
        return "posts/show";
    }

    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("post", new Post("title", "description"));
        return "posts/index";
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    @ResponseBody
    public String indexPage() {
        return "This is the index page"; //retrieve data id and post it
    }


    @RequestMapping(path = "/posts/{indexnum}", method = RequestMethod.GET)
    @ResponseBody
    public String indexPage(@PathVariable int indexnum) {
        return "Show post: " + indexnum; //retrieve data id and post it
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String createPostForm() {
        return "This is the form page for creating post <form action=\"/posts/create\" method=\"post\"><button type=\"submit\">Submit</button></form>"; //retrieve data id and post it
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String createPostAction() {
        return "Process the created form values"; //retrieve data id and post it
    }



}
