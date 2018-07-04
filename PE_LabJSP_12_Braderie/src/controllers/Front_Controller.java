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
package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import controllers.commands.article.CurrentArticleCommand;
import controllers.commands.article.PrecedentCommand;
import controllers.commands.article.SuivantCommand;
import controllers.commands.authentification.LoginCommand;
import controllers.commands.authentification.LogoutCommand;
import controllers.commands.caddie.AjouterArticleCommand;
import controllers.commands.caddie.ContenuCaddieCommand;
import controllers.commands.caddie.RestoreCaddieCommand;
import controllers.commands.caddie.SaveCaddieCommand;
import controllers.commands.caddie.SupprimerArticleCommand;
import controllers.commands.caddie.ViderCaddieCommand;
import controllers.commands.exceptions.ErrorHandlerCommand;
import controllers.commands.interfaces.ICommand;

/**
 * Servlet implementation class Front_Controller
 * Servlet principal de l'application
 * Elle sert a aiguiller les diverses commandes de l'application
 * @author Manonegra
 */
@WebServlet(urlPatterns="/FC")
public class Front_Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// Collection de commandes
	// Remplie lors de la phase d'initialisation de la servlet
	private Map<String, ICommand> commands = new HashMap<>();

	@Override
	public void init(){
		// Ajouter les commandes à la collection de commandes
		commands.put("login",  new LoginCommand());
		commands.put("logout", new LogoutCommand());
		commands.put("contenu_caddie", new ContenuCaddieCommand());
		commands.put("suivant", new SuivantCommand());
		commands.put("precedent", new PrecedentCommand());
		commands.put("current", new CurrentArticleCommand());
		commands.put("ajouter", new AjouterArticleCommand());
		commands.put("vider", new ViderCaddieCommand());
		commands.put("supprimer", new SupprimerArticleCommand());
		commands.put("error", new ErrorHandlerCommand());
		commands.put("save", new SaveCaddieCommand());
		commands.put("restore", new RestoreCaddieCommand());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Front_Controller() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void processCommand(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException{

		HttpSession session = request.getSession();
		// Obtenir l'action à exécuter passée dans l'url (GET) ou dans le corps de la requête (POST)
		String formAction = request.getParameter("action");

		// Si pas d'action spécifiée, init par défaut
		if (formAction == null) {
			session.invalidate();
			formAction = "login";
		}

		// Récupérer la commande dans la table des commandes
		ICommand command = commands.get(formAction);

		// S'il n'existe pas de commande correspondante dans la table des commandes
		if(command == null){
			// Lever une exception
			throw new IllegalArgumentException("No command for form action : " + formAction);
		}

		// Exécuter la commande correspondante
		command.execute(request, response);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processCommand(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		processCommand(request, response);
	}

}
