/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TodoSite.domain;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author HP
 */
public interface todoListRepository extends JpaRepository<todoList, Integer>{
    
    ArrayList<todoList> findByUserId(Integer id);
    
    List findByUserIdAndCompleted(Integer user, Integer id);
}
