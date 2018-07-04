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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Article;
import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;
import modele.dao.factory.OracleDAOFactory;
import modele.dao.interfaces.IDao;
import modele.dao.utilitaires.DAOUtil;


/**
 * Classe de Dao du bean article
 * Cette classe implémente l'interface IDao
 * Elle sert a communiquer avec la base de donnée (oracle)
 * @author Manonegra
 *
 */
public class OracleArticleDAO implements IDao<Article>{

	// Connexion à la base de données
	private Connection hconnection = null;

	// Sélection de tous les Articles
	private final String QRY_FINDALL = "SELECT * FROM " + IDao.T_ARTICLE;

	// Sélection d'un Article par son id
	private final String QRY_FINDBYID = "SELECT * FROM " + IDao.T_ARTICLE + " WHERE IDARTICLE = ";

	// Sélection du dernier Article
	private final String QRY_LASTID = "SELECT * FROM " + IDao.T_ARTICLE +
			"	WHERE IDARTICLE = (SELECT MAX(IDARTICLE) FROM " + IDao.T_ARTICLE+")";

	// Sélection du premier Article
	private final String QRY_FIRSTID = "SELECT * FROM " + IDao.T_ARTICLE +
			"	WHERE IDARTICLE = (SELECT MIN(IDARTICLE) FROM " + IDao.T_ARTICLE+")";

	// Sélection de l'article précedent
	private final String QRY_FIND_PREVIOUSARTICLE = "SELECT IDARTICLE"
			+ " FROM (SELECT IDARTICLE, ROWNUM AS RNUM FROM (SELECT IDARTICLE"
			+ " FROM " + IDao.T_ARTICLE + " WHERE IDARTICLE < ? ORDER BY ROWNUM desc) WHERE ROWNUM = 1)"
			+ " WHERE RNUM = 1";

	// Sélection de l'article suivant
	private final String QRY_FIND_NEXTARTICLE = "SELECT IDARTICLE"
			+ " FROM (SELECT IDARTICLE, ROWNUM AS RNUM FROM (SELECT IDARTICLE"
			+ " FROM " + IDao.T_ARTICLE + " WHERE IDARTICLE > ? ORDER BY IDARTICLE) WHERE ROWNUM = 1)"
			+ " WHERE RNUM = 1";

	// Logger
	private static final Logger log = LogManager.getLogger(OracleArticleDAO.class);


	public OracleArticleDAO() {
		// Obtenir une connexion à la base de données
		hconnection = OracleDAOFactory.createConnection();

		log.info("Hash Code connexion : " + System.identityHashCode(hconnection));
	}

	/**
	 * Récupération d'un article par son identifiant
	 * @param id	identifiant de l'article à récupérer
	 * @throws DAOException si problème d'exécution de la requête ou si article non trouvé
	 */
	@Override
	public Article findByID(int id) throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle;
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
				// Création de l'objet article
				hArticle = new Article(rs.getInt("idarticle"),rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
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

		// Retourner l'objet article créé
		return hArticle;
	}

	/**
	 * Recherche de tous les articles
	 * @return Retourne une collection qui contient tous les articles. La collection sera
	 *  vide s'il n'existe aucun article dans la base.
	 * @throws DAOException si problème d'exécution de la requête
	 */
	@Override
	public List<Article> findAll() throws DAOException {
		//Creation d'une collection pour stocker les articles
		List<Article> lArticles = new ArrayList<>();

		Statement st = null;
		ResultSet rs  = null;
		Article hArticle = null;

		try
		{
			// Création d'un statement pour exécution de la requête
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Exécution de la requête.
			// Retourne un resultset de la forme:  idarticle (PK), description, marque, prixunitaire
			rs = st.executeQuery(QRY_FINDALL);

			// TANT QUE la fin du resultset n'est pas atteint
			while (rs.next()) {

				// Création d'un objet Article
				hArticle = new Article();

				// Positionner les attributs de l'article à partir des valeurs contenues dans le resultset
				hArticle.setId(rs.getInt(1));
				hArticle.setDescription(rs.getString(2));
				hArticle.setMarque(rs.getString(3));
				hArticle.setPrixunitaire(rs.getInt(4));

				// Ajout de l'objet Article à la collection
				lArticles.add(hArticle);

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

		// Retourner la collection d'objets Article
		return lArticles;
	}

	@Override
	public Article findLastArticle() throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle;
		String query = null;

		try {

			// Création du statement
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			// Construire la requête
			query = QRY_LASTID;

			// Exécuter la requête
			rs = st.executeQuery(query);

			// SI un enregistrement a été trouvé
			if (rs.first()){
				// Création de l'objet article
				hArticle = new Article(rs.getInt("idarticle"),rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
			}
			// SINON aucun enregistrement trouvé
			else
				// Lever une exception DAOException
				throw new DAOException("Problème pendant la recherche par ID");

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("Problème pendant la recherche last ID", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closeStatement(st);
		}
		return hArticle;

	}

	@Override
	public Article findFirstArticle() throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle;
		String query = null;

		try {

			// Création du statement
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);

			// Construire la requête
			query = QRY_FIRSTID;

			// Exécuter la requête
			rs = st.executeQuery(query);

			// SI un enregistrement a été trouvé
			if (rs.first()){
				// Création de l'objet article
				hArticle = new Article(rs.getInt("idarticle"),rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
			}
			// SINON aucun enregistrement trouvé
			else
				// Lever une exception DAOException
				throw new DAOException("Problème pendant la recherche par ID");

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("Problème pendant la recherche du 1st ID", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closeStatement(st);
		}
		return hArticle;

	}

	@Override
	@SuppressWarnings("resource")
	public Article findPrevArticle(int id) throws DAOException {
		Article hArticle;
		ResultSet rs  = null;
		PreparedStatement ps = null;
		int next_id = 0;

		try {

			// Création d'un statement pour exécuter la requête
			ps = hconnection.prepareStatement(QRY_FIND_PREVIOUSARTICLE);
			ps.setInt(1, id);

			// Exécuter la requête
			rs = ps.executeQuery();

			// Si un enregistrement a été retourné
			if (rs.next())
				// Récupérer l'id
				next_id = rs.getInt(1);
			// SINON pas d'enregistrement retourné
			else {
				// Création d'un prepare statement pour exécuter la requête
				ps = hconnection.prepareStatement(QRY_LASTID);

				// Exécuter la requête : recherche de l'article avec le plus grand ID
				rs = ps.executeQuery();

				// Si un enregistrement a été retourné
				if (rs.next())
					// Récupérer l'id
					next_id = rs.getInt(1);
			}

			// Récupérer l'article correspondant à l'id
			hArticle = findByID(next_id);

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
			// Lever une exception DAOException
			throw new DAOException("findPrec <exception> ");
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closePreparedStatement(ps);

		}

		// Retourner l'article créé
		return hArticle;
	}

	@Override
	@SuppressWarnings("resource")
	public Article findNextArticle(int id) throws DAOException {
		Article hArticle;
		ResultSet rs  = null;
		PreparedStatement ps = null;
		int next_id = 0;

		try {

			// Création d'un statement pour exécuter la requête
			ps = hconnection.prepareStatement(QRY_FIND_NEXTARTICLE);
			ps.setInt(1, id);

			// Exécuter la requête
			rs = ps.executeQuery();

			// Si un enregistrement a été retourné
			if (rs.next())
				// Récupérer l'id
				next_id = rs.getInt(1);
			// SINON pas d'enregistrement retourné
			else {
				// Création d'un prepare statement pour exécuter la requête
				ps = hconnection.prepareStatement(QRY_FIRSTID);

				// Exécuter la requête : recherche de l'article avec le plus grand ID
				rs = ps.executeQuery();

				// Si un enregistrement a été retourné
				if (rs.next())
					// Récupérer l'id
					next_id = rs.getInt(1);
			}

			// Récupérer l'article correspondant à l'id
			hArticle = findByID(next_id);

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
			// Lever une exception DAOException
			throw new DAOException("findPrec <exception> ");
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closePreparedStatement(ps);

		}

		// Retourner l'article créé
		return hArticle;
	}

	@Override
	public int findUser(String login, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Boolean sauveCaddie(Caddie caddie) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

}
