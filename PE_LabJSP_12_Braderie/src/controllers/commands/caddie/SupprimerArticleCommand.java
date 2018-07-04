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
package controllers.commands.caddie;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import controllers.commands.interfaces.ICommand;
import modele.beans.Caddie;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;

public class SupprimerArticleCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(SupprimerArticleCommand.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Caddie hcaddie = caddieService.createCaddie();
		int qtite;
		int idArticle;

		// Récupération des attributs de session
		idArticle = Integer.parseInt(request.getParameter("idArticle"));
		qtite = Integer.parseInt(request.getParameter("qtite"));
		hcaddie = (Caddie) session.getAttribute("caddie");

		// Suppression de l'article récupéré
		hcaddie.removeArticle(idArticle, qtite);

		session.setAttribute("caddie", hcaddie);
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/contenuCaddie.jsp").forward(request, response);
		else
			response.encodeURL("/WEB-INF/contenuCaddie.jsp");
	}

}
