package io.pdsi.virtualexamrest.api.dto;

import io.pdsi.virtualexamrest.core.jpa.entity.Exam;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Builder
@Getter
public class ExamDto {
	private Integer id;
	@Size(min = 1)
	private String title;
	private ZonedDateTime startDate;
	private ZonedDateTime endDate;

	public static ExamDto fromEntity(Exam exam) {
		return ExamDto.builder()
				.id(exam.getId())
				.title(exam.getTitle())
				.startDate(exam.getStartDate())
				.endDate(exam.getEndDate())
				.build();
	}

	public Exam toEntity() {
		Exam exam = Exam.builder()
				.title(this.getTitle())
				.startDate(this.getStartDate())
				.endDate(this.getEndDate())
				.build();
		exam.setId(this.getId());
		return exam;
	}
}
