package com.mich.crudPractice.controllers;

import com.mich.crudPractice.dto.StudentDto;
import com.mich.crudPractice.models.Student;
import com.mich.crudPractice.service.StudentService;
import com.mich.crudPractice.utils.CustomResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/")
@CrossOrigin(origins = {"*"})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public ResponseEntity<CustomResponse<List<Student>>> getAll(){
        return new ResponseEntity<>(
                this.studentService.getAll(),
                HttpStatus.OK
        );
    }

    @GetMapping("/active")
    public ResponseEntity<CustomResponse<List<Student>>> getActive(){
        return new ResponseEntity<>(
                this.studentService.getActive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/inactive")
    public ResponseEntity<CustomResponse<List<Student>>> getInactive(){
        return new ResponseEntity<>(
                this.studentService.getInactive(),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> getOne(@PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.studentService.getOne(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/")
    public ResponseEntity<CustomResponse<Student>> insert(
            @RequestBody StudentDto dto, @Valid BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        CustomResponse<Student> response = this.studentService.insert(dto.getStudent());
        return new ResponseEntity<>(
                response,
                HttpStatus.CREATED
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomResponse<Student>> update(
            @RequestBody StudentDto dto, @Valid BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
                this.studentService.update(dto.getStudent()),
                HttpStatus.OK
        );
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> updateStatus(
            @RequestBody StudentDto dto){
        return new ResponseEntity<>(
                this.studentService.changeStatus(dto.getStudent()),
                HttpStatus.OK
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomResponse<Boolean>> delete(
            @PathVariable("id") Long id){
        return new ResponseEntity<>(
                this.studentService.delete(id),
                HttpStatus.OK
        );
    }

}
