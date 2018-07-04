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
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import controllers.actions.article.CurrentArticleAction;
import modele.beans.Caddie;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;

/**
 * Classe d'action pour la supression de tout le contenu du caddie de l'utilisateur
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="viderCaddie")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="contenuCaddie.jsp")
public class ViderCaddieAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(CurrentArticleAction.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	private Map<String, Object> session = null;	// SessionAware

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public String execute() throws ServletException, IOException {
		Caddie hcaddie = caddieService.createCaddie();

		// Récupération des attributs de session
		hcaddie = (Caddie) session.get("caddie");

		// Vidage du caddie
		hcaddie.getArticles().clear();

		session.put("caddie", hcaddie);
		return SUCCESS;

	}

}
