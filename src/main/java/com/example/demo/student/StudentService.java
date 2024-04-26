package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static java.util.Calendar.MAY;
@Service
public class StudentService {
    private final StudentsRepository studentsRepository;
    @Autowired
    public StudentService(StudentsRepository studentsRepository) {
        this.studentsRepository = studentsRepository;
    }

    public List<Student> getStudents(){
        return studentsRepository.findAll();
    }

    public void addNewStudent(Student student) {
        Optional<Student> studentOptional= studentsRepository.findStudentByEmail(student.getEmail());
        if(studentOptional.isPresent()){
            throw new IllegalStateException("Student already exists");
        }
        studentsRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        boolean exists= studentsRepository.existsById(studentId);
        if(!exists){throw new IllegalStateException("does not exsist");}
        studentsRepository.deleteById(studentId);
    }
    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student= studentsRepository.findById(studentId).orElseThrow(()->new IllegalStateException("doesnot exsist"));
        if(name!=null && name.length()>0 && !Objects.equals(student.getName(),name)){
            student.setName(name);
        }
        if(email!=null && email.length()>0&& !Objects.equals(student.getEmail(),email)){
            Optional<Student> studentOptional= studentsRepository.findStudentByEmail(email);
            if(studentOptional.isPresent()){
                throw new IllegalStateException("email already exists");
            }
            student.setEmail(email);
        }
    }
}
