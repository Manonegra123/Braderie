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
package modele.services.tests;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import modele.beans.User;
import modele.services.factory.ServiceFactory;
import modele.services.impl.Service;
import modele.services.impl.UserService;

/**
 * @author Manonegra
 *
 */
public class UserServiceTest {

	static Service<User> service;
	static User hUser = null;

	// Exécutée avant la première méthode de test
	@SuppressWarnings("unchecked")
	@BeforeClass
	public static void testBefore() throws Exception {
		System.out.println("BeforeClass : AVANT LES TESTS");

		service = ServiceFactory.newService(UserService.class);
		hUser = new User();

		System.out.println(service.toString());
	}

	// Exécutée après la dernière méthode de test
	@AfterClass
	public static void testAfter() throws Exception {
		System.out.println("AfterClass : APRES LES TESTS");
	}

	// Exécutée avant le test de chaque méthode
	@Before
	public void setBeforeTests() {
		System.out.println("Before : AVANT LE TEST");
	}

	// Exécutée après le test de chaque méthode
	@After
	public void setAfterTests() {
		System.out.println("After : APRES LE TEST");
	}

	// Test de la methode getListUser() de la classe UserService
	@Test
	public void getListUserTest() {

	}
}
