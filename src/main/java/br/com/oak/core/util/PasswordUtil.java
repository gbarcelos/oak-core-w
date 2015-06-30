package br.com.oak.core.util;

import org.springframework.security.authentication.encoding.MessageDigestPasswordEncoder;

public class PasswordUtil {

	public static String generateMD5(final String password, final String salt) {
		return new MessageDigestPasswordEncoder("MD5").encodePassword(password, salt);
	}
}