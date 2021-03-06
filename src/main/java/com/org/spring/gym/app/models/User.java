package com.org.spring.gym.app.models;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
public class User {
    @Id
    private String id;

    private String CIN;
    private String firstname;
    private String lastname;
    private String phone;
    private String position;
    private String speciality;

    @NotBlank
    @Size(max = 20)
    private String username;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 120)
    private String password;

    @DBRef
    private Set<Client> privateClients;
    @DBRef
    private Set<Role> roles = new HashSet<>();

    public User(){}

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User(String CIN, String firstname, String lastname, String phone, String position,
                 String speciality,String email, String username, String password) {
        this.CIN = CIN;
        this.firstname = firstname;
        this.lastname = lastname;
        this.position = position;
        this.speciality = speciality;
        this.phone = phone;
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public String getCIN() { return CIN; }

    public void setCIN(String CIN) { this.CIN = CIN; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getPosition() { return position; }

    public void setPosition(String position) { this.position = position; }

    public String getSpeciality() { return speciality; }

    public void setSpeciality(String speciality) { this.speciality = speciality; }

    public Set<Client> getPrivateClients() { return privateClients; }

    public void setPrivateClients(Set<Client> privateClients) { this.privateClients = privateClients; }
}
