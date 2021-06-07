package fr.aston.poec.spring.orm.domains;


import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"numero"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Compte extends AbstractEntity{
	@NonNull
	@Column(length = 50)
	String numero;
	
	@NonNull
	BigDecimal solde;
	
	@NonNull
	boolean ferme;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	Client client;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	Agence agence;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	Type type;

	public Compte(String numero, BigDecimal solde, boolean ferme) {
		this.numero = numero;
		this.solde = solde;
	}
	
	public Compte() {

	}
	
}

