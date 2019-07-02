package com.magda.projekt.entity;

import javax.persistence.*;


@Entity
@Table(name = "WantToRead")
public class WantToRead {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer book;
    private Integer user;

    public WantToRead(){}

    public WantToRead(Integer book,Integer user)
    {
        this.book=book;
        this.user=user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
