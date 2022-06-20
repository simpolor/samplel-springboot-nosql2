package io.simpolor.redis.repository;

import io.simpolor.redis.repository.entity.Student;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<Student, String> {

}
