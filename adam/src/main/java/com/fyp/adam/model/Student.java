package com.fyp.adam.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table(name = "user_group")
public class Student {

    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String name, email;
    @ManyToOne(cascade=CascadeType.PERSIST)
    private User user;

    public Student(String name, String email){
        this.name = name;
        this.email= email;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)
    private Set<Event> events;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}