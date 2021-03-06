package com.loopme.opinta.model;

import com.loopme.opinta.enums.AppType;
import com.loopme.opinta.enums.ContentType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
import java.util.Set;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class App {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @Enumerated(EnumType.STRING)
    private AppType appType;
    @ElementCollection(fetch = FetchType.EAGER, targetClass = ContentType.class)
    @JoinTable(name = "content_type", joinColumns = @JoinColumn(name = "appId"))
    @Column(name = "interest", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<ContentType> contentTypes;
    @OneToOne
    private User user;
}
