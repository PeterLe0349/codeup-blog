package com.codeup.springblog.models;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length=100)
    private String title;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="books_genres",
            joinColumns = {@JoinColumn(name="book_id")},
            inverseJoinColumns ={@JoinColumn(name="genre_id")}
    )
    private List<Genre> genres;

    public Book() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }
}
