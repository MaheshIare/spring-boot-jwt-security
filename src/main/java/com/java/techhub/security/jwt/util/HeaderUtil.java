/**
 * 
 */
package com.java.techhub.security.jwt.util;

import java.util.Base64;

import org.springframework.stereotype.Service;

/**
 * @author mahes
 *
 */
@Service
public class HeaderUtil {

	public String[] extractUserDetails(String authHeader) {
		if (authHeader != null && authHeader.startsWith("Basic")) {
			String[] str = new String[2];
		    String encodedUsernamePassword = authHeader.substring("Basic ".length()).trim();
		    String usernamePassword = new String(Base64.getDecoder().decode(encodedUsernamePassword));
		    int seperatorIndex = usernamePassword.indexOf(':');
		    String username = usernamePassword.substring(0, seperatorIndex);
		    String password = usernamePassword.substring(seperatorIndex + 1);
		    str[0] = username;
		    str[1] = password;
		    return str;
		}
		return null;
	}
}
