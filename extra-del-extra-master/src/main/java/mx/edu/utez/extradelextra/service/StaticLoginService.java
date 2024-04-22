package mx.edu.utez.extradelextra.service;

import mx.edu.utez.extradelextra.model.Login;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class StaticLoginService implements LoginService {
    private List<Login> logins = new ArrayList<>();
    private long index = 1;

    @Override
    public List<Login> findAll() {
        return logins;
    }

    @Override
    public void save(Login login) {
        login.setId(index);
        login.setDate(LocalDateTime.now());
        logins.add(login);
        index++;
    }
}
