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
package controllers.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


/**
 * @author Manonegra
 * Classe de journalisation implémentant l'interface Filter
 *
 */
@WebFilter(urlPatterns="/FC?action=login" )
public class Journal implements Filter{

	// Logger
	private static final Logger log = LogManager.getLogger(Journal.class);

	private FilterConfig filterConfig = null;
	private String appli = "" ;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.filterConfig = filterConfig;
		appli = filterConfig.getInitParameter("PE_LabJSP_12_Braderie") ;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			if (filterConfig == null)
				return;

			log.info ("Poste Hôte client : " + request.getRemoteHost());
			log.info ("Adresse IP du client : " + request.getRemoteAddr());
			log.info ("Numéro du port du client : " + request.getRemotePort());
			log.info ("Protocole : " + request.getProtocol());

			chain.doFilter(request, response);
			log.info("Time to execute request:"+(System.currentTimeMillis() - System.currentTimeMillis())+ " milliseconds");
		}
		catch (Throwable t) {
			log.error(t);
		}
	}

	@Override
	public void destroy() {
		filterConfig = null;

	}

}
