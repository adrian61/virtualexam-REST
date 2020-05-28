package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExaminerDto;
import io.pdsi.virtualexamrest.core.jpa.repository.ExaminerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
	private final ExaminerRepository examinerRepository;

	@Override
	public ExaminerDto getExaminerById(Integer id) {
		return ExaminerDto.fromEntity(examinerRepository.findById(id).orElse(null));
	}
}
