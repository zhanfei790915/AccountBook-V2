package com.afei.filter;


import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.ArrayUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.afei.model.SessionUser;
import com.afei.model.User;
import com.afei.service.IUserService;
import com.afei.service.impl.UserServiceImpl;
import com.afei.util.Constants;


public class AuthorityFilter implements Filter {
	private final static String[] static_ext = { "js", "css", "jpg", "png", "gif", "html", "ico", "vm", "swf" };
	private final static String action_ext = "action";
	private static String absolutePath = null;
	
	private Logger logger=LoggerFactory.getLogger(AuthorityFilter.class);
	
	private IUserService userService;
	
	private IUserService getUserService(){
		return userService;
	}
	
	private final SimpleDateFormat formate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		ServletContext application = request.getSession().getServletContext();
		String req_uri = request.getRequestURI();
		HttpSession session = request.getSession();
		String type = req_uri.substring(req_uri.lastIndexOf('.') + 1);
		
		// 静态资源忽略
		if (ArrayUtils.contains(static_ext, type)) {
			chain.doFilter(req, resp);
			return;
		}
		
		if(absolutePath == null){
			absolutePath = getRealPath(request);
		}
		
		if(application.getAttribute(Constants.ABSOLUTEPATH) == null){
			application.setAttribute(Constants.ABSOLUTEPATH,absolutePath);
		}
		
		Object sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
		
		// 如果现在session中没有用户对象，自动登录
		if(null == sessionUserObj){
			autoLogin(request, response);
		}
		
		// 凡是有action后缀的都不过滤（注册，登录功能带action后缀），其他都过滤
		if(!action_ext.equals(type)){
			sessionUserObj = session.getAttribute(Constants.SESSION_USER_KEY);
			if(null==sessionUserObj){
				response.setContentType("text/html;charset=utf-8");
				PrintWriter out = response.getWriter();  
		        out.println("<html>");      
		        out.println("<script>"); 
		        out.println("alert('您的登录已过期，请重新登录')"); 
		        out.println("parent.location.href = ('"+request.getContextPath()+"/userController/showUserLogin.action')");      
		        out.println("</script>");      
		        out.println("</html>");   
				return;
			}
		}
		chain.doFilter(request, resp);
		return;
	}
	//http://127.0.0.1:8080/AccountBook-V2/frameController/showframe

	/**
	 * 自动登录功能
	 * @param req
	 */
	private void autoLogin(HttpServletRequest req, HttpServletResponse resp) {
		
		ServletContext sc = req.getSession().getServletContext();
        XmlWebApplicationContext cxt = (XmlWebApplicationContext)WebApplicationContextUtils.getWebApplicationContext(sc);
        
        if(cxt != null && cxt.getBean("userService") != null && userService == null){
            userService = (UserServiceImpl) cxt.getBean("userService");
        }
		
		try {
			Cookie cookieInfo = getCookieByName(req, Constants.COOKIE_USER_INFO);
			if (cookieInfo != null) {
				String info = URLDecoder.decode(cookieInfo.getValue(), "utf-8");
				if (info != null && !"".equals(info)) {
					String infos[] = info.split("\\|");
					IUserService userService1 = getUserService();
					User user = userService1.login(infos[0], infos[1], true);
					if (user != null) {
						SessionUser loginUser = new SessionUser();
						loginUser.setUserId(user.getId());
						loginUser.setUserName(user.getName());
						req.getSession().setAttribute(Constants.SESSION_USER_KEY, loginUser);
						req.getSession().setAttribute(Constants.USER_ID, user.getId());
						userService.update(user); 
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("====> 自动登录失败");
		}
	}
	
	private String getRealPath(HttpServletRequest request) {
		String port = request.getServerPort() == 80 ? "" : (":" + request.getServerPort());
		String realPath = "http://" + request.getServerName() + port + request.getContextPath();
		return realPath;
	}

	public Cookie getCookieByName(HttpServletRequest request, String name) {
		Map<String, Cookie> cookieMap = ReadCookieMap(request);
		if (cookieMap.containsKey(name)) {
			Cookie cookie = cookieMap.get(name);
			return cookie;
		} else {
			return null;
		}
	}

	private Map<String, Cookie> ReadCookieMap(HttpServletRequest request) {
		Map<String, Cookie> cookieMap = new HashMap<String, Cookie>();
		Cookie[] cookies = request.getCookies();
		if (null != cookies) {
			for (Cookie cookie : cookies) {
				cookieMap.put(cookie.getName(), cookie);
			}
		}
		return cookieMap;
	}
	
	@Override
	public void destroy() {
	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
		
	}
}