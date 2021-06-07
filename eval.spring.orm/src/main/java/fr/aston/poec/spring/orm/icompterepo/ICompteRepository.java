package fr.aston.poec.spring.orm.icompterepo;

import java.util.List;

import fr.aston.poec.spring.orm.domains.Compte;

public interface ICompteRepository {
	
	void delete(Integer id);
	
	List<Compte> find(boolean etat);

	Compte find(Integer id);
	
	List<Compte> find(List<Integer> liste);
	
	List<Compte> find(String numero);
	
	void insert(Compte compte);
	
	void update(Compte compte);
	
	void update(List<Compte> liste);
}
