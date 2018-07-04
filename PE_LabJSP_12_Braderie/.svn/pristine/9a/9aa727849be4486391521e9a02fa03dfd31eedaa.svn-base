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
package controllers.commands.interfaces;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Les objet commande héritent de l'interface command, qui est une classe avec
 * une méthode execute() qui n'est pas codée.
 * Pour chaque traitement que l'on veut effectuer, on va créer un objet commande
 * implémentant ICommand, dont on ira définir la méthode execute().
 * @author Manonegra
 *
 */
public interface ICommand {

	// Logger
	public static final Log log = LogFactory.getLog(ICommand.class);

	public void execute(HttpServletRequest request,HttpServletResponse response)throws ServletException, IOException;
}
