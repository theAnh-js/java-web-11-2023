package com.laptrinhjavaweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.utils.SessionUtil;

public class AuthorizationFilter implements Filter {

	private ServletContext servletContext;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		this.servletContext = filterConfig.getServletContext();
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;

		String url = req.getRequestURI();
		if (url.startsWith("/admin")) {
			UserModel user = (UserModel) SessionUtil.getInstance().getValue(req, "user");

			if (user != null) {
				if (user.getRole().getCode().equals(SystemConstant.ADMIN)) {
					chain.doFilter(request, response);
				} else if (user.getRole().getCode().equals(SystemConstant.USER)) {
					resp.sendRedirect(
							req.getContextPath() + "/dang-nhap?action=login&message=not_permission&alert=danger");
				}
			} else {
				resp.sendRedirect(req.getContextPath() + "/dang-nhap?action=login&message=not_login&alert=danger");
			}

		} else {
			chain.doFilter(request, response);
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

}
