/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package modele.dao.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.beans.Article;
import modele.dao.exceptions.DAOException;
import modele.dao.factory.DAOFactory;
import modele.dao.interfaces.IDao;

/**
 * Classe de test de la classe ArticleDAO
 * Ne fonctionne pas avec une datasource
 * @author Manonegra
 *
 */
public class ArticleDAOTest {

	static IDao<Article> articleDao;
	List<Article> articlesList;
	Article myArticle;

	// Exécutée avant la première méthode de test
	@BeforeClass
	public static void testBefore() throws Exception {
		System.out.println("BeforeClass : AVANT LES TESTS");

		// Créer la DAO Factory pour Oracle
		DAOFactory myFactory = DAOFactory.getDAOFactory(DAOFactory.ORACLE);

		// Créer un DAO Article à partir de la factory
		articleDao = myFactory.getArticleDAO();
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

		// Vérifier que l'objet ArticleDAO n'est pas null
		assertNotNull(articleDao);
	}

	// Exécutée après le test de chaque méthode
	@After
	public void setAfterTests() {
		System.out.println("After : APRES LE TEST");

		articleDao = null;
	}

	// Définir un temps max pour l'exécution d'un test (long délai en ms)
	@Test(timeout=10000)
	public void testFinalize() {
		//fail("Non implémenté actuellement");
		System.out.println("Test avec timeout  : TEST TIMEOUT");
	}

	@Test
	public void testFindAll()  {

		System.out.println("\n testFindAll() : LISTE DE TOUS LES ARTICLES");
		// Sélection de tous les articles
		try {
			articlesList = articleDao.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Vérifier si il existe 4 articles
		assertEquals(4, articlesList.size());

		/* for (int i=0; i < articlesList.size();i++) {
			myArticle = articlesList.get(i);
			System.out.println("NOM : " + myArticle.getNom() + "\t\tCODE POSTAL : " + myArticle.getCodepostal());
		} */
	}

	@Test
	public void testFindById() {
		try {
			myArticle = articleDao.findByID(1);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Pas d'assertion
		assertEquals("CASTORAMA", myArticle.getMarque());

		// Assertion
		//assertEquals("HARIBO", myArticle.getMarque());
	}

	// Tester si la méthode lève l'exception DAOException
	@Test(expected=DAOException.class)
	public void testDAOException() throws DAOException {

		// Recherche d'une article qui n'existe pas
		myArticle = articleDao.findByID(100);
	}

}
