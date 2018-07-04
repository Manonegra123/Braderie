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
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;

/**
 * Classe appelée par la front_controller par la balise action=suivant
 * @author Manonegra
 *
 */
@Namespace("/")
@Action(value="suivant")
@Result(name="success", location="currentArticle", type="redirectAction")
public class SuivantAction extends ActionSupport implements SessionAware{

	private static final long serialVersionUID = 1L;

	// Logger
	private static final Logger log = LogManager.getLogger(SuivantAction.class);

	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	// Variables pour l'interface SessionAware
	private Map<String, Object> session = null;	// SessionAware

	@Override
	public String execute() throws ServletException, IOException {

		int idArticle = (int) session.get("idArticle");
		int numArticle = (int) session.get("numArticle");
		try {
			// On met en place le carroussel avec le if else qui va controler
			// si l'id est bien dans les bornes de la bases de donnÃ©e
			if(idArticle < articleService.findLastID().getId()) {
				idArticle = articleService.findNextID(idArticle);
				numArticle++;
			}
			else {
				idArticle = articleService.findFirstID().getId();
				numArticle = 1;
			}

		} catch (ServiceException e) {
			log.error(e);
			log.info(e);
			throw new ServletException(e);
		}
		// Redirection vers la page current_article avec l'article suivant
		session.put("numArticle", numArticle);
		session.put("idArticle",idArticle);
		return SUCCESS;

	}



}
