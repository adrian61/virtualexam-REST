package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.StudentEntryDto;
import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import io.pdsi.virtualexamrest.core.jpa.repository.StudentEntryRepository;
import io.pdsi.virtualexamrest.web.exception.IdNotFoundException;
import lombok.RequiredArgsConstructor;
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
	public List<StudentEntryDto> getStudentEntries() {
		return studentEntryRepository
				.findAll(Sort.by(Sort.Direction.ASC, "id"))
				.stream()
				.map(StudentEntryDto::fromEntity)
				.collect(Collectors.toList());
	}

	@Override
	public StudentEntryDto getStudentEntryById(Integer id) {
		try {
			StudentEntryDto studentEntryDto = StudentEntryDto.fromEntity(Objects.requireNonNull(studentEntryRepository.findById(id).orElse(null)));
			return studentEntryDto;
		} catch (NullPointerException e) {
			throw new IdNotFoundException("Student entry with ID:" + id + "not found");
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
