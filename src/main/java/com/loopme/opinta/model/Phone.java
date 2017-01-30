package com.loopme.opinta.model;

//import com.fasterxml.jackson.annotation.JsonIgnore;
import com.loopme.opinta.enums.PhoneType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString(exclude = {"clients"})
public class Phone {
	@Id
	@GeneratedValue
	private Integer id;
	private String number;
	@Enumerated(EnumType.STRING)
	private PhoneType type;
	private String description;
//	@JsonIgnore
	@OneToMany(mappedBy = "phone")
	private List<Client> clients;
}
