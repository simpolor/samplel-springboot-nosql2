package io.simpolor.redis.repository;

import io.simpolor.redis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
/*
@Repository
@RequiredArgsConstructor
public class StoreRepository {

    public static Long INDEX = 1L;
    private final RedisTemplate redisTemplate;

    public List<Student> findAll(){

        // redisTemplate.opsForList()

        // return studentMap.keySet().stream().map(key -> studentMap.get(key)).collect(Collectors.toList());
    }

    public Student save(Student student){

        *//*if(Objects.isNull(student.getStudentId())){
            student.setStudentId(INDEX++);
        }
        redisTemplate.set (student.getStudentId(), student);

        return student;*//*
    }

    *//*Iterable<Student> students = studentRepository.findAll();
    Optional<Student> optionalStudent = studentRepository.findById(studentId);
    return studentRepository.save(student);
    studentRepository.deleteById(studentId);*//*

}*/
