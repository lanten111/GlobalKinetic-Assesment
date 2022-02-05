package co.za.globalkimetic.Assesment.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TokenEntity {

    @Id
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @Column(nullable = false, columnDefinition = "false")
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
