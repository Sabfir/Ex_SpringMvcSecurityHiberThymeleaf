//package com.loopme.opinta.model;
//
//import lombok.Getter;
//import lombok.Setter;
//import lombok.ToString;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.ManyToMany;
//import javax.persistence.Table;
//import java.util.Set;
//
//@Entity
//@Table(name = "roles")
//@Getter
//@Setter
//@ToString
//public class Role {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//
//    @Column(name = "name")
//    private String name;
//
//    @ManyToMany(mappedBy = "roles")
//    private Set<User> users;
//}
