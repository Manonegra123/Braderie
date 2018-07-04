/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 15:10:32
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.User;
import dao.exceptions.DAOException;
import dao.factory.DAOFactory;
import dao.interfaces.IDao;

/**
 * Classe de test de la classe UserDAO
 * Ne fonctionne pas avec une datasource
 * @author 31010-79-11
 *
 */
public class UserDAOTest {
	static IDao<User> userDao;
	List<User> usersList;
	User myUser;

	// Exécutée avant la première méthode de test
	@BeforeClass
	public static void testBefore() throws Exception {
		System.out.println("BeforeClass : AVANT LES TESTS");

		// Créer la DAO Factory pour Oracle
		DAOFactory myFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);

		// Créer un DAO User à partir de la factory
		userDao = myFactory.getUserDAO();
	}

	// Exécutée après la dernière méthode de test
	@AfterClass
	public static void testAfter() throws Exception {
		System.out.println(" afterClass : APRES LES TESTS");
	}

	// Exécutée avant le test de chaque méthode
	@Before
	public void setBeforeTests() {
		System.out.println("Before : AVANT LE TEST");

		// Vérifier que l'objet UserDAO n'est pas null
		assertNotNull(userDao);
	}

	// Exécutée après le test de chaque méthode
	@After
	public void setAfterTests() {
		System.out.println("After : APRES LE TEST");

		userDao = null;
	}

	// Définir un temps max pour l'exécution d'un test (long délai en ms)
	@Test(timeout=10000)
	public void testFinalize() {
		//fail("Non implémenté actuellement");
		System.out.println("Test avec timeout  : TEST TIMEOUT");
	}

	@Test
	public void testFindAll()  {

		System.out.println("\n testFindAll() : LISTE DE TOUS LES USERS");
		// Sélection de tous les users
		try {
			usersList = userDao.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Vérifier si il existe 4 users
		assertEquals(4, usersList.size());

		/* for (int i=0; i < usersList.size();i++) {
			myUser = usersList.get(i);
			System.out.println("NOM : " + myUser.getNom() + "\t\tCODE POSTAL : " + myUser.getCodepostal());
		} */
	}

	@Test
	public void testFindById() {
		try {
			myUser = userDao.findByID(1);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Pas d'assertion
		assertEquals("Milesd", myUser.getLogin());

		// Assertion
		//assertEquals("Marleyb", myUser.getLogin());
	}

	// Tester si la méthode lève l'exception DAOException
	@Test(expected=DAOException.class)
	public void testDAOException() throws DAOException {

		// Recherche d'une user qui n'existe pas
		myUser = userDao.findByID(100);
	}

}
