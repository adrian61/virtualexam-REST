package io.pdsi.virtualexamrest.web.controller;

import io.pdsi.virtualexamrest.api.dto.StudentEntryDto;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import io.pdsi.virtualexamrest.web.service.StudentEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentEntryController {
	private final StudentEntryService studentEntryService;

	@GetMapping("/studentEntries")
	@CrossOrigin
	public ResponseEntity<?> handleGetStudentEntries() {
		System.out.println("hello");
		List<StudentEntryDto> examList = studentEntryService.getStudentEntries();
		return new ResponseEntity<>(examList, HttpStatus.OK);
	}

	@GetMapping("/studentEntries/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetStudentEntriesById(@PathVariable("id") Integer id) {
		try {
			StudentEntryDto studentEntryDto = studentEntryService.getStudentEntryById(id);
			return new ResponseEntity<>(studentEntryDto, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
