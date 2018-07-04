/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
package controllers.actions.caddie;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.convention.annotation.Results;
import org.apache.struts2.interceptor.ApplicationAware;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

/**
 * Classe d'action pour la sauvegarde du caddie dans la bdd
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="saveCaddie")
@ResultPath(value="/WEB-INF/")
@Results({
	@Result(name="success", location="contenuCaddie.jsp"),
	@Result(name="error", location="error.jsp")})
public class SaveCaddieAction extends ActionSupport implements RequestAware, SessionAware, ApplicationAware {

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(SaveCaddieAction.class);

	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	private Map<String, Object> request = null;	// RequestAware
	private Map<String, Object> session = null;	// SessionAware
	private Map<String, Object> context = null;	// ApplicationAware


	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}
	@Override
	public void setApplication(Map<String, Object> context) {
		this.context = context;
	}


	@Override
	public String execute() throws ServletException, IOException {

		Boolean test = false;
		Caddie hCaddie = caddieService.createCaddie();


		// Récuperation user et Caddie
		hCaddie = (Caddie) session.get("caddie");

		// Appel du Service pour sauvegarder
		try {
			caddieService.sauveCaddie(hCaddie);
			test = false;
		} catch (DAOException e) {
			log.error("erreur DAO : " + e);
			test = true;
		}

		// Retour affichage page caddie
		if (test == false) {
			request.put("message", "Le caddie a été sauvegardé ");
			return SUCCESS;
		}
		// Ou error
		else {
			return ERROR;
		}

	}

}
