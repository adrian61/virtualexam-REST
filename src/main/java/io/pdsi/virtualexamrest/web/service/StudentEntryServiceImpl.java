package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.StudentEntryDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.entity.StudentEntry;
import io.pdsi.virtualexamrest.core.jpa.repository.StudentEntryRepository;
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
public class StudentEntryServiceImpl implements StudentEntryService {
	private final StudentEntryRepository studentEntryRepository;

	@Override
	public List<StudentEntryDto> getStudentEntriesDto() {
		return studentEntryRepository
				.findAll(Sort.by(Sort.Direction.ASC, "id"))
				.stream()
				.map(StudentEntryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public StudentEntryDto getStudentEntryDtoById(Integer id) throws IdNotFoundException {
		try {
			StudentEntryDto studentEntry = StudentEntryDto.fromEntity(Objects.requireNonNull(studentEntryRepository.findById(id).orElse(null)));
			return studentEntry;
		} catch (NullPointerException e) {
			throw new IdNotFoundException("StudentEntry with ID:" + id + "not found");
		}
	}

	@Override
	public boolean createStudentEntry(StudentEntryDto studentEntryDto) {
		StudentEntry studentEntry = studentEntryDto.toEntity();
		studentEntry.setId(0);
		try {
			studentEntryRepository.save(studentEntry);
			return true;
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			return false;
		}
	}

	@Override
	public void updateStudentEntry(StudentEntryDto studentEntry) throws IdNotFoundException, DataIntegrityViolationException {
		if (studentEntry.getId() == null) throw new IdNotFoundException("StudentEntry with ID:" + studentEntry.getId() + "not found");
		try {
			StudentEntry studentEntryIsExists = studentEntryRepository.getOne(studentEntry.getId());
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("StudentEntry with ID:" + studentEntry.getId() + "not found");
		}
		try {
			studentEntryRepository.save(studentEntry.toEntity());
		} catch (IllegalArgumentException | DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Couldn't save" + studentEntry.toString());
		}

	}

	@Override
	public void deleteStudentEntryById(Integer id) throws IdNotFoundException {
		try {
			StudentEntry studentEntryIsExists = studentEntryRepository.getOne(id);
		} catch (IllegalArgumentException e) {
			throw new IdNotFoundException("StudentEntry with ID:" + id + "not found");
		}
		try {
			studentEntryRepository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new IdNotFoundException("StudentEntry with ID:" + id + "not found");
		}

	}

	@Override
	public List<StudentEntryDto> getStudentEntryDtoByExamId(Exam exam) {
		return studentEntryRepository
				.getAllByExam(exam)
				.stream()
				.map(StudentEntryDto::fromEntity)
				.collect(Collectors.toList());
	}
}
