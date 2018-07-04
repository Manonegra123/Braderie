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
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.ResultPath;
import org.apache.struts2.interceptor.RequestAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.beans.Caddie;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;

/**
 * Classe d'action pour la suppression d'un article du caddie
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="delArticle")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="contenuCaddie.jsp")
public class SupprimerArticleAction extends ActionSupport implements RequestAware, SessionAware{

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(SupprimerArticleAction.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	private Map<String, Object> session = null;	// SessionAware
	private Map<String, Object> request = null;	// SessionAware

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

		Caddie hcaddie = caddieService.createCaddie();
		int qtite;
		int idArticle;

		// Récupération des attributs de session
		idArticle = Integer.parseInt(ServletActionContext.getRequest().getParameter("idArticle"));
		qtite = Integer.parseInt(ServletActionContext.getRequest().getParameter("qtite"));
		hcaddie = (Caddie) session.get("caddie");

		// Suppression de l'article récupéré
		hcaddie.removeArticle(idArticle, qtite);

		session.put("caddie", hcaddie);
		return SUCCESS;
	}

}
