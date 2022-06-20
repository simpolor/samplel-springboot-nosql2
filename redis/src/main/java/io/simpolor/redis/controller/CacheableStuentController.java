
package io.simpolor.redis.controller;

import io.simpolor.redis.model.ResultDto;
import io.simpolor.redis.model.StudentDto;
import io.simpolor.redis.repository.entity.Student;
import io.simpolor.redis.service.CacheableStudentService;
import io.simpolor.redis.service.CustomStudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/cacheable/students")
@RequiredArgsConstructor
public class CacheableStuentController {

	private final CacheableStudentService cacheableStudentService;

	@GetMapping
	public List<StudentDto> list() {

		return StudentDto.of(cacheableStudentService.getAll());
	}

	@GetMapping(value="/{studentId}")
	public StudentDto detail(@PathVariable String studentId) {

		Student student = cacheableStudentService.get(studentId);

		return StudentDto.of(student);
	}

	@PostMapping
	public ResultDto register(@RequestBody StudentDto studentDto) {

		Student student = cacheableStudentService.create(studentDto.toEntity());

		return ResultDto.of(student.getStudentId());
	}

	@PutMapping(value="/{studentId}")
	public void modify(@PathVariable String studentId,
					   @RequestBody StudentDto studentDto) {

		studentDto.setId(studentId);
		cacheableStudentService.update(studentDto.toEntity());
	}

	@DeleteMapping(value="/{studentId}")
	public void delete(@PathVariable String studentId) {

		cacheableStudentService.delete(studentId);
	}

}
