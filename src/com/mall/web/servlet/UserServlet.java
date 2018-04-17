package com.mall.web.servlet;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;

import com.mall.pojo.User;
import com.mall.service.UserService;
import com.mall.service.impl.UserServiceImpl;
import com.mall.util.MyDateConverter;
import com.mall.util.StringUtils;
import com.mall.web.base.BaseServlet;

import cn.dsna.util.images.ValidateCode;

public class UserServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;

	private UserService userService = new UserServiceImpl();

	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0 desc:web层注册方法
	 */
	public String register(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// 1、验证码和密码校验
		String validateCode = (String) request.getParameter("validateCode");
		String sessionValidateCode = (String) request.getSession().getAttribute("sessionValidateCode");
		if ("".equals(validateCode) || validateCode == null) {
			request.setAttribute("msg", "验证码不能为空！");
			return "/jsp/register.jsp";
		}

		if (!validateCode.equalsIgnoreCase(sessionValidateCode)) {
			request.setAttribute("msg", "验证码错误！");
			return "/jsp/register.jsp";
		}

		String password = request.getParameter("password");
		String confirmpwd = request.getParameter("confirmpwd");
		if ("".equals(password) || password == null || "".equals(confirmpwd) || confirmpwd == null) {
			request.setAttribute("msg", "密码或二次密码不能为空！");
			return "/jsp/register.jsp";
		}

		if (!password.equals(confirmpwd)) {
			request.setAttribute("msg", "两次密码不一致！");
			return "/jsp/register.jsp";
		}

		// 2、组装数据交给业务层
		User user = new User();
		Map<String, String[]> parameterMap = request.getParameterMap();

		// 注册转换器：把user中数据类型为java.util.Date的属性名在parameterMap中对应的值转换成Date类型
		ConvertUtils.register(new MyDateConverter(), Date.class);
		BeanUtils.populate(user, parameterMap);
		userService.add(user);

		// 3、设置提示信息
		request.setAttribute("msg", "恭喜你注册成功,账号激活邮件已发送到你的注册邮箱,请激活账号以便登录！");
		return "/jsp/msg.jsp";
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0 desc:验证码
	 */
	public void validateCode(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ValidateCode validateCode = new ValidateCode(130, 30, 4, 9);
		String code = validateCode.getCode();
		// 保存到session域中
		request.getSession().setAttribute("sessionValidateCode", code);
		System.out.println("sessionValidateCode:" + code);
		// 使用服务的输出流向客户端输入图片流
		validateCode.write(response.getOutputStream());
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0 desc:账号激活
	 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String activeCode = request.getParameter("code");
		String msg = userService.active(activeCode);// 成功激活返回“success”
		if ("success".equals(msg)) {
			String path = request.getContextPath() + "/jsp/login.jsp";
			msg = "你的账号已成功激活，请点击<a href='" + path + "'>这里</a>进行登录";
		}
		request.setAttribute("msg", msg);
		// 提示激活成功，并提供登录地址
		return "/jsp/msg.jsp";
	}

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:登录
	 */
	public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//1、校验验证码，账号密码
		String validateCode = (String) request.getParameter("validateCode");
		String sessionValidateCode = (String) request.getSession().getAttribute("sessionValidateCode");
		if ("".equals(validateCode) || validateCode==null) {
			request.setAttribute("msg", "验证码不能为空！");
			return "/jsp/login.jsp";
		}
		
		if (!validateCode.equalsIgnoreCase(sessionValidateCode)) {
			request.setAttribute("msg", "验证码错误！");
			return "/jsp/login.jsp";
		}
		
		String username = (String) request.getParameter("username");
		String password = (String) request.getParameter("password");
		if ("".equals(username) || username == null || "".equals(password) || password == null) {
			request.setAttribute("msg", "账号或密码不能为空！");
			return "/jsp/login.jsp";
		}
		
		User user = userService.queryUser(username,password);
		if(user==null){
			request.setAttribute("msg", "账号或密码错误！");
			return "/jsp/login.jsp";
		}
		
		//2、校验成功，保存用户信息
		//处理“自动登录”和“记住用户”
		String isAutoLogin = request.getParameter("isAutoLogin");
		//添加cookie
		if ("1".equals(isAutoLogin)) {
			//用户信息
			Cookie userInfo = new Cookie("userInfo",username+"&"+password);
			userInfo.setMaxAge(60*60*24*7);//一周内免登录
			userInfo.setPath("/");
			response.addCookie(userInfo);
			
			//自动登录方框处理
			Cookie autoLoginBox = new Cookie("isAutoLogin","1");
			userInfo.setMaxAge(60*60*24*7);
			userInfo.setPath("/");
			response.addCookie(autoLoginBox);
		//删除cookie
		} else {
			Cookie userInfo = new Cookie("userInfo","");
			userInfo.setMaxAge(0);
			userInfo.setPath("/");
			response.addCookie(userInfo);
			
			Cookie autoLoginBox = new Cookie("isAutoLogin","");
			userInfo.setMaxAge(0);
			userInfo.setPath("/");
			response.addCookie(autoLoginBox);
		}
		
		//记住用户名
		String isRemName = request.getParameter("isRemName");
		if ("1".equals(isRemName)) {
			Cookie usernameCookie = new Cookie("usernameCookie",username);
			usernameCookie.setMaxAge(60*60*24*7);
			usernameCookie.setPath("/");
			response.addCookie(usernameCookie);
		} else {
			Cookie usernameCookie = new Cookie("usernameCookie","");
			usernameCookie.setMaxAge(0);
			usernameCookie.setPath("/");
			response.addCookie(usernameCookie);
		}
		
		user.setPassword("");
		request.getSession().setAttribute("user", user);
		response.sendRedirect(request.getContextPath()+"/jsp/index.jsp");
		return null;
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:注销
	 */
	public String logout(HttpServletRequest request, HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "/jsp/login.jsp";
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:用于ajax校验用户名是否被注册
	 */
	public void checkUsername(HttpServletRequest request, HttpServletResponse response) throws Exception{
		String username = request.getParameter("username");
		String rs = userService.checkUsername(username);
		response.getWriter().write(rs);
	}
	
	/**
	 * 
	 * @author bo
	 * @date 2018年4月17日
	 * @version 1.0
	 * desc:后台管理员登录
	 * @throws Exception 
	 */
	public String manLogin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
			request.setAttribute("msg", "用户名或密码不能为空");
			return "/admin/index.jsp";
		}
		
		User user = userService.queryUser(username, password);
		if (user == null) {
			request.setAttribute("msg", "用户名或密码错误");
			return "/admin/index.jsp";
		}
		if (!"2".equals(user.getType())) {
			request.setAttribute("msg", "请使用管理员账号登录");
			return "/admin/index.jsp";
		}
		
		user.setPassword("");
		request.getSession().setAttribute("user", user);
		return "/admin/home.jsp";
	}
}
