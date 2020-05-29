package io.pdsi.virtualexamrest.web.service;

import io.pdsi.virtualexamrest.api.dto.ExamDto;
import io.pdsi.virtualexamrest.api.dto.ExaminerDto;

public interface ExaminerService {
	ExaminerDto getExaminerById(Integer id);
}
