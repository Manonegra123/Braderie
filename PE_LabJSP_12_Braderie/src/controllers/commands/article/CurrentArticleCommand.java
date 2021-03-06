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
import modele.beans.Article;
import modele.beans.Caddie;
import modele.beans.User;
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

/**
 * Classe appelée par la front_controller par la balise action=current
 * @author Manonegra
 *
 */
public class CurrentArticleCommand implements ICommand{

	// Logger
	private static final Log log = LogFactory.getLog(CurrentArticleCommand.class);

	// Declaration des services
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);
	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Creation variables
		Article harticle = new Article();
		Caddie hcaddie = caddieService.createCaddie();
		User user;
		HttpSession session = request.getSession();
		int idArticle;
		int idUser;
		int qtite;
		int numArticle;

		// Récupération des attributs de session
		idArticle = (int) session.getAttribute("idArticle");
		idUser = (int) session.getAttribute("idUser");
		hcaddie = (Caddie) session.getAttribute("caddie");
		numArticle = (int) session.getAttribute("numArticle");

		// Récupération de l'article dont l'id est l'attribut de session idArticle
		harticle.setId(idArticle);
		try {
			harticle = articleService.findById(idArticle);
			user = userService.findById(idUser);
		} catch (ServiceException e) {
			log.error(e);
			log.info(e);
			throw new ServletException(e);
		}
		qtite = hcaddie.getQtite(idArticle);

		session.setAttribute("user", user);
		session.setAttribute("idUser", idUser);
		session.setAttribute("numArticle", numArticle);
		session.setAttribute("qtite", qtite);
		session.setAttribute("article", harticle);
		session.setAttribute("caddie", hcaddie);
		session.setAttribute("idArticle", harticle.getId());
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/article.jsp").forward(request, response);
		else
			response.encodeURL("/WEB-INF/article.jsp");

	}
}
