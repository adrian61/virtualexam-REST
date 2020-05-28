package io.pdsi.virtualexamrest.core.jpa.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(callSuper = true)
@Table(name = "examiner", schema = "public")
public class Examiner extends BaseEntity {
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	//Setter added for tests
	@Setter
	@Column(name = "login")
	private String login;

	@Setter
	@Column(name = "password")
	private String password;

	@Column(name = "role")
	private String role;

}
