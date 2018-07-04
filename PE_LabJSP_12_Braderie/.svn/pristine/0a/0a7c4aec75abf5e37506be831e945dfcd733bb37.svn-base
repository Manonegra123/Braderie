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

import controllers.commands.article.CurrentArticleCommand;
import controllers.commands.interfaces.ICommand;
import modele.beans.Caddie;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;

public class ViderCaddieCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(CurrentArticleCommand.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Caddie hcaddie = caddieService.createCaddie();

		// Récupération des attributs de session
		hcaddie = (Caddie) session.getAttribute("caddie");

		// Vidage du caddie
		hcaddie.getArticles().clear();

		session.setAttribute("caddie", hcaddie);
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/contenuCaddie.jsp").forward(request, response);
		else
			response.encodeURL("/WEB-INF/contenuCaddie.jsp");

	}

}
