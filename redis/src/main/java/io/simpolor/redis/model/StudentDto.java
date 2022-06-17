package io.simpolor.redis.model;

import io.simpolor.redis.repository.entity.Student;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
public class StudentDto {

	private String id;
	private String name;
	private Integer grade;
	private Integer age;

	public Student toEntity(){

		Student student = new Student();
		student.setId(this.id);
		student.setName(this.name);
		student.setGrade(this.grade);
		student.setAge(this.age);

		return student;
	}

	public static StudentDto of(Student student){

		StudentDto studentDto = new StudentDto();
		studentDto.setId(student.getId());
		studentDto.setName(student.getName());
		studentDto.setGrade(student.getGrade());
		studentDto.setAge(student.getAge());

		return studentDto;
	}

	public static List<StudentDto> of(List<Student> students){

		return students.stream()
				.map(StudentDto::of)
				.collect(Collectors.toList());
	}
}
