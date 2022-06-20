package io.simpolor.redis.repository.entity;

import lombok.*;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

// @RedisHash(value = "student", timeToLive = 30)
@RedisHash(value = "student")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {

	@Id
	private String studentId;

	private String name;
	private Integer grade;
	private Integer age;
}
