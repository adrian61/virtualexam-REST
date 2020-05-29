package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExaminerDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Examiner;
import io.pdsi.virtualexamrest.core.jpa.repository.ExaminerRepository;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExaminerServiceImpl implements ExaminerService {
	private final ExaminerRepository examinerRepository;

	@Override
	public List<ExaminerDto> getExaminersDto() {
		return examinerRepository
				.findAll(Sort.by(Sort.Direction.ASC, "id"))
				.stream()
				.map(ExaminerDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public ExaminerDto getExaminerDtoById(Integer id) throws IdNotFoundException {
		try {
			ExaminerDto examiner = ExaminerDto.fromEntity(examinerRepository.findById(id).orElse(null));
			return examiner;
		} catch (NullPointerException e) {
			throw new IdNotFoundException("Examiner with ID:" + id + "not found");
		}
	}

	@Override
	public boolean createExaminer(ExaminerDto examinerDto) {
		Examiner examiner = examinerDto.toEntity();
		examiner.setId(0);
		try {
			examinerRepository.save(examiner);
			return true;
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			return false;
		}
	}

	@Override
	public void updateExaminer(ExaminerDto examiner) throws IdNotFoundException, DataIntegrityViolationException {
		if (examiner.getId() == null)
			throw new IdNotFoundException("Examiner with ID:" + examiner.getId() + "not found");
		try {
			Examiner examinerIsExists = examinerRepository.getOne(examiner.getId());
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("Examiner with ID:" + examiner.getId() + "not found");
		}
		try {
			examinerRepository.save(examiner.toEntity());
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Couldn't save" + examiner.toString());
		}

	}

	@Override
	public void deleteExaminerById(Integer id) throws IdNotFoundException {
		try {
			Examiner examinerIsExists = examinerRepository.getOne(id);
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("Examiner with ID:" + id + "not found");
		}
		try {
			examinerRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new IdNotFoundException("Examiner with ID:" + id + "not found");
		}

	}
}
