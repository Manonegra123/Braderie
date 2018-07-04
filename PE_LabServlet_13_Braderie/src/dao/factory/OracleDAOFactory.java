/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:51:47
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import beans.Article;
import beans.User;
import dao.impl.OracleArticleDAO;
import dao.impl.OracleUserDAO;
import dao.interfaces.IDao;

/**
 * Classe de Dao qui hérite de la DAOFactory
 * Elle permet de créer une DAO qui communique avec une base oracle
 * @author 31010-79-11
 *
 */
public class OracleDAOFactory extends DAOFactory {

	private static DataSource ds;
	private static Connection myconnection = null;

	/**
	 * Obtenir une connexion
	 * @return connexion : instance de l'objet Connection
	 * @throws SQLException
	 */
	public static Connection createConnection(){

		try {
			// Obtenir le contexte de nommage et rechercher la data source
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/OracleDS");
			System.out.println(" --> OK");

			if (!isValidConnection()) {
				System.out.println("OUVERTURE D'UNE CONNEXION - createConnection ()");
				myconnection = ds.getConnection();
			}
			return myconnection;

		} catch (NamingException | SQLException e) {
			System.out.println(" --> PAS OK");
			e.printStackTrace();
			return myconnection;
		}

	}


	/**
	 * Fermer la connexion
	 * 	 @return true si connexion a été fermée sinon false
	 */
	@SuppressWarnings("finally")
	public static boolean cloreConnexion()
	{
		System.out.println("\nFERMETURE DE LA CONNEXION - cloreConnexion()\n");

		// Booléen retourné par la méthode
		boolean bClosed = false;

		try
		{
			// SI connexion ouverte
			if (isValidConnection()) {
				// Fermer la connexion
				myconnection.close();

				// Positionner le handle à NULL
				myconnection = null;

				// Retourner true pour indiquer que la connexion fermée
				bClosed = true;
			}
		}
		catch (SQLException ex) {
			System.out.println("Fermeture connexion impossible " + ex.getMessage());
		}
		finally{
			return bClosed;
		}
	}

	/**
	 * Verifier si la connexion est ouverte
	 * Utilisation de la méthode isValid() disponible avec jdbc4
	 * @return true si connexion ouverte sinon false
	 */
	public static boolean isValidConnection(){

		// SI handle vaut null ALORS connexion fermée SINON poursuivre traitement
		if (myconnection==null)
			return false;

		try{
			// SI connexion est fermée RETOURNER false SINON RETOURNER true
			// note : méthode isValid() existe depuis JDBC 4
			// isValid() teste la connexion en exécutant une requête
			// 10 secondes : temps d'attente
			if (myconnection.isValid(10))
				return true;
			else
				return false;
		}
		catch(SQLException ex){
			return false;
		}
		finally{
		}
	}

	/**
	 * Création d'un objet DAO Article pour Oracle
	 */
	@Override
	public IDao<Article> getArticleDAO() {
		// TODO Auto-generated method stub
		return new OracleArticleDAO();
	}

	/**
	 * Création d'un objet DAO User pour Oracle
	 */
	@Override
	public IDao<User> getUserDAO() {
		// TODO Auto-generated method stub
		return new OracleUserDAO();
	}
}