package com.magda.projekt.entity;

import javax.persistence.*;


@Entity
@Table(name = "rates")
public class Rates {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer book;
    private Integer user;
    private float rate;

    public Rates(){}

    public Rates(Integer book,Integer user, float rate)
    {
        this.book=book;
        this.user=user;
        this.rate=rate;
    }

    public Integer getBook() {
        return book;
    }

    public void setBook(Integer book) {
        this.book = book;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUser() {
        return user;
    }

    public void setUser(Integer user) {
        this.user = user;
    }
}
