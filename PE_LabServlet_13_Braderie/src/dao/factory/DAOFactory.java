/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:09:15
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.factory;

import beans.Article;
import beans.User;
import dao.interfaces.IDao;

/**
 * Classe abstraite permettant de créer des dao
 * @author 31010-79-11
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