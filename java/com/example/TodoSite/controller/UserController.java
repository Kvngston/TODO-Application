/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.TodoSite.controller;

import com.example.TodoSite.domain.RoleRepository;
import com.example.TodoSite.domain.User;
import com.example.TodoSite.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author HP
 */
@Controller
public class UserController {
    
    @Autowired
    private UserRepository userRepo;
    
    //auto wire password encoder
    @Autowired
    private BCryptPasswordEncoder bcrypt;
    
    @Autowired
    private RoleRepository roleRepo;
    
    public static String uploadDirectory = "src\\main\\resources\\static\\images";
    
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model){
        
        //create the instance of the entity class
        User user = new User();
        //adding the object to the html page
        model.addAttribute("user",user);
        
        return "register";
    }
    
    @RequestMapping(value = "/registerUser", method = RequestMethod.POST)
    private String addUser(@ModelAttribute(name = "user")@Valid User user,BindingResult errors ,Model model,@RequestParam("profileimage") MultipartFile files, @RequestParam("cpassword") String cPassword, @RequestParam("dateOfBirth") String dateOfBirth){
        
        if(errors.hasErrors()){
            if(!(user.getPassword().equals(cPassword))){
                System.out.println("Password mis match");
                model.addAttribute("passwordMismatch", true);
            }
            if(files.isEmpty()){
                System.out.println("Profile Image is empty");
                model.addAttribute("empty", true);
            }
            model.addAttribute("error", true);
            return "register";
        }
        
        
        user.setPassword(bcrypt.encode(user.getPassword()));
        user.setDateOfBirth(dateOfBirth);
        user.setProfileImage(files.getOriginalFilename());
        user.setRole(roleRepo.getOne(1));
        
        //System.out.println(userRepo.countByEmail("tochukwuchinedu007@gmail.com"));
        //System.out.println(userRepo.findByLastname("Tochukwu"));
        
        Path fileNameAndPath = Paths.get(uploadDirectory, files.getOriginalFilename());
        try{
            Files.write(fileNameAndPath, files.getBytes());
        }catch(IOException ex){
            System.err.println("Error " + ex.getMessage());
        }
        System.out.println(user);
        userRepo.save(user);
        return "login";
    }
}
