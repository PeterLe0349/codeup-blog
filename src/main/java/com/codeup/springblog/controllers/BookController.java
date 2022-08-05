package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Book;
import com.codeup.springblog.repositories.BookRepository;
import com.codeup.springblog.repositories.GenreRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class BookController {

    private BookRepository bookDao;
    private GenreRepository genreDao;

    public BookController(BookRepository bookDao, GenreRepository genreDao) {
        this.bookDao = bookDao;
        this.genreDao = genreDao;
    }

    @GetMapping("/books")
    public String bookIndex(Model model){
//        Book book = bookDao.getById(1L)
//        book.getGenres().add(genreDao.getById(2L));
        model.addAttribute("books", bookDao.findAll());
        return "books/index";
    }

    @GetMapping("/books/delete/{n}")
    public String deleteBook(@PathVariable long n){
        return "redirect:/books";
    }

    @GetMapping("/books/edit")
    public String editBook(Model model){
        model.addAttribute("books", bookDao.findAll());
        model.addAttribute("genres", genreDao.findAll());
        return "books/editGenre";
    }

    @PostMapping("books/edit")
    public String editBookPart2(@RequestParam(name="bookid") long bookid, @RequestParam(name="genre") long genreid){
        Book book = bookDao.getById(bookid);
        book.getGenres().add(genreDao.getById(genreid));
        bookDao.save(book);
        return "redirect:/books";
    }



}
