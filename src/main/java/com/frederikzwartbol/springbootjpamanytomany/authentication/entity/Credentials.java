package com.frederikzwartbol.springbootjpamanytomany.authentication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;

@Table(name="credentials")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
        @SequenceGenerator( name = "credentials_sequence",
                sequenceName = "credentials_sequence",
                allocationSize = 1)
        @GeneratedValue(
                strategy = GenerationType.SEQUENCE,
                generator = "credentials_sequence"
        )
        @Column(name = "id")
        @Id
        private Long id;

        /** username must be unique*/
        @Column(unique=true)
        private String username;
        private String password;

        @ManyToMany(fetch = FetchType.EAGER)
        private Collection<Role> roles = new HashSet();

        public Credentials(String username, String password) {
                this.username = username;
                this.password = password;
        }
}
