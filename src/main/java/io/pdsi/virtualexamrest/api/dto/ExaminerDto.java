package io.pdsi.virtualexamrest.api.dto;

import io.pdsi.virtualexamrest.core.jpa.entity.Examiner;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;

@Builder
@Getter
public class ExaminerDto {
	private Integer id;
	@Size(min = 1)
	private String firstName;
	@Size(min = 1)
	private String lastName;

	public static ExaminerDto fromEntity(Examiner examiner) {
		return ExaminerDto.builder()
				.id(examiner.getId())
				.firstName(examiner.getFirstName())
				.lastName(examiner.getLastName())
				.build();
	}

	public Examiner toEntity() {
		Examiner examiner = Examiner.builder()
				.firstName(this.getFirstName())
				.lastName(this.getLastName())
				.build();
		examiner.setId(this.getId());
		return examiner;
	}

}
