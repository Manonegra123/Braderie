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
package controllers.actions.authentification;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.beans.Caddie;
import modele.beans.User;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;
/**
 * Classe appelée par la front_controller par la balise action=login
 * @author Manonegra
 *
 */
@Namespace("/")
@Action(value="login")
@Results({
	@Result(name="success", location="currentArticle", type="redirectAction"),
	@Result(name="error", location="/WEB-INF/index.jsp")})
public class LoginAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(LoginAction.class);

	// Declaration des services
	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);
	private User user;

	// GETTERS & SETTERS
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// Variables pour l'interface SessionAware
	private Map<String, Object> session = null;	// SessionAware

	@Override
	public String execute()throws ServletException, IOException{

		// Stockage des parametres de la requete dans des chaines
		String login = user.getLogin();
		String password = user.getPassword();

		// Déclaration des id
		int idUser;
		int idArticle;
		int numArticle = 1;

		// Creation d'un caddie au log d'un nouvel utilisateur
		Caddie hcaddie = caddieService.createCaddie();
		try {
			// Récuperation des 2 chaines contenant le login et mot de passe
			// et passage en parametres de la méthode find de la classe UserService
			idUser = userService.find(login, password);

			// On positonne la variable idArticle sur le premier ID d'article trouvé dans la base de données
			idArticle = articleService.findFirstID().getId();

			// Et on la passe en parametre dans la methode findById
			articleService.findById(idArticle);
		} catch (ServiceException e) {
			log.error(e);
			throw new ServletException(e);
		}

		hcaddie.setId(idUser);

		/* On positionne 3 attributs pour la session
		 * id de l'user qui s'est connecté
		 * id du premier article trouvé
		 * caddie crée précédemment
		 **/
		session.put("numArticle", numArticle);
		session.put("idUser",idUser);
		session.put("idArticle", idArticle);
		session.put("caddie", hcaddie);

		// Consolidation en cas d'identifiants non valides,
		// on renvoie directement à la page d'accueil
		if(idUser != -1) {
			log.debug("return success");
			return SUCCESS;
		}else {
			log.debug("return error");
			addActionError(getText("error.login"));
			return ERROR;
		}

	}

}
