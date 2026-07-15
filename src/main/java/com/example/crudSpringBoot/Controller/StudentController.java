package com.example.crudSpringBoot.Controller;


import com.example.crudSpringBoot.Entity.Student;
import com.example.crudSpringBoot.Service.StudentService;
import org.apache.coyote.Response;
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

       @PostMapping("/create")
       public ResponseEntity<Student> createStudent(@RequestBody Student student){
             Student createdStudent =  studentService.createStudent(student);
             return ResponseEntity
                     .status(HttpStatus.CREATED)
                     .body(createdStudent);

       }

       @GetMapping("/get/{id}")
       public ResponseEntity<Student> getStudent(@PathVariable Long id){
           Student Studentresp = studentService.getStudent(id);

           if(Studentresp == null){
               return ResponseEntity.notFound().build();
           }
           return ResponseEntity.ok(Studentresp);
       }

       @GetMapping("/getAll")
       public ResponseEntity<List<Student>> getAllStudents(){
           List<Student> studentlist = studentService.getAllStudents();

           if(studentlist == null){
               return ResponseEntity.notFound().build();
           }
           return ResponseEntity.ok(studentlist);
       }

    @PutMapping("/update/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student studentreq){
        Student Studentresp = studentService.updateStudent(id,studentreq);

        if(Studentresp == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(Studentresp);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id){
          Boolean isDeleted = studentService.deleteStudent(id);

          if(!isDeleted){
              return ResponseEntity.notFound().build();
          }
          return ResponseEntity.ok("Record Deleted");
    }

    @PatchMapping("/delete-soft/{id}")
        public ResponseEntity<String> deleteStudentSoftly(@PathVariable Long id){
            Boolean isDeleted = studentService.deleteStudentSoftly(id);

            if(!isDeleted){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok("Record Deleted");
        }



}
