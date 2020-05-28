package io.pdsi.virtualexamrest.web.controller;


import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.web.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/")
public class ExamController {
	private final ExamService examService;

	@GetMapping("/exams")
	@CrossOrigin
	public ResponseEntity<?> handleGetExams() {
		List<ExamDto> examList = examService.getExams();
		return new ResponseEntity<>(examList, HttpStatus.OK);
	}

	@GetMapping("/exams/{id}")
	@CrossOrigin
	public ResponseEntity<?> handleGetExams(@PathVariable Integer id) {
		try {
			ExamDto examDto = examService.getExamById(id);
			return new ResponseEntity<>(examDto, HttpStatus.OK);
		} catch (NullPointerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
