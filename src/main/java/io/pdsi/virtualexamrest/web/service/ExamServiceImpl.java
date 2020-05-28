package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.core.jpa.repository.ExamRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
	private final ExamRepository examRepository;

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
}
