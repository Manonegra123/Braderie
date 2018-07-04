/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
package modele.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;
import modele.dao.factory.OracleDAOFactory;
import modele.dao.interfaces.IDao;
import modele.dao.utilitaires.DAOUtil;

/**
 * Classe de Dao du bean caddie
 * Cette classe implémente l'interface IDao
 * Elle sert a communiquer avec la base de donnée (oracle)
 * @author Manonegra
 *
 */
public class OracleCaddieDAO implements IDao<Caddie>{

	// Logger
	private static final Logger log = LogManager.getLogger(OracleCaddieDAO.class);

	// Connexion à la base de données
	private Connection hconnection = null;

	// Selection d'un Caddie par son id
	private final String QRY_FINDBYID = "SELECT * FROM "+IDao.T_CADDIE+" WHERE IDCADDIE = ";

	// Creation 'un caddie
	private final String QRY_CREATE = "INSERT INTO  "+IDao.T_CADDIE+" VALUES (";

	// Constructeur
	public OracleCaddieDAO() {
		// Obtenir une connexion à la base de données
		hconnection = OracleDAOFactory.createConnection();

		log.info("Hash Code connexion : " + System.identityHashCode(hconnection));
	}

	/**
	 * Récupération d'un caddie par son identifiant
	 * @param id	identifiant du caddie à récupérer
	 * @throws DAOException si problème d'exécution de la requête ou si caddie non trouvé
	 */
	@Override
	public Caddie findByID(int id) throws DAOException {
		Caddie hCaddie = new Caddie();
		HashMap<Integer, Integer> hMapCaddie = new HashMap<>();
		Statement st = null;
		ResultSet rs  = null;
		String query;

		log.info("----------------------------------------------------------");
		log.info("findByID()");

		try {

			// Creation d'un statement pour executer la requete
			st = hconnection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);

			// Construction de la requete
			query = QRY_FINDBYID + id;

			// Execution de la requete et recuperation du resultat dans un resultset
			rs = st.executeQuery(query);

			// SI un enregistrement a ete retourne
			while (rs.next()) {
				//on remplit la hMapCaddie
				hMapCaddie.put(rs.getInt("IdArticle"), rs.getInt("Quantite"));
			}

			//on remplit le Caddie
			hCaddie.setId(id);
			hCaddie.setArticles(hMapCaddie);


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
		return hCaddie;
	}

	/**
	 * Suppression d'un Caddie a partir de son identifiant
	 * @return 0 si pas de suppression sinon 1
	 */
	public Boolean deleteCaddie(int idCaddie) throws DAOException {

		CallableStatement cs = null;
		Boolean result = false;

		log.info("----------------------------------------------------------");
		log.info("deleteCaddie()");

		try {

			// Création du statement pour exécution de la requête
			cs = hconnection.prepareCall( "{call DeleteCaddie(?) }" );
			cs.setInt(1, idCaddie);

			// Exécution de la requête  -  iret : nombre d'entregistrements modifiés
			result = cs.execute();
			log.info("result : " + result);

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("Problème sur le deleteCaddie ", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeCallableStatement(cs);

		}
		return result;
	}

	/**
	 * Creation d'un Caddie à partir de son identifiant
	 * @return 0 si pas de suppression sinon 1
	 */
	//--------------------------------------------------------------------------------

	@Override
	public Boolean sauveCaddie(Caddie caddie) throws DAOException {

		HashMap<Integer, Integer> hMapCaddie = null;
		CallableStatement cs = null;
		ResultSet rs  = null;
		Boolean result = false;
		int idArticle = 0;
		Integer quantite = 0;

		log.info("----------------------------------------------------------");
		log.info("sauveCaddie() avec CallableStatement");

		//d'abord on delete caddie existant
		log.info("delete caddie : id = " + caddie.getId());

		result = deleteCaddie(caddie.getId());

		log.info(result + " articles supprimés dans caddie " );

		log.info("----------------------------------------------------------");
		log.info("retour sauveCaddie() après DELETE");
		//puis on cree caddie

		try {
			// Creation d'un statement pour executer la requete
			cs = hconnection.prepareCall( "{call InsertArticleCaddie(?, ?, ?) }" );

			//on recupère id article et qté depuis hMapCaddie
			hMapCaddie = caddie.getArticles();
			log.info("hMapCaddie : " + hMapCaddie);

			Iterator iterator = hMapCaddie.entrySet().iterator();

			//on insere tous les articles dans la table T_CADDIE
			while (iterator.hasNext()) {
				//on recupère les données article
				Map.Entry entree = (Map.Entry) iterator.next();
				idArticle = (int) entree.getKey();
				quantite = (Integer) entree.getValue();

				//on fait mijoter la requete
				cs.setInt(1, caddie.getId());
				cs.setInt(2, idArticle);
				cs.setInt(3, quantite);
				log.info("on insere idArticle : " + idArticle + " qté : " + quantite);

				// Execution de la requete et recuperation du resultat dans un resultset
				result = cs.execute();
				log.info("result : " + result);
			}

		} catch (SQLException s) {
			// Enregistrement de l'erreur dans le fichier de journalisation (log)
			log.error("SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

			// Lever une exception DAOException
			throw new DAOException("Problème pendant la sauvegarde du caddie ", s);
		}
		finally {
			// Fermeture du resultset
			DAOUtil.closeResultSet(rs);

			// Fermeture du statement
			DAOUtil.closeCallableStatement(cs);
		}

		// Retourner l'objet article créé
		return result;
	}


	@Override
	public List<Caddie> findAll() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caddie findLastArticle() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caddie findFirstArticle() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caddie findPrevArticle(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Caddie findNextArticle(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int findUser(String login, String pwd) {
		// TODO Auto-generated method stub
		return 0;
	}

}
