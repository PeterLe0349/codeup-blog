package com.codeup.springblog.controllers;

import com.codeup.springblog.models.*;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.TagRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {
    private final PostRepository postDao;
    private final UserRepository userDao;
    private final TagRepository tagDao;
    private final EmailService emailService;

    public PostController(PostRepository postDao, UserRepository userDao, TagRepository tagDao, EmailService emailService) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.tagDao = tagDao;
        this.emailService = emailService;
    }

//    @GetMapping("/show")
//    public String show(Model model) {
//        model.addAttribute("post", new Post("title", "description"));
//        return "posts/show";
//    }

    @GetMapping("/index")
    public String index(Model model) {
//        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        System.out.println(principal.getClass());
        List<Post> posts = postDao.findAll();
//        System.out.println(title);
//        System.out.println(post.getTitle());
//        System.out.println(post.getBody());
        model.addAttribute("tags", tagDao.findAll());
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
        model.addAttribute("tags", tagDao.findAll());
        return "posts/edit";
    }

    @PostMapping("/posts/{id}/edit")
    public String editPostPart2(@RequestParam(name="tagid") long tagid, @RequestParam(name="title") String title, @RequestParam(name="body") String body, @RequestParam(name="id") long id){
        System.out.println(title + body + id);
        Post post = postDao.getById(id);
        post.setTitle(title);
        post.setBody(body);
        if(post.getTags() == null){
            post.setTags(new ArrayList<>());
        }
        post.getTags().add(tagDao.getById(tagid));
        postDao.save(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/create")
    public String createPost(Model model) {
        model.addAttribute("post", new Post());
        model.addAttribute("users", userDao.findAll());
        model.addAttribute("tags", tagDao.findAll());
        return "/posts/create";
    }


    @PostMapping("/posts/create")
    public String createPostPart2(@ModelAttribute Post post, @RequestParam(name = "userid") long userid, @RequestParam(name="tagid") long tagid) {
        post.setUser(userDao.getById(userid));
        post.setTags(new ArrayList<>());
        post.getTags().add(tagDao.getById(tagid));
        postDao.save(post);
        emailService.prepareAndSend(post,post.getTitle(), post.getBody());
        return "redirect:/index";

    }

    @GetMapping("/posts/tag/{tag}")
    public String tagPosts(@PathVariable String tag, Model model){
        List<Tag> tags = tagDao.searchByTagLike(tag);
        List<Post> posts = new ArrayList<>();
        if(!tags.isEmpty()){
            posts = tags.get(0).getPosts();
        }
        model.addAttribute("posts", posts);
        return "posts/showByTag";
    }

    @PostMapping("/posts/chooseTag")
    public String tagPostsSelect(@RequestParam(name="tagname") String tagname, Model model){
        System.out.println("i'm in tag search");
        List<Tag> tags = tagDao.searchByTagLike(tagname);
        List<Post> posts = new ArrayList<>();
        if(!tags.isEmpty()){
            posts = tags.get(0).getPosts();
        }
        model.addAttribute("posts", posts);
        System.out.println("passed add model");
        return "posts/showByTag";
    }





}
