package io.simpolor.redis.service;

import io.simpolor.redis.repository.CustomStudentRepository;
import io.simpolor.redis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomStudentService {

    private final CustomStudentRepository customStudentRepository;

    public List<Student> getAll() {

        Iterable<Student> students = customStudentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    public Student get(String studentId) {

        Optional<Student> optionalStudent = customStudentRepository.findById(studentId);
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+studentId);
        }

        return optionalStudent.get();
    }

    public Student create(Student student) {

        return customStudentRepository.save(student);
    }

    public Student update(Student student) {

        Optional<Student> optionalStudent = customStudentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return customStudentRepository.save(student);
    }

    public String delete(String studentId) {

        customStudentRepository.deleteById(studentId);

        return studentId;
    }

}
