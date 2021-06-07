package fr.aston.poec.spring.orm.domains;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"nom", "codeGuichet"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Agence extends AbstractEntity{
	
	@NonNull
	@Column(length = 25)
	String nom;
	
	@NonNull
	@Column(length = 25)
	String codeGuichet;

	@ManyToOne(cascade = CascadeType.PERSIST)
	Banque banque;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	Adresse adresse;
	
	@OneToMany(mappedBy = "agence")
	List<Compte> comptes;
	
	public Agence() {

	}

	public Agence(String nom, String codeGuichet) {
		this.nom = nom;
		this.codeGuichet = codeGuichet;
	}
	@PrePersist
	public void beforePersistence()
	{
		setNom(JpaUtils.capitalize(nom));
	}
	
}
