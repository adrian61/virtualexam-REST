package io.pdsi.virtualexamrest.web.controller;


import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import io.pdsi.virtualexamrest.web.service.ExamService;
import io.pdsi.virtualexamrest.web.service.StudentEntryService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class ExamController {
	private final ExamService examService;
	private final StudentEntryService studentEntryService;

	@GetMapping("/exams")
	@CrossOrigin
	public ResponseEntity<?> handleGetExams() {
		List<ExamDto> examList = examService.getExamsDto();
		return new ResponseEntity<>(examList, HttpStatus.OK);
	}

	@GetMapping("/exams/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetExamById(@PathVariable Integer id) {
		try {
			ExamDto examDto = examService.getExamDtoById(id);
			return new ResponseEntity<>(examDto, HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/exams")
	@CrossOrigin
	public ResponseEntity<?> handleCreateExam(@RequestBody ExamDto exam) {
		boolean isAdded = examService.createExam(exam);
		if (isAdded) return new ResponseEntity<>(HttpStatus.CREATED);
		else return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

	@PutMapping("/exams")
	@CrossOrigin
	public ResponseEntity<?> handleUpdateExam(@RequestBody ExamDto exam) {
		try {
			examService.updateExam(exam);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (DataIntegrityViolationException e) {
			return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@DeleteMapping("/exams/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleDeleteExam(@PathVariable Integer id) {
		try {
			examService.deleteExamById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/examItems/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetExamItems(@PathVariable Integer id) {
		try {
			ExamDto examDto = examService.getExamDtoById(id);
			Exam exam = examDto.toEntity();
			return new ResponseEntity<>(studentEntryService.getStudentEntryDtoByExamId(exam), HttpStatus.OK);
		} catch (IdNotFoundException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
