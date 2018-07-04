/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
/**
 *
 */
package modele.dao.factory;

import modele.beans.Article;
import modele.beans.Caddie;
import modele.beans.User;
import modele.dao.interfaces.IDao;

/**
 * Classe abstraite permettant de créer des dao
 * @author Manonegra
 *
 */
//Classe abstraite DAO Factory
public abstract class DAOFactory {

	// Liste des types de DAO supportés par la fabrique abstraite
	public static final int ORACLE = 1;

	// Il doit y avoir une méthode pour chaque DAO qui peut être créé
	// Les fabriques concrètes doivent implémenter ces méthodes
	public abstract IDao<Article> getArticleDAO();
	public abstract IDao<User> getUserDAO();
	public abstract IDao<Caddie> getCaddieDAO();

	// Méthode qui permet de créer et de retourner la fabrique demandée
	public static DAOFactory getDAOFactory(int whichFactory) {
		switch (whichFactory) {
		case ORACLE :
			return new OracleDAOFactory();
		default :
			return null;
		}
	}
}