package com.example.crudSpringBoot.Controller;


import com.example.crudSpringBoot.Entity.Student;
import com.example.crudSpringBoot.Service.StudentService;
import com.example.crudSpringBoot.dto.CreateStudentRequestDto;
import com.example.crudSpringBoot.dto.CreateStudentResponseDto;
import com.example.crudSpringBoot.dto.UpdateStudentRequestDto;
import com.example.crudSpringBoot.dto.UpdateStudentResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

       private StudentService studentService;

       public StudentController(StudentService studentService){
              this .studentService = studentService;
       }

       @PostMapping
       public ResponseEntity<CreateStudentResponseDto> createStudent( @Valid @RequestBody CreateStudentRequestDto createStudentRequestDto){
           CreateStudentResponseDto createdStudent =  studentService.createStudent(createStudentRequestDto);
             return ResponseEntity
                     .status(HttpStatus.CREATED)
                     .body(createdStudent);

       }

       @GetMapping("/{id}")
       public ResponseEntity<CreateStudentResponseDto> getStudent(@PathVariable Long id){
           CreateStudentResponseDto Studentresp = studentService.getStudent(id);
           return ResponseEntity.ok(Studentresp);
       }

       @GetMapping
       public ResponseEntity<List<CreateStudentResponseDto>> getAllStudents(){
           List<CreateStudentResponseDto> studentlist = studentService.getAllStudents();
           return ResponseEntity.ok(studentlist);
       }

    @PutMapping("/{id}")
    public ResponseEntity<UpdateStudentResponseDto> updateStudent(@PathVariable Long id, @RequestBody UpdateStudentRequestDto studentreq){
        UpdateStudentResponseDto updateStudentResponseDto = studentService.updateStudent(id,studentreq);
        return ResponseEntity.ok(updateStudentResponseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
          studentService.deleteStudent(id);
          return ResponseEntity.noContent().build();
    }

    @PatchMapping("/delete-soft/{id}")
        public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
            studentService.deleteStudentSoftly(id);
            return ResponseEntity.notFound().build();

        }



}
