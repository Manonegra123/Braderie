/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:13:35
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.User;
import dao.exceptions.DAOException;
import dao.factory.OracleDAOFactory;
import dao.interfaces.IDao;
import dao.utilitaires.DAOUtil;

/**
 * Classe de Dao du bean user
 * Cette classe implémente l'interface IDao
 * Elle sert a communiquer avec la base de donnée (oracle)
 * @author 31010-79-11
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
	private static final Log log = LogFactory.getLog(OracleUserDAO.class);


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
	public int find(String login, String pwd) throws DAOException {
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

			System.out.println("find user_dao OK");
			// Retourner le résultat
			return nbre;
		}
		catch (SQLException s) {
			System.out.println("SQL Error: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
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
	public User findByID(long id) throws DAOException {
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
				hUser = new User(id,rs.getString("login"),rs.getString("password"),rs.getInt("nbconnexion"));
			}
			// SINON aucun enregistrement trouvé
			else
				// Lever une exception DAOException
				throw new DAOException("Problème pendant la recherche par ID");

		} catch (SQLException ex) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error(ex);

			// Lever une exception DAOException
			throw new DAOException("Problème pendant la recherche par ID", ex);
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
				hUser.setId(rs.getLong(1));
				hUser.setLogin(rs.getString(2));
				hUser.setPassword(rs.getString(3));
				hUser.setNbConnexion(rs.getInt(4));

				// Ajout de l'objet User à la collection
				lUsers.add(hUser);

			} // FIN QUE la fin du resultset n'est pas atteint
		}
		catch (SQLException ex) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error(ex);

			// Lever une exception DAOException
			throw new DAOException("findAll <exception> :", ex);
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

	/**
	 * Recherche des users répondant aux critères passés en paramètre
	 * @return Retourne une collection qui contient tous les users. La collection sera
	 * vide s'il n'existe aucun user dans la base de données.
	 * @throws DAOException si problème d'exécution de la requête
	 *
	 **/
	@Override
	public List<User> findByCriteria(User criteres) throws DAOException {
		// Création d'une collection pour stocker les users retournés par la méthode
		List<User> lUsers  = new ArrayList<>();

		Statement st = null;
		ResultSet rs = null;
		User hUser = null;

		try
		{
			// Création du statement
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Construire la réquête en fonction des critères contenu dans l'objet user
			String sql = "SELECT iduser,description,marque,prixunitaire from " + IDao.T_USER;
			sql = sql + " WHERE 1=1";
			if (criteres.getId() != 0)
				sql = sql + " AND IDUSER = '" + criteres.getId() + "'";
			if (criteres.getLogin() != null)
				sql = sql + " AND LOGIN like '" + criteres.getLogin() + "'";
			if (criteres.getPassword() != null)
				sql = sql + " AND PASSWORD like '" + criteres.getPassword() + "'";
			if (criteres.getNbConnexion() != 0)
				sql = sql + "AND NBCONNEXION = '" + criteres.getNbConnexion() + "'";

			// Exécution de la requête
			rs = st.executeQuery(sql);

			// TANT QUE la fin du resultset n'est pas atteinte
			while (rs.next()) {
				// Création d'un user
				hUser = new User();

				// Positionner les attributs de l'objet user avec les données du resultset
				hUser.setId(rs.getLong(1));
				hUser.setLogin(rs.getString(2));
				hUser.setPassword(rs.getString(3));
				hUser.setNbConnexion(rs.getInt(4));

				// Ajouter le user à la collection
				lUsers.add(hUser);
			}
		}
		catch (SQLException ex) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error(ex);

			// Lever une exception DAOException
			throw new DAOException("findByCriteria <exception> :", ex);
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
	public User findLastID() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User findFirstID() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
