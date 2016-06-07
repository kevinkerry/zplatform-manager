/*
 * Created on Aug 14, 2008
 *
 * @author camel
 */

package com.zlebank.zplatform.manager.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class LoginFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request =((HttpServletRequest) arg0);
		HttpServletResponse response =((HttpServletResponse) arg1);
			String path ="/zplatform-manager/pages/active/saveActiveStatusActiveStatusAction.action";
			String pathA=request.getRequestURI();
		if (null == request.getSession().getAttribute("LOGIN_USER")&&!path.equals(pathA)) {
			((HttpServletResponse) response).sendRedirect(request.getContextPath() + "?overtime");
			return;
		} else {
			chain.doFilter(request, response);
		}
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
}
