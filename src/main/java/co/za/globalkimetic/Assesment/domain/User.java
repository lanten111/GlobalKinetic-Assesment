package co.za.globalkimetic.Assesment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private String id;

    @Column
    private String userName;

    @Column
    private String phoneNumber;

    @Column
    private String password;
}
