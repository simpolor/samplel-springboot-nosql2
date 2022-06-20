
package io.simpolor.redis.controller;

import io.simpolor.redis.model.ResultDto;
import io.simpolor.redis.model.StudentDto;
import io.simpolor.redis.repository.entity.Student;
import io.simpolor.redis.service.CustomStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/custom/students")
@RequiredArgsConstructor
public class CustomStuentController {

	private final CustomStudentService customStudentService;

	@GetMapping
	public List<StudentDto> list() {

		return StudentDto.of(customStudentService.getAll());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable String studentId) {

		Student student = customStudentService.get(studentId);

		return StudentDto.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto studentDto) {

		Student student = customStudentService.create(studentDto.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable String studentId,
					   @RequestBody StudentDto studentDto) {

		studentDto.setId(studentId);
		customStudentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable String studentId) {

		customStudentService.delete(studentId);
	}

	/*
	@RequestMapping(value="/not", method=RequestMethod.GET)
	public String studentNot() {

		return "Is not a student";
	}*/


}
