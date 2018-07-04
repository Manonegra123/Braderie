/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:37:08
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.factory;

import services.exceptions.ServiceFactoryException;
import services.impl.ArticleService;
import services.impl.Service;
import services.impl.UserService;

/**
 * Classe de factory de la couche service permettant de créer des services
 * @author 31010-79-11
 *
 */
public class ServiceFactory {
	// Méthode statique qui permet de créer la classe service demandée.
	// La classe service passée en paramètre doit hériter de la classe Service
	public static Service newService(Class<? extends Service> clazz){
		if (clazz.equals(ArticleService.class)){
			return new ArticleService(ServiceFactory.class, "Article service");
		}
		else
			if (clazz.equals(UserService.class)){
				return new UserService(ServiceFactory.class, "User service");
			}
		throw new ServiceFactoryException("Impossible de créer le service demandé.");
	}
}