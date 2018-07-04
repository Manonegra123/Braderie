/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:39:48
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import beans.User;
import services.factory.ServiceFactory;
import services.impl.Service;
import services.impl.UserService;

/**
 * @author 31010-79-11
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
