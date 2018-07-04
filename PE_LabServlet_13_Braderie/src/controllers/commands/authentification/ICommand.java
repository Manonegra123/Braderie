/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 24 mai 2018
 * A : 12:24:02
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
/**
 *
 */
package controllers.commands.authentification;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Les objet commande héritent de l'interface command, qui est une classe avec
 * une méthode execute() qui n'est pas codée.
 * Pour chaque traitement que l'on veut effectuer, on va créer un objet commande
 * implémentant ICommand, dont on ira définir la méthode execute().
 * @author 31010-79-11
 *
 */
public interface ICommand {

	public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException;
}
