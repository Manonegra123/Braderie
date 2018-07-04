/*******************************************************************************
 * @author Manonegra
 * Date de creation : 14 juin 2018
 * A : 09:50:54
 *
 * PE_LabSTRUTS_15_Braderie
 ******************************************************************************/
/**
 *
 */
package modele.dao.utilitaires;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import modele.dao.exceptions.DAOException;

/**
 * Classe utilitaire dÃ©finissant les mÃ©thodes pour fermer les diffÃ©rents statements utilisÃ© dans les autres classes
 * @author Manonegra
 *
 */
public class DAOUtil {
	// Logger
	private static final Logger log = LogManager.getLogger(DAOUtil.class);

	// Constructeur en private -> pas d'instanciation
	private  DAOUtil (){}

	/**
	 * Fermeture d'un Statement
	 * @param stmt
	 * @throws DAOException si problÃ¨me lors de la fermeture du statement
	 */
	public static void closeStatement(final Statement stmt) throws DAOException
	{
		// SI statement existe
		if (stmt != null){
			try{
				// Fermeture du statement
				stmt.close();
			}
			catch (SQLException s) {
				log.error(stmt + "SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());

				throw new DAOException("closeStatement <exception> :", s);
			}
		}
	}

	/**
	 * Fermeture d'un ResultSet
	 * @param rs : resultset Ã  fermer
	 * @throws DAOException si problÃ¨me lors de la fermeture du resultset
	 */
	public static void closeResultSet(final ResultSet rs) throws DAOException
	{
		// SI ResultSet existe
		if (rs != null){
			try{
				// Fermeture du ResultSet
				rs.close();
			}
			catch (SQLException s){
				log.error(rs + "SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
				throw new DAOException("closeResultSet <exception> :", s);
			}
		}
	}

	/**
	 * Fermeture d'un PreparedStatement
	 * @param ps : PreparedStatement Ã  fermer
	 * @throws DAOException si problÃ¨me lors de la fermeture du PreparedStatement
	 */
	public static void closePreparedStatement(final PreparedStatement ps) throws DAOException
	{
		// SI PreparedStatement existe
		if (ps != null){
			try{
				// Fermeture du PreparedStatement
				ps.close();
			}
			catch (SQLException s){
				log.error(ps+ "SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
				throw new DAOException("closePreparedStatement <exception> :", s);
			}
		}
	}

	/**
	 * Fermeture d'un CallableStatement
	 * @param cs : CallableStatement Ã  fermer
	 * @throws DAOException si problÃ¨me lors de la fermeture du CallableStatement
	 */
	public static void closeCallableStatement(final CallableStatement cs) throws DAOException
	{
		// SI PreparedStatement existe
		if (cs != null){
			try{
				// Fermeture du PreparedStatement
				cs.close();
			}
			catch (SQLException s){
				log.error(cs + "SQL ERROR: "+ s.toString() +" "+ s.getErrorCode() +" "+ s.getSQLState());
				throw new DAOException("closeCallableStatement <exception> :", s);
			}
		}
	}
}
