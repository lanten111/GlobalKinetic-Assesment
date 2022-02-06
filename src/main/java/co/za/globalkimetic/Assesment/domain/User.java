package co.za.globalkimetic.Assesment.domain;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "name cannot be empty")
    @Column( nullable = false, unique = true)
    private String username;

    @NotEmpty(message = "phone number cannot be empty")
    @Column( nullable = false)
    private String phoneNumber;

    @NotEmpty(message = "password cannot be empty")
    @Column( nullable = false)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
