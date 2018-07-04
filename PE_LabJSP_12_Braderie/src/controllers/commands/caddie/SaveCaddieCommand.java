/*******************************************************************************
 * @author Manonegra
 * Date de creation : 8 juin 2018
 * A : 10:20:43
 *
 * PE_LabJSP_12_Braderie
 ******************************************************************************/
package controllers.commands.caddie;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import controllers.commands.interfaces.ICommand;
import modele.beans.Caddie;
import modele.dao.exceptions.DAOException;
import modele.services.factory.ServiceFactory;
import modele.services.impl.ArticleService;
import modele.services.impl.CaddieService;
import modele.services.impl.UserService;

/**
 * Classe appelée par la front_controller par la balise action=save
 * Elle permet à l'utilisateur d'enregistrer son caddie dans la base
 * @author Manonegra
 *
 */
public class SaveCaddieCommand implements ICommand {

	// Logger
	private final static Log log = LogFactory.getLog(SaveCaddieCommand.class);

	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);
	private CaddieService caddieService = (CaddieService) ServiceFactory.newService(CaddieService.class);

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Boolean test = false;
		Caddie hCaddie = caddieService.createCaddie();

		// Déclarations variables de session et de contexte
		ServletContext context = request.getSession().getServletContext();
		HttpSession session = request.getSession();

		// Récuperation user et Caddie
		hCaddie = (Caddie) session.getAttribute("caddie");

		// Appel du Service pour sauvegarder
		try {
			caddieService.sauveCaddie(hCaddie);
			test = false;
		} catch (DAOException e) {
			log.error("erreur DAO : " + e);
			test = true;
		}

		// Retour affichage page caddie
		if (test == false) {
			request.setAttribute("message", "Le caddie a été sauvegardé ");
			context.getRequestDispatcher("/WEB-INF/contenuCaddie.jsp").forward(request, response);
		}
		// Ou error
		else {
			context.getRequestDispatcher("/FC?action=error").forward(request, response);
		}

	}

}
