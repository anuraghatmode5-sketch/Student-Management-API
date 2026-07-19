package com.example.crudSpringBoot.Service;

import com.example.crudSpringBoot.Entity.Student;
import com.example.crudSpringBoot.GlobalExecptionHandler.ResourceNotFound;
import com.example.crudSpringBoot.Repository.StudentRepository;
import com.example.crudSpringBoot.dto.CreateStudentRequestDto;
import com.example.crudSpringBoot.dto.CreateStudentResponseDto;
import com.example.crudSpringBoot.dto.UpdateStudentRequestDto;
import com.example.crudSpringBoot.dto.UpdateStudentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

      private StudentRepository studentRepository;

      public StudentService(StudentRepository studentRepository){
          this.studentRepository = studentRepository;
      }

      public CreateStudentResponseDto createStudent(CreateStudentRequestDto createStudentRequestDto){

           Student student = maptoEntity(createStudentRequestDto);

           Student studentreqs =  studentRepository.save(student);

           return maptoDto(studentreqs);

      }

      public CreateStudentResponseDto getStudent(Long id){
          Student studentreqs = studentRepository
                  .findByIdAndDeletedIsFalse(id)
                  .orElseThrow(()-> new ResourceNotFound("Student Not Found"));

          return maptoDto(studentreqs);
      }

      public List<CreateStudentResponseDto> getAllStudents(){
          List<Student> response = studentRepository.findByDeletedIsFalse();
          return response.stream()
                  .map(this::maptoDto)
                  .toList();
      }

      public UpdateStudentResponseDto updateStudent(Long id, UpdateStudentRequestDto studentreq){
          Student existingstudent = studentRepository
                  .findByIdAndDeletedIsFalse(id)
                  .orElseThrow(()-> new ResourceNotFound("Student Not Found"));

          Student stutosave = existingstudent;

          stutosave.setName(studentreq.getName());
          stutosave.setAge(studentreq.getAge());
          stutosave.setSubject(studentreq.getSubject());
          stutosave.setRollNo(studentreq.getRollNo());
          stutosave.setDeleted(false);

          Student updated = studentRepository.save(stutosave);

          return maptoUpdateDto(updated);


      }

      public void deleteStudent(Long id){
         Student student = studentRepository
                 .findById(id)
                 .orElseThrow(()-> new ResourceNotFound("Student Not Found"));


         studentRepository.delete(student);

      }

      public void deleteStudentSoftly(Long id){

          Student existingstudent = studentRepository
                  .findByIdAndDeletedIsFalse(id)
                  .orElseThrow(()-> new ResourceNotFound("Student Not Found"));

          existingstudent.setDeleted(true);
          studentRepository.save(existingstudent);
      }

      private Student maptoEntity(CreateStudentRequestDto createStudentRequestDto){

          Student student = new Student();

          student.setName(createStudentRequestDto.getName());
          student.setAge(createStudentRequestDto.getAge());
          student.setEmail(createStudentRequestDto.getEmail());
          student.setSubject(createStudentRequestDto.getSubject());
          student.setRollNo(createStudentRequestDto.getRollNo());
          student.setDeleted(false);

          return student;

      }

      private CreateStudentResponseDto maptoDto(Student student){
          CreateStudentResponseDto createStudentResponseDto = new CreateStudentResponseDto();

          createStudentResponseDto.setId(student.getId());
          createStudentResponseDto.setName(student.getName());
          createStudentResponseDto.setAge(student.getAge());
          createStudentResponseDto.setEmail(student.getEmail());
          createStudentResponseDto.setRollNo(student.getRollNo());
          createStudentResponseDto.setSubject(student.getSubject());

          return createStudentResponseDto;
      }

      private UpdateStudentResponseDto maptoUpdateDto(Student student){

          UpdateStudentResponseDto updateStudentResponseDto = new UpdateStudentResponseDto();

          updateStudentResponseDto.setId(student.getId());
          updateStudentResponseDto.setName(student.getName());
          updateStudentResponseDto.setAge(student.getAge());
          updateStudentResponseDto.setEmail(student.getEmail());
          updateStudentResponseDto.setRollNo(student.getRollNo());
          updateStudentResponseDto.setSubject(student.getSubject());

          return updateStudentResponseDto;

      }

}
