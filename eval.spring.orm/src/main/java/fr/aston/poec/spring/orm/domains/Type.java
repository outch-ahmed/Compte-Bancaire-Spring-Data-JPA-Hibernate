package fr.aston.poec.spring.orm.domains;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"libelle"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Type extends AbstractEntity{

	@Enumerated(EnumType.STRING)
	TypeCompte libelle;

	public Type() {
	}

	public Type(TypeCompte libelle) {
		this.libelle = libelle;
	}	
}
