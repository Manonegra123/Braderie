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
package modele.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Caddie;
import modele.beans.User;
import modele.dao.exceptions.DAOException;
import modele.dao.factory.OracleDAOFactory;
import modele.dao.interfaces.IDao;
import modele.dao.utilitaires.DAOUtil;

/**
 * Classe de Dao du bean user
 * Cette classe implémente l'interface IDao
 * Elle sert a communiquer avec la base de donnée (oracle)
 * @author Manonegra
 *
 */
public class OracleUserDAO implements IDao<User>{

	// Connexion à la base de données
	Connection hconnection = null;

	// Sélection de tous les Users
	private final String QRY_FINDALL = "SELECT * FROM " + IDao.T_USER;

	// Sélection d'un User par son id
	private final String QRY_FINDBYID = "SELECT * FROM " + IDao.T_USER + " WHERE IDUSER = ";

	// Logger
	private static final Logger log = LogManager.getLogger(OracleUserDAO.class);


	public OracleUserDAO() {
		// Obtenir une connexion à la base de données
		hconnection = OracleDAOFactory.createConnection();

		log.info("Hash Code connexion : " + System.identityHashCode(hconnection));
	}

	/**
	 *
	 * @param login
	 * @param pwd
	 * @return un id, -1 si la procedure stockée IsValidLogon ne trouve pas de correspondance
	 * @throws DAOException si problème d'exécution de la requête ou si user non trouvé
	 */
	@Override
	public int findUser(String login, String pwd) throws DAOException {
		CallableStatement cs = null;

		// Id du user
		int nbre = 0;

		try {

			// Préparer la requête : création d'un objet CallableStatement à partir de la connexion
			cs = hconnection.prepareCall( "{? = call IsValidLogon(?,?) }" );

			// Positionner le type du paramètre de retour : 1er ?
			cs.registerOutParameter(1, Types.INTEGER);

			// Positionner le paramètre de la fonction stockée : 2ème ?
			cs.setString(2, login);
			cs.setString(3, pwd);

			// Exécution de la procédure stockée
			cs.execute();

			// Lecture du résultat = id du user
			nbre = cs.getInt(1);

			// Retourner le résultat
			return nbre;
		}
		catch (SQLException s) {
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
			return nbre;
		}
		finally
		{
			// Fermeture du callableStatement
			DAOUtil.closeCallableStatement(cs);
		}

	}

	/**
	 * Récupération d'un user par son identifiant
	 * @param id	identifiant de l'user à récupérer
	 * @throws DAOException si problème d'exécution de la requête ou si user non trouvé
	 */
	@Override
	public User findByID(int id) throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		User hUser = new User();
		String query = null;

		try {

			// Création du statement
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			// Construire la requête
			query = QRY_FINDBYID + id;

			// Exécuter la requête
			rs = st.executeQuery(query);

			// SI un enregistrement a été trouvé
			if (rs.first()){
				// Création de l'objet user
				hUser = new User(rs.getInt("iduser"),rs.getString("login"),rs.getString("pass"),rs.getInt("nbconnexion"));
			}
			// SINON aucun enregistrement trouvé
			else
				// Lever une exception DAOException
				throw new DAOException("Problème pendant la recherche par ID");

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("Problème pendant la recherche par ID", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closeStatement(st);
		}

		// Retourner l'objet user créé
		return hUser;
	}

	/**
	 * Recherche de tous les users
	 * @return Retourne une collection qui contient tous les users. La collection sera
	 *  vide s'il n'existe aucun user dans la base.
	 * @throws DAOException si problème d'exécution de la requête
	 */
	@Override
	public List<User> findAll() throws DAOException {
		//Creation d'une collection pour stocker les users
		List<User> lUsers = new ArrayList<>();

		Statement st = null;
		ResultSet rs  = null;
		User hUser = null;

		try
		{
			// Création d'un statement pour exécution de la requête
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Exécution de la requête.
			// Retourne un resultset de la forme:  iduser (PK), login, password, nbconnexion
			rs = st.executeQuery(QRY_FINDALL);

			// TANT QUE la fin du resultset n'est pas atteint
			while (rs.next()) {

				// Création d'un objet User
				hUser = new User();

				// Positionner les attributs de l'user à partir des valeurs contenues dans le resultset
				hUser.setId(rs.getInt(1));
				hUser.setLogin(rs.getString(2));
				hUser.setPassword(rs.getString(3));
				hUser.setNbConnexion(rs.getInt(4));

				// Ajout de l'objet User à la collection
				lUsers.add(hUser);

			} // FIN QUE la fin du resultset n'est pas atteint
		}
		catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("findAll <exception> :", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closeStatement(st);
		}

		// Retourner la collection d'objets User
		return lUsers;
	}

	@Override
	public User findLastArticle() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findFirstArticle() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findPrevArticle(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findNextArticle(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean sauveCaddie(Caddie caddie) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
