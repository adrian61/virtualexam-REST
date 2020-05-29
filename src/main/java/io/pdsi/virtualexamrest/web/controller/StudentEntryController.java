package io.pdsi.virtualexamrest.web.controller;

import io.pdsi.virtualexamrest.api.dto.StudentEntryDto;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import io.pdsi.virtualexamrest.web.service.StudentEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class StudentEntryController {
	private final StudentEntryService studentEntryService;

	@GetMapping("/studentEntries")
	@CrossOrigin
	public ResponseEntity<?> handleGetStudentEntries() {
		List<StudentEntryDto> studentEntryList = studentEntryService.getStudentEntriesDto();
		return new ResponseEntity<>(studentEntryList, HttpStatus.OK);
	}

	@GetMapping("/studentEntries/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetStudentEntryById(@PathVariable Integer id) {
		try {
			StudentEntryDto studentEntryDto = studentEntryService.getStudentEntryDtoById(id);
			return new ResponseEntity<>(studentEntryDto, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/studentEntries")
	@CrossOrigin
	public ResponseEntity<?> handleCreateStudentEntry(@RequestBody @Valid StudentEntryDto studentEntry) {
		boolean isAdded = studentEntryService.createStudentEntry(studentEntry);
		if (isAdded) return new ResponseEntity<>(HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/studentEntries")
	@CrossOrigin
	public ResponseEntity<?> handleUpdateStudentEntry(@RequestBody @Valid StudentEntryDto studentEntry) {
		try {
			studentEntryService.updateStudentEntry(studentEntry);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/studentEntries/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleDeleteStudentEntry(@PathVariable Integer id) {
		try {
			studentEntryService.deleteStudentEntryById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
