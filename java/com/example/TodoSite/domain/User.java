package com.example.TodoSite.domain;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;
@Entity
public class User extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty
    @Pattern(regexp = "^([a-zA-Z]{2,})$", message = "Name must be more than 2 letters")
    @Column(name = "first_name", nullable = false)
    private String firstname;
    @NotEmpty(message = "Name cannot be empty")
    @Pattern(regexp = "^([a-zA-Z]{2,})$", message = "Name must be more than 2 letters")
    @Column(name = "last_name", nullable = false)
    private String lastname;
    @Email
    @NotEmpty(message = "Email vcannot be left empty")
    private String email;
    @NotEmpty(message = "Phone Number cannot be left empty")
    @Pattern(regexp = "([+][2][3][4][7-9]{1}[0-9]{9}|[\\d]{11})", message = "Enter a valid Phone Number")
    private String phoneNumber;
    @NotNull(message = "Date can't be left empty")
    private String dateOfBirth;
    private String profileImage;
    @Size(min = 4)
    @NotEmpty(message = "Please Set up Password")
    private String password;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role",
        joinColumns = @JoinColumn(name = "user_id"),
        inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Role role;
    @OneToMany(
        mappedBy = "user"
    )
    private Set<todoList> todo;
    //constructor
    public User() {
    }
    public User(String firstname, String lastname, String email, String phoneNumber, String dateOfBirth, String profileImage, String password, Role role, Set<todoList> todo) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.dateOfBirth = dateOfBirth;
        this.profileImage = profileImage;
        this.password = password;
        this.role = role;
        this.todo = todo;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getLastname() {
        return lastname;
    }
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhoneNumber() {
        return phoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    public String getDateOfBirth() {
        return dateOfBirth;
    }
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public String getProfileImage() {
        return profileImage;
    }
    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public Set<todoList> getTodo() {
        return todo;
    }
    public void setTodo(Set<todoList> todo) {
        this.todo = todo;
    }
    @Override
    public String toString() {
        return "User{" + "id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email + ", phoneNumber=" + phoneNumber + ", dateOfBirth=" + dateOfBirth + ", profileImage=" + profileImage + ", password=" + password + ", role=" + role + ", todo=" + todo + '}';
    }
}
