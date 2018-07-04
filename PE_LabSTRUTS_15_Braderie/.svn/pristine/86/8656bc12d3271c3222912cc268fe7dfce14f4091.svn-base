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
package modele.services.impl;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.beans.Article;
import modele.dao.exceptions.DAOException;
import modele.dao.interfaces.IDao;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;

/**
 * Classe de Service du bean Article héritant de la classe Service
 * Elle définis toutes les méthodes de cette classe
 * Ces méthodes font appel a la couche DAO
 * @author Manonegra
 *
 */
public class ArticleService extends Service<Article> {

	// Créer un DAO Article à partir de la factory
	private IDao<Article> articleDao = myFactory.getArticleDAO();

	// Logger
	private static final Logger log = LogManager.getLogger(ArticleService.class);

	public ArticleService() {

	}
	public ArticleService(Class<ServiceFactory> callerClass, String name){
		this.name = name;
	}

	// Methode de creation d'un article
	public Article createArticle() {
		return new Article();
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Article findById(int id) throws ServiceException {
		Article article = new Article();

		try {
			article = articleDao.findByID(id);

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return article;

	}

	@Override
	public List<Article> findAll() throws ServiceException {
		//Creation d'une collection pour stocker les articles
		List<Article> lArticles = null;

		try {
			lArticles = articleDao.findAll();
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver les articles");
		}
		return lArticles;
	}

	public Article findLastID() throws ServiceException {
		Article article = new Article();

		try {
			article = articleDao.findLastArticle();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return article;
	}

	public Article findFirstID() throws ServiceException {
		Article article = new Article();

		try {
			article = articleDao.findFirstArticle();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return article;
	}

	public int findPrevID(int id) throws ServiceException {
		int idArticle;

		try {
			idArticle = articleDao.findPrevArticle(id).getId();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return idArticle;
	}

	public int findNextID(int id) throws ServiceException {
		int idArticle;

		try {
			idArticle = articleDao.findNextArticle(id).getId();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return idArticle;
	}
}
