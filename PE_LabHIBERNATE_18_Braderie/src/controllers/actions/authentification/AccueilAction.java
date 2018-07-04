/*******************************************************************************
 * @author Manonegra
 * Date de creation : 27 juin 2018
 * A : 13:36:51
 *
 * PE_LabHIBERNATE_18_Braderie
 ******************************************************************************/
package controllers.actions.authentification;

import java.io.IOException;

import javax.servlet.ServletException;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;

import com.opensymphony.xwork2.ActionSupport;

@Namespace("/")
@Action(value="accueil")
@Result(name="success", location="/WEB-INF/index.jsp")
public class AccueilAction  extends ActionSupport{

	private static final long serialVersionUID = 1L;

	@Override
	public String execute()throws ServletException, IOException{
		return SUCCESS;
	}

}
