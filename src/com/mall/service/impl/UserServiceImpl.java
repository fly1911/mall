package com.mall.service.impl;

import com.mall.dao.UserDAO;
import com.mall.dao.impl.UserDAOImpl;
import com.mall.pojo.User;
import com.mall.service.UserService;
import com.mall.util.MD5Utils;
import com.mall.util.MailUtils;
import com.mall.util.UUIDUtils;

public class UserServiceImpl implements UserService {
	
	private UserDAO userDAO = new UserDAOImpl();

	@Override
	public void add(User user) throws Exception{
		//1、插入记录后
		user.setUid(UUIDUtils.ranUUID());
		String active_code = UUIDUtils.ranUUID();
		user.setCode(active_code);
		user.setPassword(MD5Utils.genMD5(user.getPassword()));
		user.setType("1");
		userDAO.add(user);
		
		//、发送邮件
		String path = "http://localhost:8080/HeimaMall/userServlet?method=active&code="+active_code;
		String msg = "恭喜你成功在黑马商城中注册,请点击以下超链接进行账号激活：<a href='"+path+"'>"+path+"</a>";
		MailUtils.sendMail(user.getEmail(), msg);
	}

	@Override
	public String active(String activeCode) throws Exception {
		/* 
		 * 判断该激活码是否有效
		 * 1)无效返回“无效的激活码”
		 * 2)有效需判断是否已使用过
		 *   1、已使用过提示"该账号已经激活，请不要多次激活"
		 *   2、修改激活码对应的用户激活状态
		 */
		String rs = "";
		User user = userDAO.queryByActiveCode(activeCode);
		if (user==null) {
			rs = "无效的激活码!";
			return rs;
		}
		if (user.getState()==1) {
			rs = "该账号已经激活，请不要多次激活!";
			return rs;
		}
		userDAO.updateUserState(user);
		rs = "success";
		return rs;
	}

	@Override
	public User queryUser(String username, String password) throws Exception {
		User user = userDAO.queryUser(username, MD5Utils.genMD5(password));
		return user;
	}

	@Override
	public String checkUsername(String username) throws Exception {
		String rs = "1";
		User user = userDAO.checkUsername(username);
		if (user==null) {
			rs = "0";
		}
		return rs;
	}

}
