package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;

import java.util.List;

public interface ExamService {
	List<ExamDto> getExams();

}
