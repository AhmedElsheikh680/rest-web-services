package com.sprng.rest.api.controller;

import com.sprng.rest.api.exception.StudentErrorResponse;
import com.sprng.rest.api.exception.StudentException;
import com.sprng.rest.api.model.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MainController {

    List<Student> list = new ArrayList<>();

    // http://localhost:8080/api/v1/main
    @GetMapping("/main")
    public String main(){
        return "Hello World!!";
    }

    @PostConstruct
    public void start(){
        Student s1 = new Student(1, "AHmed", "012450");
        Student s2 = new Student(2, "Mohamed", "012450");
        Student s3 = new Student(3, "Ali", "012450");
        Student s4 = new Student(4, "Ramy", "012450");
        Student s5 = new Student(5, "Samy", "012450");

        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
    }

    // http://localhost:8080/api/v1/students
    @GetMapping("students")
    public List<Student> getStudents(){

        return list;
    }
    // http://localhost:8080/api/v1/students/{id}
    @GetMapping("students/{id}")
    public Student getStudentById(@PathVariable int id){
        if(id <= 0 || id > list.size()){
            throw new StudentException("Student ID "+ id + " Not Found");
        }
        return list.get(id -1);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> getExceptionNotFound(StudentException studentException){
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(studentException.getMessage());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());
        return new ResponseEntity<StudentErrorResponse>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> getException(Exception ex){
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();
        studentErrorResponse.setStatusCode(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(ex.getMessage());
        studentErrorResponse.setTimestamp(System.currentTimeMillis());
        return  new ResponseEntity<StudentErrorResponse>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }















}
