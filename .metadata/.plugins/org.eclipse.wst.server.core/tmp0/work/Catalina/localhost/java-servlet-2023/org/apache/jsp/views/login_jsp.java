/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/8.5.78
 * Generated at: 2023-11-17 02:35:23 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.views;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent,
                 org.apache.jasper.runtime.JspSourceImports {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  static {
    _jspx_dependants = new java.util.HashMap<java.lang.String,java.lang.Long>(3);
    _jspx_dependants.put("/common/taglib.jsp", Long.valueOf(1699812140021L));
    _jspx_dependants.put("jar:file:/C:/Users/meiou/OneDrive/デスクトップ/JAVA-WEB-11-2023/workspace2/.metadata/.plugins/org.eclipse.wst.server.core/tmp0/wtpwebapps/java-servlet-2023/WEB-INF/lib/jstl-1.2.jar!/META-INF/c.tld", Long.valueOf(1153352682000L));
    _jspx_dependants.put("/WEB-INF/lib/jstl-1.2.jar", Long.valueOf(1698759897454L));
  }

  private static final java.util.Set<java.lang.String> _jspx_imports_packages;

  private static final java.util.Set<java.lang.String> _jspx_imports_classes;

  static {
    _jspx_imports_packages = new java.util.HashSet<>();
    _jspx_imports_packages.add("javax.servlet");
    _jspx_imports_packages.add("javax.servlet.http");
    _jspx_imports_packages.add("javax.servlet.jsp");
    _jspx_imports_classes = null;
  }

  private org.apache.jasper.runtime.TagHandlerPool _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody;

  private volatile javax.el.ExpressionFactory _el_expressionfactory;
  private volatile org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public java.util.Set<java.lang.String> getPackageImports() {
    return _jspx_imports_packages;
  }

  public java.util.Set<java.lang.String> getClassImports() {
    return _jspx_imports_classes;
  }

  public javax.el.ExpressionFactory _jsp_getExpressionFactory() {
    if (_el_expressionfactory == null) {
      synchronized (this) {
        if (_el_expressionfactory == null) {
          _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
        }
      }
    }
    return _el_expressionfactory;
  }

  public org.apache.tomcat.InstanceManager _jsp_getInstanceManager() {
    if (_jsp_instancemanager == null) {
      synchronized (this) {
        if (_jsp_instancemanager == null) {
          _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
        }
      }
    }
    return _jsp_instancemanager;
  }

  public void _jspInit() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
  }

  public void _jspDestroy() {
    _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.release();
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      throws java.io.IOException, javax.servlet.ServletException {

    final java.lang.String _jspx_method = request.getMethod();
    if (!"GET".equals(_jspx_method) && !"POST".equals(_jspx_method) && !"HEAD".equals(_jspx_method) && !javax.servlet.DispatcherType.ERROR.equals(request.getDispatcherType())) {
      response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED, "JSPではGET、POST、またはHEADのみが許可されます。 JasperはOPTIONSも許可しています。");
      return;
    }

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\r');
      out.write('\n');
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("	\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("\r\n");
      out.write("<link\r\n");
      out.write("	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css\"\r\n");
      out.write("	rel=\"stylesheet\" id=\"bootstrap-css\">\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://maxcdn.bootstrapcdn.com/bootstrap/4.1.1/js/bootstrap.min.js\"></script>\r\n");
      out.write("<script\r\n");
      out.write("	src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>\r\n");
      out.write("<!------ Include the above in your HEAD tag ---------->\r\n");
      out.write("\r\n");
      out.write("<title>Login Page</title>\r\n");
      out.write("<!--Made with love by Mutiullah Samim -->\r\n");
      out.write("\r\n");
      out.write("<!--Bootsrap 4 CDN-->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\"\r\n");
      out.write("	integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\"\r\n");
      out.write("	crossorigin=\"anonymous\">\r\n");
      out.write("\r\n");
      out.write("<!--Fontawesome CDN-->\r\n");
      out.write("<link rel=\"stylesheet\"\r\n");
      out.write("	href=\"https://use.fontawesome.com/releases/v5.3.1/css/all.css\"\r\n");
      out.write("	integrity=\"sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU\"\r\n");
      out.write("	crossorigin=\"anonymous\">\r\n");
      out.write("	\r\n");
      out.write("<style>\r\n");
      out.write("\r\n");
      out.write("/* Made with love by Mutiullah Samim*/\r\n");
      out.write("@import url('https://fonts.googleapis.com/css?family=Numans');\r\n");
      out.write("\r\n");
      out.write("html, body {\r\n");
      out.write("	background-image:\r\n");
      out.write("		url('http://getwallpapers.com/wallpaper/full/a/5/d/544750.jpg');\r\n");
      out.write("	background-size: cover;\r\n");
      out.write("	background-repeat: no-repeat;\r\n");
      out.write("	height: 100%;\r\n");
      out.write("	font-family: 'Numans', sans-serif;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".container {\r\n");
      out.write("	height: 100%;\r\n");
      out.write("	align-content: center;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".card {\r\n");
      out.write("	height: 370px;\r\n");
      out.write("	margin-top: auto;\r\n");
      out.write("	margin-bottom: auto;\r\n");
      out.write("	width: 400px;\r\n");
      out.write("	background-color: rgba(0, 0, 0, 0.5) !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".social_icon span {\r\n");
      out.write("	font-size: 60px;\r\n");
      out.write("	margin-left: 10px;\r\n");
      out.write("	color: #FFC312;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".social_icon span:hover {\r\n");
      out.write("	color: white;\r\n");
      out.write("	cursor: pointer;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".card-header h3 {\r\n");
      out.write("	color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".social_icon {\r\n");
      out.write("	position: absolute;\r\n");
      out.write("	right: 20px;\r\n");
      out.write("	top: -45px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".input-group-prepend span {\r\n");
      out.write("	width: 50px;\r\n");
      out.write("	background-color: #FFC312;\r\n");
      out.write("	color: black;\r\n");
      out.write("	border: 0 !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("input:focus {\r\n");
      out.write("	outline: 0 0 0 0 !important;\r\n");
      out.write("	box-shadow: 0 0 0 0 !important;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".remember {\r\n");
      out.write("	color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".remember input {\r\n");
      out.write("	width: 20px;\r\n");
      out.write("	height: 20px;\r\n");
      out.write("	margin-left: 15px;\r\n");
      out.write("	margin-right: 5px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".login_btn {\r\n");
      out.write("	color: black;\r\n");
      out.write("	background-color: #FFC312;\r\n");
      out.write("	width: 100px;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".login_btn:hover {\r\n");
      out.write("	color: black;\r\n");
      out.write("	background-color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".links {\r\n");
      out.write("	color: white;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".links a {\r\n");
      out.write("	margin-left: 4px;\r\n");
      out.write("}\r\n");
      out.write("</style>\r\n");
      out.write("</head>\r\n");
      out.write("<body>\r\n");
      out.write("	<div class=\"container\">\r\n");
      out.write("		<div class=\"d-flex justify-content-center h-100\">\r\n");
      out.write("			<div class=\"card\">\r\n");
      out.write("				<div class=\"card-header\">\r\n");
      out.write("					<h3>Sign In</h3>\r\n");
      out.write("					<div class=\"d-flex justify-content-end social_icon\">\r\n");
      out.write("						<span><i class=\"fab fa-facebook-square\"></i></span> <span><i\r\n");
      out.write("							class=\"fab fa-google-plus-square\"></i></span> <span><i\r\n");
      out.write("							class=\"fab fa-twitter-square\"></i></span>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"card-body\">\r\n");
      out.write("				\r\n");
      out.write("				<!-- Khi bấm login thì form sẽ được chuyển đến url servlet '/dang-nhap'/ trong action.\r\n");
      out.write("				-> Thông qua <input type=\"hidden\" value=\"login\" name=\"action\">, ta lấy được parameter action=login\r\n");
      out.write("				bên '/dang-nhap'/ servlet trong phương thực doPost vì method form là post.\r\n");
      out.write("				-> Vào doPost: check authentication user có username, password có tồn tại trong database không.\r\n");
      out.write("				-> Nếu không có(null) -> chuyển về login.\r\n");
      out.write("				-> Nếu có -> lấy ra thông tin user đó, rồi getSession, rồi check xem user đố là USER hay ADMIN\r\n");
      out.write("				-> là USER -> đến /trang-chu\r\n");
      out.write("				-> là ADMIN -> đến /admin-home\r\n");
      out.write("				\r\n");
      out.write("				 -->\r\n");
      out.write("					<form action=\"");
      if (_jspx_meth_c_005furl_005f0(_jspx_page_context))
        return;
      out.write("\" id=\"formLogin\" method=\"post\">\r\n");
      out.write("					\r\n");
      out.write("						<div class=\"input-group form-group\">\r\n");
      out.write("							<div class=\"input-group-prepend\">\r\n");
      out.write("								<span class=\"input-group-text\"><i class=\"fas fa-user\"></i></span>\r\n");
      out.write("							</div>\r\n");
      out.write("							<input type=\"text\" class=\"form-control\" placeholder=\"username\" name=\"userName\">\r\n");
      out.write("\r\n");
      out.write("						</div>\r\n");
      out.write("						\r\n");
      out.write("						<div class=\"input-group form-group\">\r\n");
      out.write("							<div class=\"input-group-prepend\">\r\n");
      out.write("								<span class=\"input-group-text\"><i class=\"fas fa-key\"></i></span>\r\n");
      out.write("							</div>\r\n");
      out.write("							<input type=\"password\" class=\"form-control\"\r\n");
      out.write("								placeholder=\"password\" name=\"password\">\r\n");
      out.write("						</div>\r\n");
      out.write("						\r\n");
      out.write("						<div class=\"row align-items-center remember\">\r\n");
      out.write("							<input type=\"checkbox\">Remember Me\r\n");
      out.write("						</div>\r\n");
      out.write("						<div class=\"form-group\">\r\n");
      out.write("							<input type=\"submit\" value=\"Login\"\r\n");
      out.write("								class=\"btn float-right login_btn\">\r\n");
      out.write("						</div>\r\n");
      out.write("						<input type=\"hidden\" value=\"login\" name=\"action\">\r\n");
      out.write("					</form>\r\n");
      out.write("				</div>\r\n");
      out.write("				<div class=\"card-footer\">\r\n");
      out.write("					<div class=\"d-flex justify-content-center links\">\r\n");
      out.write("						Don't have an account?<a href=\"#\">Sign Up</a>\r\n");
      out.write("					</div>\r\n");
      out.write("					<div class=\"d-flex justify-content-center\">\r\n");
      out.write("						<a href=\"#\">Forgot your password?</a>\r\n");
      out.write("					</div>\r\n");
      out.write("				</div>\r\n");
      out.write("			</div>\r\n");
      out.write("		</div>\r\n");
      out.write("	</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try {
            if (response.isCommitted()) {
              out.flush();
            } else {
              out.clearBuffer();
            }
          } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }

  private boolean _jspx_meth_c_005furl_005f0(javax.servlet.jsp.PageContext _jspx_page_context)
          throws java.lang.Throwable {
    javax.servlet.jsp.PageContext pageContext = _jspx_page_context;
    javax.servlet.jsp.JspWriter out = _jspx_page_context.getOut();
    //  c:url
    org.apache.taglibs.standard.tag.rt.core.UrlTag _jspx_th_c_005furl_005f0 = (org.apache.taglibs.standard.tag.rt.core.UrlTag) _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.get(org.apache.taglibs.standard.tag.rt.core.UrlTag.class);
    boolean _jspx_th_c_005furl_005f0_reused = false;
    try {
      _jspx_th_c_005furl_005f0.setPageContext(_jspx_page_context);
      _jspx_th_c_005furl_005f0.setParent(null);
      // /views/login.jsp(149,19) name = value type = null reqTime = true required = false fragment = false deferredValue = false expectedTypeName = null deferredMethod = false methodSignature = null
      _jspx_th_c_005furl_005f0.setValue("/dang-nhap");
      int _jspx_eval_c_005furl_005f0 = _jspx_th_c_005furl_005f0.doStartTag();
      if (_jspx_th_c_005furl_005f0.doEndTag() == javax.servlet.jsp.tagext.Tag.SKIP_PAGE) {
        return true;
      }
      _005fjspx_005ftagPool_005fc_005furl_0026_005fvalue_005fnobody.reuse(_jspx_th_c_005furl_005f0);
      _jspx_th_c_005furl_005f0_reused = true;
    } finally {
      org.apache.jasper.runtime.JspRuntimeLibrary.releaseTag(_jspx_th_c_005furl_005f0, _jsp_getInstanceManager(), _jspx_th_c_005furl_005f0_reused);
    }
    return false;
  }
}
