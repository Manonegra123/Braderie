/*******************************************************************************
 * Copyright (C) 2018, Pierre-Eloi Deledalle
 * @author 31010-79-11
 * Date de creation : 23 mai 2018
 * A : 14:06:53
 *
 * PE_LabServlet_13_Braderie
 ******************************************************************************/
package dao.utilitaires;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import dao.exceptions.DAOException;

/**
 * Classe utilitaire définissant les méthodes pour fermer les différents statements utilisé dans les autres classes
 * @author 31010-79-11
 *
 */
public class DAOUtil {
	static final private Log log = LogFactory.getLog(DAOUtil.class);

	// Constructeur en private -> pas d'instanciation
	private  DAOUtil (){}

	/**
	 * Fermeture d'un Statement
	 * @param stmt
	 * @throws DAOException si problème lors de la fermeture du statement
	 */
	public static void closeStatement(final Statement stmt) throws DAOException
	{
		// SI statement existe
		if (stmt != null){
			try{
				// Fermeture du statement
				stmt.close();
			}
			catch (SQLException ex) {
				log.error(stmt, ex);
				throw new DAOException("closeStatement <exception> :", ex);
			}
		}
	}

	/**
	 * Fermeture d'un ResultSet
	 * @param rs : resultset à fermer
	 * @throws DAOException si problème lors de la fermeture du resultset
	 */
	public static void closeResultSet(final ResultSet rs) throws DAOException
	{
		// SI ResultSet existe
		if (rs != null){
			try{
				// Fermeture du ResultSet
				rs.close();
			}
			catch (SQLException ex){
				log.error(rs, ex);
				throw new DAOException("closeResultSet <exception> :", ex);
			}
		}
	}

	/**
	 * Fermeture d'un PreparedStatement
	 * @param ps : PreparedStatement à fermer
	 * @throws DAOException si problème lors de la fermeture du PreparedStatement
	 */
	public static void closePreparedStatement(final PreparedStatement ps) throws DAOException
	{
		// SI PreparedStatement existe
		if (ps != null){
			try{
				// Fermeture du PreparedStatement
				ps.close();
			}
			catch (SQLException ex){
				log.error(ps, ex);
				throw new DAOException("closePreparedStatement <exception> :", ex);
			}
		}
	}

	/**
	 * Fermeture d'un CallableStatement
	 * @param cs : CallableStatement à fermer
	 * @throws DAOException si problème lors de la fermeture du CallableStatement
	 */
	public static void closeCallableStatement(final CallableStatement cs) throws DAOException
	{
		// SI PreparedStatement existe
		if (cs != null){
			try{
				// Fermeture du PreparedStatement
				cs.close();
			}
			catch (SQLException ex){
				log.error(cs, ex);
				throw new DAOException("closeCallableStatement <exception> :", ex);
			}
		}
	}
}
