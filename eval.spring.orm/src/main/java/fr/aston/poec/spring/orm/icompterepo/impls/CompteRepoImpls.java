package fr.aston.poec.spring.orm.icompterepo.impls;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import fr.aston.poec.spring.orm.domains.Compte;
import fr.aston.poec.spring.orm.icompterepo.ICompteRepository;
import fr.aston.poec.spring.orm.repositories.CompteRepository;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

@Repository
public class CompteRepoImpls implements ICompteRepository {

	@Autowired
	private CompteRepository compteRepo;
	
	@PersistenceContext
	private EntityManager em;
	
	@Override
	public void delete(Integer id) {
		compteRepo.deleteById(id);
		
	}

	@Override
	public List<Compte> find(boolean etat) {
		List<Compte> liste = new ArrayList<Compte>();
		TypedQuery<Compte> requete = em.createQuery("select a from Compte a where a.ferme = :etat", Compte.class);
		requete.setParameter("etat", Boolean.toString(etat));
		liste = requete.getResultList();
		return liste;
	}

	@Override
	public Compte find(Integer id) {
		return compteRepo.getById(id);
	}

	@Override
	public List<Compte> find(List<Integer> liste) {
		List<Compte> list = new ArrayList<Compte>();
		for(Integer id : liste)
		{
			list.add(compteRepo.getById(id));
		}
		return list;
	}

	@Override
	public List<Compte> find(String numero) {
		List<Compte> liste = new ArrayList<Compte>();
		TypedQuery<Compte> requete = em.createQuery("select a from Compte a where a.numero = :numero", Compte.class);
		requete.setParameter("numero", numero);
		liste = requete.getResultList();
		return liste;
	}

	@Override
	public void insert(Compte compte) {
		compteRepo.save(compte);
	}

	
	@Override
	public void update(Compte compte) {
		compteRepo.saveAndFlush(compte);
	}

	@Override
	public void update(List<Compte> liste) {
		for (Compte compte : liste)
		{
			update(compte);
		}
		
	}

}
