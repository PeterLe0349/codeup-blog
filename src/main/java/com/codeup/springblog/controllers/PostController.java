package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;

    public PostController(PostRepository postDao, UserRepository userDao, TagRepository tagDao) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
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

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable long id, Model model) {
        model.addAttribute("post", postDao.getById(id));
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostPart2(@RequestParam(name="title") String title, @RequestParam(name="body") String body, @RequestParam(name="id") long id){
        System.out.println(title + body + id);
        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setBody(body);
        postDao.save(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("users", userDao.findAll());
        return "/posts/create";
    }


    @PostMapping("/posts/create")
    public String createPostPart2(@ModelAttribute Post post, @RequestParam(name = "userid") long userid) {
        post.setUser(userDao.getById(userid));
        postDao.save(post);
        return "redirect:/index";

    }



}
