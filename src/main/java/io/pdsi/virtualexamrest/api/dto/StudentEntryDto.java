package io.pdsi.virtualexamrest.api.dto;

import io.pdsi.virtualexamrest.core.jpa.entity.StudentEntry;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Size;
import java.time.ZonedDateTime;

@Builder
@Getter
public class StudentEntryDto {
	private Integer id;
	@Size(min = 1)
	private String firstName;
	@Size(min = 1)
	private String lastName;
	private ZonedDateTime finishDate;


	public static StudentEntryDto fromEntity(StudentEntry studentEntry) {
		return StudentEntryDto.builder()
				.id(studentEntry.getId())
				.firstName(studentEntry.getFirstName())
				.lastName(studentEntry.getLastName())
				.finishDate(studentEntry.getFinishDate())
				.build();
	}

	public StudentEntry toEntity() {
		StudentEntry studentEntry = StudentEntry.builder()
				.firstName(this.firstName)
				.lastName(this.lastName)
				.finishDate(this.finishDate)
				.build();
		studentEntry.setIndex(this.id);
		return studentEntry;
	}
}
