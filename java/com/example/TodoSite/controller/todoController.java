package com.example.TodoSite.controller;

import com.example.TodoSite.domain.User;
import com.example.TodoSite.domain.UserRepository;
import com.example.TodoSite.domain.todoList;
import com.example.TodoSite.domain.todoListRepository;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author HP
 */
@Controller
public class todoController {
    
    @Autowired
    private todoListRepository todoRepo;
    
    @Autowired
    private UserRepository userRepo;
    
    @RequestMapping(value = "/todo" , method = RequestMethod.GET)
    public String gettodoPage(Principal principal, Model model){
        // the principal refers to the logged in user 
        User user = userRepo.findByEmail(principal.getName());
        System.out.println(user.getId());
        todoList todoList = new todoList();
        model.addAttribute("todoList", todoList);
        model.addAttribute("ProfileImage", user);
        
        //model.addAttribute("list", todoRepo.findByUser(user.getId()));
        
        ArrayList userTodos = todoRepo.findByUserId(user.getId());
        System.out.println(userTodos);
        
        if(userTodos.isEmpty())
            model.addAttribute("emptyList", true);
        else{
            model.addAttribute("listNotEmpty", true);
            model.addAttribute("list", todoRepo.findByUserId(user.getId()));
        }
       
        int completed = todoRepo.findByUserIdAndCompleted(user.getId(), 1).size();
        int notCompleted = userTodos.size() - completed;
        int noOfTodos = userTodos.size();
        
        model.addAttribute("completed", completed);
        model.addAttribute("notCompleted", notCompleted);
        model.addAttribute("noOfTodos", noOfTodos);
        
        return "todos";
    }
    
    @RequestMapping(value ="/upload", method=RequestMethod.POST)
    public String postItem(@ModelAttribute(name = "todoList")@Valid todoList todoList, BindingResult bindingResult,Principal principal, Model model){
        
        //retrieve the user
        User user = userRepo.findByEmail(principal.getName());
        
        todoList.setUser(user);
        
        
        //checking successful save
        boolean check = false;
        
        //assert
        System.out.println("New Item : " + todoList);
        
        //check if the form has errors
        if(bindingResult.hasErrors())
            return "todos";
        
        //submitting the form to the db
        todoRepo.save(todoList);
        check = true;
            
            if(check){
                model.addAttribute("check", check);
                model.addAttribute("list", todoRepo.findAll());
                model.addAttribute("ProfileImage", user);
            }
        
        return "redirect:/todo";
    }
    
    @RequestMapping(value = "/delete{id}", method = RequestMethod.GET)
    public String deleteRecord(@PathVariable(name="id") Integer id, Model model){
        
        
        todoRepo.deleteById(id);
        
        return "redirect:/todo";
    }
    
    
    @RequestMapping(value = "/edit{id}", method = RequestMethod.GET)
    public String displayEditForm(@PathVariable(name="id") Integer id, Model model){
        
        System.out.println(id);
        //declare variable message
        String message= "Edit record for  item " ;
        
        Optional<todoList> items = todoRepo.findById(id);
        System.out.println(todoRepo.findById(id));
        //check for emptiness
        if(items.isPresent()){
            model.addAttribute("todoList", items.get());
            message += items.get().getId();
        }
            
         
        model.addAttribute("check", false);
        model.addAttribute("message", message);
        
        return "editItem";
    }
    
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateRecord(@ModelAttribute(name = "todoList") todoList todoList , BindingResult bindingResult, Model model){
        
        boolean check;
        
        
        //delete studentobject
         if(bindingResult.hasErrors())
            return "editItem";
        
        //submitting the form to the db
        todoRepo.save(todoList);
        check = true;
            
            
            
            if(check){
                model.addAttribute("check", check);
                model.addAttribute("todoList", todoRepo.findAll());
            }
        return "redirect:/todo";
    }
    
    @RequestMapping(value = "/completed{id}", method = RequestMethod.GET)
    public String completed(@PathVariable(name = "id" )Integer id){
        Optional<todoList> todo = todoRepo.findById(id);
        
        if(todo.isPresent()){
            if(todo.get().getCompleted()==0){
                todo.get().setCompleted(1);
            }else
            {
                todo.get().setCompleted(0);
            }
        }
        
        todoRepo.save(todo.get());
        
        return "redirect:/todo";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }
    
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout(){
        return "login";
    }
}
