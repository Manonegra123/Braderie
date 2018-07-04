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
package controllers.actions.article;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.beans.Article;
import modele.beans.Caddie;
import modele.beans.User;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

/**
 * Classe appelée par la front_controller par la balise action=current
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="currentArticle")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="article.jsp")
public class CurrentArticleAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(CurrentArticleAction.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);
	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private Article harticle;
	private Caddie hcaddie;
	private User user;

	/**
	 * @return the Article
	 */
	public Article getArticle() {
		return harticle;
	}

	/**
	 * @param harticle the Article to set
	 */
	public void setArticle(Article harticle) {
		this.harticle = harticle;
	}

	/**
	 * @return the Caddie
	 */
	public Caddie getCaddie() {
		return hcaddie;
	}

	/**
	 * @param hcaddie the Caddie to set
	 */
	public void setCaddie(Caddie hcaddie) {
		this.hcaddie = hcaddie;
	}

	/**
	 * @return the User
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the User to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// Variables pour l'interface SessionAware
	private Map<String, Object> session = null;	// SessionAware

	/*
	 * SUCCESS is returned when the action executed successfully and the corresponding result is displayed to the user.
	 */
	@Override
	public String execute() throws ServletException, IOException {
		log.debug("debut execute");
		int idArticle;
		int idUser;
		int qtite;
		int numArticle;

		// Récupération des attributs de session
		idArticle = (int) session.get("idArticle");
		idUser = (int) session.get("idUser");
		hcaddie = (Caddie) session.get("caddie");
		numArticle = (int) session.get("numArticle");
		log.debug(idArticle+"\n"+idUser+"\n"+hcaddie+"\n"+numArticle);
		// Récupération de l'article dont l'id est l'attribut de session idArticle
		//harticle.setId(idArticle);
		try {
			log.debug("CurrentArticleAction.execute()");
			harticle = articleService.findById(idArticle);
			user = userService.findById(idUser);
		} catch (ServiceException e) {
			log.error(e);
			throw new ServletException(e);
		}
		qtite = hcaddie.getQtite(idArticle);

		session.put("user", user);
		session.put("idUser", idUser);
		session.put("numArticle", numArticle);
		session.put("qtite", qtite);
		session.put("article", harticle);
		session.put("caddie", hcaddie);
		session.put("idArticle", harticle.getId());

		log.debug("return success");
		return SUCCESS;
	}
}
