package fr.aston.poec.spring.orm.domains;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"nom", "code"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Banque extends AbstractEntity{
	@NonNull
	@Column(length = 25)
	String nom;
	
	@NonNull
	@Column(length = 25)
	String code;
	
	@OneToOne(cascade = CascadeType.PERSIST)
	Adresse adresse;
	
	@OneToMany(mappedBy = "banque")
	List<Agence> agences;
	
	public Banque() {
	}

	public Banque(String nom, String code) {
		this.nom = nom;
		this.code = code;
	}
	@PrePersist
	public void beforePersistence()
	{
		setNom(JpaUtils.capitalize(nom));
	}

}

