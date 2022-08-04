package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/show")
    public String show(Model model) {
        model.addAttribute("post", new Post("title", "description"));
        return "posts/show";
    }

    @GetMapping("/index")
    public String index(Model model) {

        List<Post> posts = postDao.findAll();
//        System.out.println(title);
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
        model.addAttribute("posts", posts);
        return "posts/index";
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String indexPage() {
        return "posts/index";
    }


    @RequestMapping(path = "/show/{indexnum}", method = RequestMethod.GET)
    public String showPost(@PathVariable int indexnum, Model model) {
        model.addAttribute("post", postDao.getById((long)indexnum));
        return "posts/show";
    }

    @GetMapping("/posts/create")
    public String createPost() {
        return "/posts/create";
    }


    @PostMapping("/posts/create")
    public String createPostPart2(@RequestParam(name = "title") String title,@RequestParam(name = "description") String description, Model model) {
        Post post = new Post(title, description, userDao.getById(1L));
        postDao.save(post);
//        System.out.println(title);
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
        model.addAttribute("post", post);
        return "posts/show";
    }



}
