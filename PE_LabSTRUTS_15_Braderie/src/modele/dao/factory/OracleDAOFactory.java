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

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Article;
import modele.beans.Caddie;
import modele.beans.User;
import modele.dao.impl.OracleArticleDAO;
import modele.dao.impl.OracleCaddieDAO;
import modele.dao.impl.OracleUserDAO;
import modele.dao.interfaces.IDao;

/**
 * Classe de Dao qui hérite de la DAOFactory
 * Elle permet de créer une DAO qui communique avec une base oracle
 * @author Manonegra
 *
 */
public class OracleDAOFactory extends DAOFactory {

	private static DataSource ds;
	private static Connection myconnection = null;
	// Logger
	private static final Logger log = LogManager.getLogger(OracleUserDAO.class);

	/**
	 * Obtenir une connexion
	 * @return connexion : instance de l'objet Connection
	 * @throws SQLException
	 */
	public static Connection createConnection(){

		try {
			// Obtenir le contexte de nommage et rechercher la data source
			ds = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/OracleDS");

			if (!isValidConnection()) {
				myconnection = ds.getConnection();
			}
			return myconnection;

		} catch (NamingException | SQLException s) {
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getMessage());
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
		catch (SQLException s) {
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
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
		catch(SQLException s){
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

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


	@Override
	public IDao<Caddie> getCaddieDAO() {
		// TODO Auto-generated method stub
		return new OracleCaddieDAO();
	}
}