package com.example.springbootjpamanytomany.enitity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Table(name="`User`")
@Entity
@Data
public class User implements Serializable {

    @SequenceGenerator( name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Id
    private Long userId;

    private String firstName;
    private String lastName;
    private String emailAddress;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("user")
    private Set<Tweet> tweets;

    public User(String frederik, String zwartbol, String s) {
        firstName = frederik;
        lastName = zwartbol;
        emailAddress = s;
    }

    public User() {
    }

}
