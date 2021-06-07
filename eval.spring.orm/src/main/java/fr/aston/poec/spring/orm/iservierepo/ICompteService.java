package fr.aston.poec.spring.orm.iservierepo;


import fr.aston.poec.spring.orm.domains.Compte;
import fr.aston.poec.spring.orm.repositories.CompteRepository;

public interface ICompteService {
	
	void crediter(Integer id, Double mantant);
	Compte getCompte(Integer id);
	void transfer (Integer idSource, Integer idTarget, Double mantant);
}
