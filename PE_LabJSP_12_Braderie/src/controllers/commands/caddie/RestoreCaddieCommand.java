/*******************************************************************************
 * @author  Manonegra
 * Date de creation : 8 juin 2018
 * A : 16:33:02
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
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
import modele.services.exceptions.ServiceException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

public class RestoreCaddieCommand implements ICommand {

	// Logger
	private final static Log log = LogFactory.getLog(SaveCaddieCommand.class);

	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		Caddie hCaddie = caddieService.createCaddie();
		int idCaddie;

		// Récuperation Caddie
		hCaddie = (Caddie) session.getAttribute("caddie");
		idCaddie = hCaddie.getId();
		try {
			hCaddie = caddieService.findById(idCaddie);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		session.setAttribute("caddie", hCaddie);
		request.setAttribute("message", "Le caddie a été restauré ");
		if(request.isRequestedSessionIdFromCookie())
			request.getRequestDispatcher("/WEB-INF/contenuCaddie.jsp").forward(request, response);
		else
			response.encodeURL("/WEB-INF/contenuCaddie.jsp");
	}

}
