package swa.semproject.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(name = "userName", unique = true, nullable = false)
    private String userName;

    @Column(name = "password", nullable = false)
    public String password;

    @Column(name = "firstName")
    private String firstName;

    @Column(name = "lastName")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "tel")
    private Integer tel;

}

