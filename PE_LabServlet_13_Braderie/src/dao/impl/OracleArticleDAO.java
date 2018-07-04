/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:13:54
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.Article;
import dao.exceptions.DAOException;
import dao.factory.OracleDAOFactory;
import dao.interfaces.IDao;
import dao.utilitaires.DAOUtil;

/**
 * Classe de Dao du bean article
 * Cette classe implémente l'interface IDao
 * Elle sert a communiquer avec la base de donnée (oracle)
 * @author 31010-79-11
 *
 */
public class OracleArticleDAO implements IDao<Article>{

	// Connexion à la base de données
	Connection hconnection = null;

	// Sélection de tous les Articles
	private final String QRY_FINDALL = "SELECT * FROM " + IDao.T_ARTICLE;

	// Sélection d'un Article par son id
	private final String QRY_FINDBYID = "SELECT * FROM " + IDao.T_ARTICLE + " WHERE IDARTICLE = ";

	// Sélection du dernier Article
	private final String QRY_LASTID = "SELECT * FROM " + IDao.T_ARTICLE +
			"	WHERE IDARTICLE = (SELECT MAX(IDARTICLE) FROM " + IDao.T_ARTICLE+")";

	// Sélection du dernier Article
	private final String QRY_FIRSTID = "SELECT * FROM " + IDao.T_ARTICLE +
			"	WHERE IDARTICLE = (SELECT MIN(IDARTICLE) FROM " + IDao.T_ARTICLE+")";

	// Logger
	private static final Log log = LogFactory.getLog(OracleArticleDAO.class);


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
	public Article findByID(long id) throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle = new Article();
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
				hArticle = new Article(id,rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
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
				hArticle.setId(rs.getLong(1));
				hArticle.setDescription(rs.getString(2));
				hArticle.setMarque(rs.getString(3));
				hArticle.setPrixunitaire(rs.getInt(4));

				// Ajout de l'objet Article à la collection
				lArticles.add(hArticle);

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

		// Retourner la collection d'objets Article
		return lArticles;
	}

	/**
	 * Recherche des articles répondant aux critères passés en paramètre
	 * @return Retourne une collection qui contient tous les articles. La collection sera
	 * vide s'il n'existe aucun article dans la base de données.
	 * @throws DAOException si problème d'exécution de la requête
	 *
	 **/
	@Override
	public List<Article> findByCriteria(Article criteres) throws DAOException {
		// Création d'une collection pour stocker les articles retournés par la méthode
		List<Article> lArticles  = new ArrayList<>();

		Statement st = null;
		ResultSet rs = null;
		Article hArticle = null;

		try
		{
			// Création du statement
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			// Construire la réquête en fonction des critères contenu dans l'objet article
			String sql = "SELECT idarticle,description,marque,prixunitaire from " + IDao.T_ARTICLE;
			sql = sql + " WHERE 1=1";
			if (criteres.getId() != 0)
				sql = sql + " AND IDARTICLE = '" + criteres.getId() + "'";
			if (criteres.getDescription() != null)
				sql = sql + " AND DESCRIPTION like '" + criteres.getDescription() + "'";
			if (criteres.getMarque() != null)
				sql = sql + " AND MARQUE like '" + criteres.getMarque() + "'";
			if (criteres.getPrixunitaire() != 0)
				sql = sql + "AND PRIXUNITAIRE = '" + criteres.getPrixunitaire() + "'";

			// Exécution de la requête
			rs = st.executeQuery(sql);

			// TANT QUE la fin du resultset n'est pas atteinte
			while (rs.next()) {
				// Création d'un article
				hArticle = new Article();

				// Positionner les attributs de l'objet article avec les données du resultset
				hArticle.setId(rs.getLong(1));
				hArticle.setDescription(rs.getString(2));
				hArticle.setMarque(rs.getString(3));
				hArticle.setPrixunitaire(rs.getInt(4));

				// Ajouter le user à la collection
				lArticles.add(hArticle);
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

		// Retourner la collection d'objets Article
		return lArticles;
	}

	@Override
	public int find(String obj1, String obj2) throws DAOException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Article findLastID() throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle = new Article();
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
				hArticle = new Article(rs.getLong("idarticle"),rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
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
		return hArticle;

	}

	@Override
	public Article findFirstID() throws DAOException {
		Statement st = null;
		ResultSet rs = null;
		Article hArticle = new Article();
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
				hArticle = new Article(rs.getLong("idarticle"),rs.getString("description"),rs.getString("marque"),rs.getInt("prixunitaire"));
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
		return hArticle;

	}

}
