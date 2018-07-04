/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
/**
 * Classe métier user
 * @author Manonegra
 */
package modele.beans;

public class User {

	private int id;
	private String login;
	private String password;
	private int nbConnexion;

	public User() {}

	/**
	 * @param id
	 * @param login
	 * @param password
	 * @param nbConnexion
	 */
	public User(int id, String login, String password, int nbConnexion) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.nbConnexion = nbConnexion;
	}

	public User(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the nbConnexion
	 */
	public int getNbConnexion() {
		return nbConnexion;
	}

	/**
	 * @param nbConnexion the nbConnexion to set
	 */
	public void setNbConnexion(int nbConnexion) {
		this.nbConnexion = nbConnexion;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "User : "+id+", login : "+login+", password : "+password+", nbConnexion : "+nbConnexion;
	}


}
