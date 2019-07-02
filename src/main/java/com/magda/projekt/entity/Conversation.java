package com.magda.projekt.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "conv")
public class Conversation {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private Integer user;
    private Integer friend;

}
