package io.pdsi.virtualexamrest.core.jpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.time.ZonedDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Builder
@Table(name = "exams", schema = "public")
public class Exam extends BaseEntity {
	@Column(name = "title")
	private String title;

	@Column(name = "password")
	private String password;

	@Column(name = "start_date")
	private ZonedDateTime startDate;

	@Column(name = "end_date")
	private ZonedDateTime endDate;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinColumn(name = "examiner_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Examiner examinerId;
}
