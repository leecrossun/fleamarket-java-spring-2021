
package com.yanado.controller.user;

import javax.servlet.http.HttpSession;

public class UserSessionUtils {

	public static final String USER_SESSION_KEY = "userId";
	public static final String AUTH = "auth";

	public static String getLoginUserId(HttpSession session) {

		String userId = (String) session.getAttribute(USER_SESSION_KEY);

		return userId;
	}

	public static String getAuth(HttpSession session) {

		String auth = (String) session.getAttribute(AUTH);

		return auth;
	}

	public static boolean hasLogined(HttpSession session) {

		if (getLoginUserId(session) != null) {
			return true;
		}

		return false;
	}

	public static boolean isLoginUser(String userId, HttpSession session) {

		String loginUser = getLoginUserId(session);

		if (loginUser == null) {
			return false;
		}

		return loginUser.equals(userId);
	}

}
