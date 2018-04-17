package com.mall.dao.impl;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.mall.dao.UserDAO;
import com.mall.pojo.User;
import com.mall.util.C3P0Util;

public class UserDAOImpl implements UserDAO {
	
	private QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());

	@Override
	public int add(User user) throws Exception {
		
		String sql = "insert into user(uid, username, password, name, email, birthday, sex, state, code, type)"
				+ " values (?,?,?,?,?,?,?,?,?,?)";
		
		int res = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), new java.sql.Date(user.getBirthday().getTime()), user.getSex(), user.getState(), 
				user.getCode(), user.getType());
		return res;
	}

	@Override
	public User queryByActiveCode(String activeCode) throws Exception{
		String sql = "select * from user where code = ?";
		User user = runner.query(sql, new BeanHandler<User>(User.class),activeCode);
		return user;
	}

	@Override
	public void updateUserState(User user) throws Exception{
		String sql = "update user set state = 1 where uid= ?";
		runner.update(sql, user.getUid());
	}

	@Override
	public User queryUser(String username, String password) throws Exception {
		String sql = "select * from user where username=? and password=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username, password);
		return user;
	}

	@Override
	public User checkUsername(String username) throws Exception {
		String sql = "select * from user where username=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class), username);
		return user;
	}
	
	

}
