package io.simpolor.redis.repository;

import io.simpolor.redis.repository.entity.Student;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CustomStudentRepository {

    public static String PREFIX = "STUDENT:";
    private final RedisTemplate<String, Object> redisTemplate;

    public List<Student> findAll(){

        Set<String> keys = redisTemplate.keys(PREFIX + "*");
        if(CollectionUtils.isEmpty(keys)){
            return Collections.EMPTY_LIST;
        }

        List<Object> objects = redisTemplate.opsForValue().multiGet(keys);

        return objects.stream().map(Student.class::cast).collect(Collectors.toList());
    }

    public Optional<Student> findById(String key){

        Object o = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(o)) {
            return Optional.empty();
        }

        Student student = Student.class.cast(o);
        student.setStudentId(key);

        return Optional.ofNullable(student);
    }

    public Student save(Student student){

        if(StringUtils.isEmpty(student.getStudentId())){
            String key = PREFIX + RandomStringUtils.randomAlphanumeric(16);
            student.setStudentId(key);
        }

        redisTemplate.opsForValue().getAndSet(student.getStudentId(), student);

        /*
        redisTemplate.opsForValue().set(key, value, expirationTime, TimeUnit.SECONDS);
        redisTemplate.opsForValue().set(key, value);
        redisTemplate.hasKey(key);
        redisTemplate.expire(key, expirationTime, TimeUnit.SECONDS);
        redisTemplate.getExpire(key, TimeUnit.SECONDS);
        */

        return student;
    }

    public void deleteById(String key){

        redisTemplate.delete(key);
    }
}
