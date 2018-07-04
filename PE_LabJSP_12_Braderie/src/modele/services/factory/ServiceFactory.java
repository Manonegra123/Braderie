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
package modele.services.factory;

import modele.services.exceptions.ServiceFactoryException;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.Service;
import modele.services.impl.UserService;

/**
 * Classe de factory de la couche service permettant de créer des services
 * @author Manonegra
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
			else
				if (clazz.equals(CaddieService.class)) {
					return new CaddieService(ServiceFactory.class, "Caddie service");
				}
		throw new ServiceFactoryException("Impossible de créer le service demandé.");
	}
}