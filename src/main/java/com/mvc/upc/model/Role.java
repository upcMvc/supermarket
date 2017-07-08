package com.mvc.upc.model;

import javax.persistence.*;

/**
 * Created by wanghaojun on 2017/7/8.
 */
@Entity
@Table(name = "role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int power;

}
