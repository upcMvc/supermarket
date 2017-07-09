package com.mvc.upc.security.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by lylllcc on 2017/7/8.
 */
@Entity
@Table(name = "authority")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private AuthorityName name;

    @ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public AuthorityName getName() {
        return name;
    }

    public void setName(AuthorityName name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
