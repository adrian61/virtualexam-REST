package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
	private final ExamRepository examRepository;
	private final ExaminerService examinerService;

	@Override
	public List<ExamDto> getExams() {
		return examRepository
				.findAll(Sort.by(Sort.Direction.ASC, "id"))
				.stream()
				.map(ExamDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public ExamDto getExamById(Integer id) {
		return ExamDto.fromEntity(examRepository.findById(id).orElse(null));
	}

	@Override
	public boolean createExam(ExamDto examDto) {
		Exam exam = examDto.toEntity();
		exam.setId(0);
		try {
			examRepository.save(exam);
			return true;
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			return false;
		}
	}
}
