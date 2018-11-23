package com.example.TodoSite;

import com.example.TodoSite.domain.Role;
import com.example.TodoSite.domain.RoleRepository;
import java.util.Arrays;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class TodoSiteApplication implements CommandLineRunner{
    
    @Autowired
    private RoleRepository roleRepo;

    @Override
    public void run(String... args) throws Exception{
        Role roles = new Role();
        Role roles2 = new Role();
        
        roles2.setRole("User");
        roles.setRole("Admin");
        
        
        roleRepo.save(roles2);
        roleRepo.save(roles);
        
        
        
    }
    
    public static void main(String[] args) {
        SpringApplication.run(TodoSiteApplication.class, args);
    }
}
