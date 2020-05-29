package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExaminerDto;
import io.pdsi.virtualexamrest.core.jpa.repository.ExaminerRepository;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
	private final ExaminerRepository examinerRepository;

	@Override
	public ExaminerDto getExaminerById(Integer id) throws IdNotFoundException {
		ExaminerDto examiner = ExaminerDto.fromEntity(examinerRepository.findById(id).orElse(null));
		if (examiner == null) {
			throw new IdNotFoundException("Examiner with ID:" + id + "not found");
		} else {
			return examiner;
		}
	}
}
