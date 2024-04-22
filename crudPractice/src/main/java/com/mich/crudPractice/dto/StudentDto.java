package com.mich.crudPractice.dto;

import com.mich.crudPractice.models.Student;
import lombok.AllArgsConstructor;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StudentDto {

    private Long id;
    private String firstName;
    private String lastName;
    @Email(message = "Correo electrónico no válido")
    private String email;
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;
    private Boolean status;

    public Student getStudent(){
        return new Student(
                getId(),
                getFirstName(),
                getLastName(),
                getEmail(),
                getPassword(),
                getStatus()
        );
    }
}
