package co.za.globalkimetic.Assesment.dto;

public class LoginResponseDTO {

    private Long id;
    private String token;

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

    @Override
    public String toString() {
        return "{" +
                "id=" + id +
                ", token='" + token + '\'' +
                '}';
    }
}
