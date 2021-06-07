package fr.aston.poec.spring.orm.iservie.impls;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.aston.poec.spring.orm.domains.Compte;
import fr.aston.poec.spring.orm.icompterepo.impls.CompteRepoImpls;
import fr.aston.poec.spring.orm.iservierepo.ICompteService;


@Service
public class CompteService implements ICompteService {

	@Autowired
	private CompteRepoImpls compteRepository;

	@Override
	public void crediter(Integer id, Double mantant) {
		Compte compte = compteRepository.find(id);
		if (mantant > 0) {
			
			compte.setSolde(compte.getSolde().add(BigDecimal.valueOf(mantant)));
			compteRepository.update(compte);
		}
	}

	@Override
	public void transfer(Integer idSource, Integer idTarget, Double mantant) {
		crediter(idTarget, mantant);
		Compte compte = compteRepository.find(idSource);
		if (mantant > 0) {
			compte.setSolde(compte.getSolde().subtract(BigDecimal.valueOf(mantant)));
			compteRepository.update(compte);
		}

	}

	@Override
	public Compte getCompte(Integer id) {
		return compteRepository.find(id);
	}

}
