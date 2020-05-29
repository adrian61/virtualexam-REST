package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.repository.ExamRepository;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExamServiceImpl implements ExamService {
	private final ExamRepository examRepository;

	@Override
	public List<ExamDto> getExamsDto() {
		return examRepository
				.findAll(Sort.by(Sort.Direction.ASC, "id"))
				.stream()
				.map(ExamDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public ExamDto getExamDtoById(Integer id) throws IdNotFoundException {
		try {
			ExamDto exam = ExamDto.fromEntity(Objects.requireNonNull(examRepository.findById(id).orElse(null)));
			return exam;
		} catch (NullPointerException e) {
			throw new IdNotFoundException("Exam with ID:" + id + "not found");
		}
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

	@Override
	public void updateExam(ExamDto exam) throws IdNotFoundException, DataIntegrityViolationException {
		if (exam.getId() == null) throw new IdNotFoundException("Exam with ID:" + exam.getId() + "not found");
		try {
			Exam examIsExists = examRepository.getOne(exam.getId());
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("Exam with ID:" + exam.getId() + "not found");
		}
		try {
			examRepository.save(exam.toEntity());
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Couldn't save" + exam.toString());
		}

	}

	@Override
	public void deleteExamById(Integer id) throws IdNotFoundException {
		try {
			Exam examIsExists = examRepository.getOne(id);
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("Exam with ID:" + id + "not found");
		}
		try {
			examRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new IdNotFoundException("Exam with ID:" + id + "not found");
		}

	}
}
