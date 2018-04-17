package com.mall.dao;

import com.mall.pojo.User;

public interface UserDAO {
	
	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:新增用户
	 */
	public int add(User user) throws Exception;

	/**
	 * 
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:根据激活码查找用户
	 */
	public User queryByActiveCode(String activeCode) throws Exception;

	/**
	 * @author bo
	 * @date 2018年4月15日
	 * @version 1.0
	 * desc:修改用户激活状态，1已激活，0未激活
	 */
	public void updateUserState(User user) throws Exception;

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
	public User checkUsername(String username) throws Exception;
} 
