package fr.aston.poec.spring.orm.domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrePersist;

import lombok.*;
import lombok.experimental.FieldDefaults;


@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"voie", "codePostal", "ville"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Adresse extends AbstractEntity{
	@NonNull
	@Column(length = 25)
	String voie;
	
	@NonNull
	@Column(length = 25)
	String codePostal;
	
	@NonNull
	@Column(length = 25)
	String ville;
	
	@NonNull
	@Column(length = 25)
	String pays;

	public Adresse() {

	}

	public Adresse(String voie, String codePostal, String ville, String pays) {
		this.voie = voie;
		this.codePostal = codePostal;
		this.ville = ville;
		this.pays = pays;
	}
	@PrePersist
	public void beforePersistence()
	{
		setVoie(JpaUtils.capitalize(voie));
		setVille(JpaUtils.capitalize(ville));
		setPays(JpaUtils.capitalize(pays));
		
	}
}
