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

import modele.beans.Caddie;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;

/**
 * Classe d'action pour l'ajout d'un article au caddie
 * @author Manonegra
 *
 */
@Namespace(value="/")
@Action(value="ajouterArticle")
@ResultPath(value="/WEB-INF/")
@Result(name="success", location="article.jsp")
public class AjouterArticleAction extends ActionSupport implements SessionAware {

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(AjouterArticleAction.class);

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
		int qtite;
		int idArticle;

		// Récupération des attributs de session
		idArticle = (int) session.get("idArticle");
		hcaddie = (Caddie) session.get("caddie");
		qtite = (int) session.get("qtite");

		// Ajout de l'article au caddie
		hcaddie.addArticle(idArticle, qtite);
		qtite = hcaddie.getQtite(idArticle);

		// Set de la nouvelle quantite
		session.put("qtite", qtite);
		return SUCCESS;

	}

}
