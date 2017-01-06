/**
 * 
 */
package za.co.sindi.commons.javaee.jaxrs.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

/**
 * @author Bienfait Sindi
 * @since 09 December 2016
 *
 */
public class HTTPBasicAuthenticationClientRequestFilter implements ClientRequestFilter {

	private String username;
	private String password;
	
	/**
	 * @param username
	 * @param password
	 */
	public HTTPBasicAuthenticationClientRequestFilter(String username, String password) {
		super();
		this.username = username == null ? "" : username;
		this.password = password == null ? "" : password;
	}

	/* (non-Javadoc)
	 * @see javax.ws.rs.client.ClientRequestFilter#filter(javax.ws.rs.client.ClientRequestContext)
	 */
	@Override
	public void filter(ClientRequestContext requestContext) throws IOException {
		// TODO Auto-generated method stub
		requestContext.getHeaders().add("Authorization", "Basic " + createAuthentication());
	}
	
	private String createAuthentication() {
		String authenticationString = username + ":" + password;
		return Base64.getEncoder().encodeToString(authenticationString.getBytes(StandardCharsets.UTF_8));
	}
}
