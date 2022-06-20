package io.simpolor.redis.service;

import io.simpolor.redis.repository.CustomStudentRepository;
import io.simpolor.redis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CacheableStudentService {

    private final CustomStudentRepository customStudentRepository;

    @Cacheable(keyGenerator = "customKeyGenerator", cacheNames = "CACHEABLE_LIST", cacheManager = "timeoutCacheManager")
    public List<Student> getAll() {

        Iterable<Student> students = customStudentRepository.findAll();
        List<Student> list = new ArrayList<>();
        for(Student student : students){
            list.add(student);
        }

        return list;
    }

    @Cacheable(key = "#studentId", cacheNames ="CACHEABLE_GET", cacheManager = "cacheManager")
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

    @CacheEvict(key = "#student.studentId", cacheNames ="CACHEABLE_GET", cacheManager = "cacheManager")
    public Student update(Student student) {

        Optional<Student> optionalStudent = customStudentRepository.findById(student.getStudentId());
        if(!optionalStudent.isPresent()){
            throw new IllegalArgumentException("studentId : "+student.getStudentId());
        }

        return customStudentRepository.save(student);
    }

    @CacheEvict(key = "#studentId", cacheNames ="CACHEABLE_GET", cacheManager = "cacheManager")
    public String delete(String studentId) {

        customStudentRepository.deleteById(studentId);

        return studentId;
    }

    /**
     * key : 캐시 키
     * cacheNames(value) : 캐시 이름 >> 캐시 그룹
     * condition : 조건부 캐싱
     * sync : 캐싱 동기화 -> thread safe하지 않을 때 사용
     * unless  : 캐싱이 이루어지지 않은 조건
     */

}
