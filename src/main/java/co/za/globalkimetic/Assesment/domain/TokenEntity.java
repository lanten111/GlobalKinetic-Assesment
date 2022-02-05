package co.za.globalkimetic.Assesment.domain;

import javax.persistence.*;

@Entity
public class TokenEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "TOKEN", nullable = false, unique = true)
    private String token;

    @Column(name = "LOGEEDOUT", nullable = false)
    private boolean loggedOut;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public boolean isLoggedOut() {
        return loggedOut;
    }

    public void setLoggedOut(boolean loggedOut) {
        this.loggedOut = loggedOut;
    }
}
