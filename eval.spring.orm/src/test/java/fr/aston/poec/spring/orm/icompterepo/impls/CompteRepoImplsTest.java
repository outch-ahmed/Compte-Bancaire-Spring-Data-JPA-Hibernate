package fr.aston.poec.spring.orm.icompterepo.impls;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Persistence;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fr.aston.poec.spring.orm.AppConfig;
import fr.aston.poec.spring.orm.domains.Adresse;
import fr.aston.poec.spring.orm.domains.Agence;
import fr.aston.poec.spring.orm.domains.Banque;
import fr.aston.poec.spring.orm.domains.Client;
import fr.aston.poec.spring.orm.domains.Compte;
import fr.aston.poec.spring.orm.domains.Type;
import fr.aston.poec.spring.orm.domains.TypeCompte;
import fr.aston.poec.spring.orm.icompterepo.ICompteRepository;
import fr.aston.poec.spring.orm.iservierepo.ICompteService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = AppConfig.class)
class CompteRepoImplsTest {

	@Autowired
	CompteRepoImpls repository;
	@Autowired
	ICompteService service;

	private void populate() {
		Compte compte1 = new Compte("001", BigDecimal.valueOf(1000.00), false);
		Client client1 = new Client("ouchaoua", "ahmed", "o.ahmed@aston.fr", "azert", LocalDate.of(1988, 6, 23));
		Adresse adresse1 = new Adresse("villa fleury", "93140", "bondy", "france");
		Agence agence1 = new Agence("agence 001", "guichet 87");
		Type type1 = new Type(TypeCompte.COURANT);
		Banque banque1 = new Banque("bnp", "15377");
		agence1.setAdresse(adresse1);
		banque1.setAdresse(adresse1);
		agence1.setBanque(banque1);
		client1.setAdresse(adresse1);
		compte1.setClient(client1);
		compte1.setType(type1);
		compte1.setAgence(agence1);
		
		
		Compte compte2 = new Compte("002", BigDecimal.valueOf(1200.32), false);
		Client client2 = new Client("morgan", "freman", "f.mogran@aston.fr", "quart", LocalDate.of(1955, 4, 1));
		Adresse adresse2 = new Adresse("jhonson street", "47855", "new york", "usa");
		Agence agence2 = new Agence("agence 145", "guichet 475");
		Type type2 = new Type(TypeCompte.EEPARGNE);
		Banque banque2 = new Banque("american express", "01524");
		agence2.setAdresse(adresse2);
		banque2.setAdresse(adresse2);
		agence2.setBanque(banque2);
		client2.setAdresse(adresse2);
		compte2.setClient(client2);
		compte2.setType(type2);
		compte2.setAgence(agence2);

		repository.insert(compte1);

		repository.insert(compte2);

	}

	@BeforeAll
	static void avantTousLesTest() {
		System.out.println("\n== Debut des test unitaires ==\n");
	}

	@BeforeEach
	void avantChaqueTest(TestInfo info) {
		System.out.println("\nDebut du test ==> " + info.getDisplayName() + "\n");
		Persistence.generateSchema("SPRING_HBM_PU", null);
		populate();
	}

	@AfterEach
	void tearDown(TestInfo info) {
		System.out.println("\nFin du test ==> " + info.getDisplayName() + "\n");
	}

	@Test
	void testInject() {
		assertNotNull(repository);
		assertNotNull(service);
	}

	@Test
	void testCrediter() {
		service.crediter(1, 200.);
	}

	@Test
	void testTransfer() {
		service.transfer(1, 2, 500.);
	}
//
//	@Test
//	void testGetCompte() {
//
//	}

	@AfterAll
	static void apresTousLesTest() {
		System.out.println("\n== Fin des test unitaires ==\n");
	}

}
