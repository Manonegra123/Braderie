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
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.beans.Caddie;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

/**
 * Classe d'action pour la restauration du caddie de l'utilisateur
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="restoreCaddie")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="contenuCaddie.jsp")
public class RestoreCaddieAction extends ActionSupport implements SessionAware, RequestAware {

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(SaveCaddieAction.class);

	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	private Map<String, Object> request = null;	// RequestAware
	private Map<String, Object> session = null;	// SessionAware

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request = request;
	}


	@Override
	public String execute() throws ServletException, IOException {

		Caddie hCaddie = caddieService.createCaddie();
		int idCaddie;

		// Récuperation Caddie
		hCaddie = (Caddie) session.get("caddie");
		idCaddie = hCaddie.getId();
		try {
			hCaddie = caddieService.findById(idCaddie);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.put("caddie", hCaddie);
		request.put("message", "Le caddie a été restauré ");
		return SUCCESS;
	}

}
