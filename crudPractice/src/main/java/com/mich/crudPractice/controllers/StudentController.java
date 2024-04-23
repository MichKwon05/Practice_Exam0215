package com.mich.crudPractice.controllers;

import com.mich.crudPractice.dto.StudentDto;
import com.mich.crudPractice.models.Student;
import com.mich.crudPractice.service.StudentService;
import com.mich.crudPractice.utils.CustomResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students/")
@CrossOrigin(origins = {"*"})
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public String listStudents(Model model) {
        model.addAttribute("students", studentService.getAll().getData());
        return "students";
    }
    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        return "register";
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

    @PostMapping("/create")
    public String createStudent(@ModelAttribute("student") StudentDto studentDto) {
        studentService.insert(studentDto.getStudent());
        return "redirect:/api/students/list";
    }


    @PutMapping("/update")
    public String updateStudent(@ModelAttribute("student") StudentDto studentDto) {
        CustomResponse<Student> response = studentService.update(studentDto.getStudent());
        if (!response.isError()) {
            return "redirect:/api/students/list";
        } else {
            // Manejar error, redirigir a página de error o mostrar mensaje de error
            return "redirect:/api/students/list";
        }
    }


    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long id) {
        CustomResponse<Boolean> response = studentService.delete(id);
        if (!response.isError()) {
            return "redirect:/api/students/list";
        } else {
            // Manejar error, redirigir a página de error o mostrar mensaje de error
            return "redirect:/api/students/list";
        }
    }

}
