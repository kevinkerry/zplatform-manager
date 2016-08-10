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
    
    private final static String RELOGIN = "relogin";
    
    public void destroy() {

    }

    public void doFilter(ServletRequest arg0,
            ServletResponse arg1,
            FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = ((HttpServletRequest) arg0);
        HttpServletResponse response = ((HttpServletResponse) arg1);
        String path = "/zplatform-manager/pages/active/saveActiveStatusActiveStatusAction.action";
        String pathA = request.getRequestURI();

        if (null == request.getSession().getAttribute("LOGIN_USER")
                && !path.equals(pathA)) {
            String relogin = null;
            if((relogin=request.getParameter("relogin"))!=null&&relogin.equals(RELOGIN)){
                chain.doFilter(request, response);
                return ;
            }
            if (request.getHeader("x-requested-with") != null
                    && request.getHeader("x-requested-with").equalsIgnoreCase(
                            "XMLHttpRequest")) {// ajax request session timeout
                {
                    response.setHeader("sessionstatus", "sessiontimeout");// 在响应头设置session状态
                    response.getWriter().print("sessiontimeout");  
                    return;
                }
            }

            ((HttpServletResponse) response).sendRedirect(request
                    .getContextPath() + "?overtime");
            return;
        } else {
            chain.doFilter(request, response);
        }
    }
    public void init(FilterConfig arg0) throws ServletException {

    }

}
