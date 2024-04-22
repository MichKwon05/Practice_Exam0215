package mx.edu.utez.extradelextra.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class User {
    @NotBlank(message = "En nombre no puede quedar en blanco")
    @Size(max = 20, message = "El nombre no puede ser mayor a 20 caracteres")
    private String name;
    @NotBlank(message = "El apellido no puede quedar en blanco")
    @Size(max = 30, message = "El apellido no puede ser mayor a 30 caracteres")
    private String surname;
    @Size(max = 30, message = "El apellido no puede ser mayor a 30 caracteres")
    private String lastname;
    @NotBlank(message = "El RFC no puede quedar en blanco")
    @Size(min = 12, max = 13, message = "El RFC debe tener entre 12 y 13 caracteres")
    private String rfc;
    @NotBlank(message = "El teléfono no puede quedar en blanco")
    @Size(min = 10, max = 10, message = "El teléfono debe contener 10 caracteres")
    private String phone;
    @NotBlank(message = "El correo no puede quedar en blanco")
    @Size(max = 30, message = "El correo no puede ser mayor a 30 caracteres")
    @Email(message = "El correo debe cumplir con el formato requerido")
    private String email;
    @NotBlank(message = "La contraseña no puede quedar en blanco")
    @Size(max = 8, message = "La contraseña no puede ser mayor a 8 caracteres")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
