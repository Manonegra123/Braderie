/*******************************************************************************
 * @author Manonegra
 * Date de creation : 7 juin 2018
 * A : 12:03:09
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
/**
 *
 */
package controllers.commands.article;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import controllers.commands.interfaces.ICommand;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;

/**
 * Classe appelée par la front_controller par la balise action=suivant
 * @author Manonegra
 *
 */
public class SuivantCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(SuivantCommand.class);

	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// RÃ©cupÃ©ration de l'id de l'article passÃ© en attribut de session
		HttpSession session = request.getSession();
		int idArticle = (int) session.getAttribute("idArticle");
		int numArticle = (int) session.getAttribute("numArticle");
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
		session.setAttribute("numArticle", numArticle);
		session.setAttribute("idArticle",idArticle);
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/FC?action=current").forward(request, response);
		else
			response.encodeURL("/FC?action=current");

	}



}
