package net.kdigital.web_project.handler;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final RequestCache requestCache = new HttpSessionRequestCache();
	private final RedirectStrategy redirectStrategy= new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException {
		clearSession(request);
		
		SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		/**
		 * preURL이 존재하는 경우
		 */
		String prevPage = (String) request.getSession().getAttribute("prevPage");
		if (prevPage != null) {
			request.getSession().removeAttribute("prevPage");
		}
		
		// 기본 URL 
		String URL = "/";
		
		if (savedRequest != null) {
			URL = savedRequest.getRedirectUrl();
		} else if (prevPage != null && !prevPage.equals("")) {
			// 회원가입 - 로그인으로 넘어온경우는 메인페이지로 redirect
			if (prevPage.contains("/user/login")) {
				URL = "";
			} else {
				URL = prevPage;
			}
		}
		
		redirectStrategy.sendRedirect(request, response, URL);
	}
	
	// 로그인 실패 후 성공 시 남아있는 에러 세션 제거
    protected void clearSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
	
}
