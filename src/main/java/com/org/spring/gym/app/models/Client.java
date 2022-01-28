package com.org.spring.gym.app.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "clients")
public class Client {
    @Id
    private String id;
    private String CIN;
    private String firstname;
    private String lastname;
    private String email;
    private String phone;
    private String subscription;
    private String startAt;
    private String endAt;

    public String getCIN() { return CIN; }

    public void setCIN(String CIN) { this.CIN = CIN; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getFirstname() { return firstname; }

    public void setFirstname(String firstname) { this.firstname = firstname; }

    public String getLastname() { return lastname; }

    public void setLastname(String lastname) { this.lastname = lastname; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPhone() { return phone; }

    public void setPhone(String phone) { this.phone = phone; }

    public String getSubscription() { return subscription; }

    public void setSubscription(String subscription) { this.subscription = subscription; }

    public String getStartAt() { return startAt; }

    public void setStartAt(String startAt) { this.startAt = startAt; }

    public String getEndAt() { return endAt; }

    public void setEndAt(String endAt) { this.endAt = endAt; }

    public Client(String CIN,String firstname, String lastname, String email, String phone, String subscription, String startAt, String endAt) {
        this.CIN = CIN;
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.phone = phone;
        this.subscription = subscription;
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
