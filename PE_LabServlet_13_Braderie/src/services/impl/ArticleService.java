/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 16:37:32
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package services.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import beans.Article;
import dao.exceptions.DAOException;
import dao.interfaces.IDao;
import services.exceptions.ServiceException;
import services.factory.ServiceFactory;

/**
 * Classe de Service du bean Article héritant de la classe Service
 * Elle définis toutes les méthodes de cette classe
 * Ces méthodes font appel a la couche DAO
 * @author 31010-79-11
 *
 */
public class ArticleService extends Service<Article> {

	// Créer un DAO Article à partir de la factory
	IDao<Article> articleDao = myFactory.getArticleDAO();

	// Logger
	static final private Log log = LogFactory.getLog(ArticleService.class);

	public ArticleService(Class<ServiceFactory> callerClass, String name){
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public Article findById(Long id) throws ServiceException {
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
	public List<Article> findByCriteria(Article critere) throws ServiceException {
		//Creation d'une collection pour stocker les articles
		List<Article> lArticles = null;

		try {
			lArticles = articleDao.findByCriteria(critere);
		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver les articles");
		}
		return lArticles;
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
			article = articleDao.findLastID();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return article;
	}

	public Article findFirstID() throws ServiceException {
		Article article = new Article();

		try {
			article = articleDao.findFirstID();

		} catch (DAOException e) {
			log.error(e);
			throw new ServiceException("Impossible de trouver l'article");
		}

		return article;
	}
}
