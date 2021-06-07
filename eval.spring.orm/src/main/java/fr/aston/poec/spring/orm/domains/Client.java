package fr.aston.poec.spring.orm.domains;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = {"nom", "prenom", "ddn"}, callSuper = true)
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString
public class Client extends AbstractEntity {

	@NonNull
	@Column(length = 20)
	String nom;

	@NonNull
	@Column(length = 20)
	String prenom;

	@NonNull
	@Column(length = 30)
	String email;

	@NonNull
	@Column(length = 30)
	String mdp;

	@NonNull
	LocalDate ddn;

	@Transient
	int age;

	@OneToOne(cascade = CascadeType.PERSIST)
	Adresse adresse;

	@OneToMany(mappedBy = "client")
	List<Compte> comptes;

	public Client(String nom, String prenom, String email, String mdp, LocalDate ddn) {
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.mdp = mdp;
		this.ddn = ddn;
	}

	public Client() {

	}
	@PrePersist
	private void beforePersistence()
	{
		setNom(nom.toUpperCase());
		setPrenom(JpaUtils.capitalize(prenom));
	}
	@PostLoad
	private void postLoad() {
        age = JpaUtils.calculeAge(ddn);
    }
}
