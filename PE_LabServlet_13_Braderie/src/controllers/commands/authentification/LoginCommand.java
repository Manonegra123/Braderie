/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @autd>or 31010-79-11
 * Date de creation : 24 mai 2018
 * A : 12:26:54
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package controllers.commands.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import services.exceptions.ServiceException;
import services.factory.ServiceFactory;
import services.impl.ArticleService;
import services.impl.UserService;

/**
 * Classe appelée par la front_controller par la balise action=login
 * @author 31010-79-11
 *
 */
public class LoginCommand implements ICommand {

	// Declaration des services
	private UserService userService = (UserService) ServiceFactory.newService(UserService.class);
	private ArticleService articleService = (ArticleService) ServiceFactory.newService(ArticleService.class);

	@Override
	public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

		// Stockage des parametres de la requete dans des chaines
		String login = request.getParameter("login");
		String pwd = request.getParameter("pwd");

		// Déclaration des id
		int idUser;
		long idArticle;
		try {
			// Récuperation des 2 chaines contenant le login et mot de passe
			// et passage en parametres de la méthode find de la classe UserService
			idUser = userService.find(login, pwd);

			// On positonne la variable idArticle sur le premier ID d'article trouvé dans la base de données
			idArticle = articleService.findFirstID().getId();

			// Et on la passe en parametre dans la methode findById
			articleService.findById(idArticle);
		} catch (ServiceException e) {
			throw new ServletException(e);
		}

		// Declaration d'un objet httpSession par la requete
		HttpSession session = request.getSession();

		// On positionne 2 attributs pour la session
		// L'id de l'user qui s'est connecté
		// et l'id du premier article trouvé
		session.setAttribute("idUser",idUser);
		session.setAttribute("idArticle", idArticle);

		// Consolidation en cas d'identifiants non valides,
		// on renvoie directement à la page d'accueil
		if(idUser != -1)
			// forward vers la commande current du front_controller
			request.getRequestDispatcher("/Front_Controller?action=current").forward(request, response);
		else
			request.getRequestDispatcher("/index.html").forward(request, response);

	}
}
