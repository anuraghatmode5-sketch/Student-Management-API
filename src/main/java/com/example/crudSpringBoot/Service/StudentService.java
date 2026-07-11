package com.example.crudSpringBoot.Service;

import com.example.crudSpringBoot.Entity.Student;
import com.example.crudSpringBoot.Repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

      private StudentRepository studentRepository;

      public StudentService(StudentRepository studentRepository){
          this.studentRepository = studentRepository;
      }

      public Student createStudent(Student studentReq){

           Student studentreqs =  studentRepository.save(studentReq);
           return studentreqs;

      }

      public Student getStudent(Long id){
          Optional<Student> studentreqs = studentRepository.findById(id);

          if(studentreqs.isPresent()){
              return studentreqs.get();
          }
          return null;
      }

      public List<Student> getAllStudents(){
          List<Student> response = studentRepository.findAll();
          return response;
      }

      public Student updateStudent(Long id, Student studentreq){
          Optional<Student> existingstudent = studentRepository.findById(id);

          if(existingstudent.isEmpty()){
              return null;
          }

          Student stutosave = existingstudent.get();

          stutosave.setName(studentreq.getName());
          stutosave.setAge(studentreq.getAge());
          stutosave.setEmail(studentreq.getEmail());
          stutosave.setSubject(studentreq.getSubject());
          stutosave.setRollNo(studentreq.getRollNo());

          Student updated = studentRepository.save(stutosave);
          return updated;


      }

      public Boolean deleteStudent(Long id){
         Boolean  isStudent = studentRepository.existsById(id);

         if(!isStudent) return false;
         studentRepository.deleteById(id);

         return true;

      }

}
