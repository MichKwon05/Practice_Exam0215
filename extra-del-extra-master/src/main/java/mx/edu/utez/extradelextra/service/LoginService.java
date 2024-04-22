package mx.edu.utez.extradelextra.service;

import mx.edu.utez.extradelextra.model.Login;

import java.util.List;

public interface LoginService {
    List<Login> findAll();
    void save(Login login);
}
