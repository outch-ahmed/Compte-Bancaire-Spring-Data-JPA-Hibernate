package fr.aston.poec.spring.orm.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import fr.aston.poec.spring.orm.domains.Compte;

public interface CompteRepository extends JpaRepository<Compte, Integer> {

}