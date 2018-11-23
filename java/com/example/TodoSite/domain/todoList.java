/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TodoSite.domain;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotEmpty;

/**
 *
 * @author HP
 */
@Entity
public class todoList {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    
    @NotEmpty()
    @Column(nullable = false)
    private String Item;
    
    @Column(nullable = false)
    private int completed;
    
    
    @ManyToOne
    @JoinColumn(name="user_id",nullable=false)
    private User user;

    public todoList() {
    }

    public todoList(String Item, int completed, User user) {
        this.Item = Item;
        this.completed = completed;
        this.user = user;
    }
 
    public todoList(int id, String Item, int completed, User user) {
        this.id = id;
        this.Item = Item;
        this.completed = completed;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getItem() {
        return Item;
    }

    public void setItem(String Item) {
        this.Item = Item;
    }

    public int getCompleted() {
        return completed;
    }

    public void setCompleted(int completed) {
        this.completed = completed;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 29 * hash + Objects.hashCode(this.Item);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final todoList other = (todoList) obj;
        if (!Objects.equals(this.Item, other.Item)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "todoList{" + "id=" + id + ", Item=" + Item + ", completed=" + completed+ '}';
    }
}
