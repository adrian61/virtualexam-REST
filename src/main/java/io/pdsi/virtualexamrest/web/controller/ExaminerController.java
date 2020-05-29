package io.pdsi.virtualexamrest.web.controller;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.api.dto.ExaminerDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.entity.Examiner;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import io.pdsi.virtualexamrest.web.service.ExamService;
import io.pdsi.virtualexamrest.web.service.ExaminerService;
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
public class ExaminerController {
	private final ExaminerService examinerService;
	private final ExamService examService;

	@GetMapping("/examiners")
	@CrossOrigin
	public ResponseEntity<?> handleGetExaminers() {
		List<ExaminerDto> examinerList = examinerService.getExaminersDto();
		return new ResponseEntity<>(examinerList, HttpStatus.OK);
	}

	@GetMapping("/examiners/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetExaminerById(@PathVariable Integer id) {
		try {
			ExaminerDto examinerDto = examinerService.getExaminerDtoById(id);
			return new ResponseEntity<>(examinerDto, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/examiners")
	@CrossOrigin
	public ResponseEntity<?> handleCreateExaminer(@RequestBody @Valid ExaminerDto examiner) {
		boolean isAdded = examinerService.createExaminer(examiner);
		if (isAdded) return new ResponseEntity<>(HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/examiners")
	@CrossOrigin
	public ResponseEntity<?> handleUpdateExaminer(@RequestBody @Valid ExaminerDto examiner) {
		try {
			examinerService.updateExaminer(examiner);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/examiners/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleDeleteExaminer(@PathVariable Integer id) {
		try {
			examinerService.deleteExaminerById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/examinerItems/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetExaminerItems(@PathVariable Integer id) {
		try {
			ExaminerDto examinerDto = examinerService.getExaminerDtoById(id);
			Examiner examiner = examinerDto.toEntity();
			return new ResponseEntity<>(examService.getExamDtoByExaminerId(examiner), HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
