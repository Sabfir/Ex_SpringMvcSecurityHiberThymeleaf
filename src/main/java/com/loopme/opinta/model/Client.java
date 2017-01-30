package com.loopme.opinta.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Client {
	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	private String lastName;
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "phone_id")
	private Phone phone;
}
