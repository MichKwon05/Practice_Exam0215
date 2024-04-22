package com.mich.crudPractice.service;

import com.mich.crudPractice.models.Student;
import com.mich.crudPractice.models.StudentRepository;
import com.mich.crudPractice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Transactional(readOnly = true)
    public CustomResponse<List<Student>> getAll(){
        return new CustomResponse<>(
                this.studentRepository.findAll(),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<Student> getOne(Long id){
        return new CustomResponse<>(
                this.studentRepository.getById(id),
                false,
                200,
                "OK"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Student>> getActive(){
        return new CustomResponse<>(
                this.studentRepository.findAllByStatus(true),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(readOnly = true)
    public CustomResponse<List<Student>> getInactive(){
        return new CustomResponse<>(
                this.studentRepository.findAllByStatus(false),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Student> insert(Student student){
        return new CustomResponse<>(
                this.studentRepository.saveAndFlush(student),
                false,
                200,
                "Ok"
        );
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Student> update(Student student){
        if (this.studentRepository.existsById(student.getId())){
            return new CustomResponse<>(
                    this.studentRepository.saveAndFlush(student),
                    false,
                    200,
                    "Ok"
            );
        } else {
            return new CustomResponse<>(
                    null,
                    true,
                    404,
                    "Estudiante no encontrado"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> changeStatus(Student student){
        if (this.studentRepository.existsById(student.getId())){
            this.studentRepository.updateStatus(student.getStatus(), student.getId());
            return new CustomResponse<>(
                    true,
                    false,
                    200,
                    "Ok"
            );
        } else {
            return new CustomResponse<>(
                    false,
                    true,
                    404,
                    "Estudiante no encontrado"
            );
        }
    }

    @Transactional(rollbackFor = {SQLException.class})
    public CustomResponse<Boolean> delete(Long id){
        if (this.studentRepository.existsById(id)){
            this.studentRepository.deleteById(id);
            return new CustomResponse<>(
                    true,
                    false,
                    200,
                    "Ok"
            );
        } else {
            return new CustomResponse<>(
                    false,
                    true,
                    404,
                    "Estudiante no encontrado"
            );
        }
    }


}
