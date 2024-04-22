package mx.edu.utez.extradelextra.model;

import java.time.LocalDateTime;

public class Login {
    private long id;
    private String username;
    private String ipAddress;
    private LocalDateTime date;

    public Login(String username, String ipAddress) {
        this.username = username;
        this.ipAddress = ipAddress;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
