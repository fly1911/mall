package com.mall.service;

import com.mall.pojo.User;

public interface UserService {
	
	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:新增用户
	 */
	public void add(User user) throws Exception;

	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:账号激活
	 */
	public String active(String activeCode) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:根据用户名和密码查询用户
	 */
	public User queryUser(String username, String password) throws Exception;
	
	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:用于ajax校验用户名是否被注册
	 */
	public String checkUsername(String username) throws Exception;
		
}
