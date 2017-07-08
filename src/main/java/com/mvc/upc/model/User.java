package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by wanghaojun on 2017/7/8.
 */
@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;



}
