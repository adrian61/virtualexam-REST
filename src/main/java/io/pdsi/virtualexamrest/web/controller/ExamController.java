package io.pdsi.virtualexamrest.web.controller;


import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.web.service.ExamService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
