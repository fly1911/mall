package com.mall.test;

import java.util.Date;
import java.util.List;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import com.mall.pojo.User;
import com.mall.util.C3P0Util;
import com.mall.util.MD5Utils;
import com.mall.util.UUIDUtils;

public class TestDbUtils {
	QueryRunner runner = new QueryRunner(C3P0Util.getDataSource());
	
	@Test
	public void testAddUser() throws Exception{
		User user = new User();
		user.setUid(UUIDUtils.ranUUID());
		user.setUsername("zhaoliu");
		user.setPassword(MD5Utils.genMD5("123456"));
		user.setName("zhaoliu");
		user.setBirthday(new Date());
		user.setCode(UUIDUtils.ranUUID());
		user.setEmail("zhaoliu@163.com");
		user.setSex("1");
		user.setState(0);
		
		String sql = "insert into user(uid, username, password, name, email, birthday, sex, state, code)"
				+ " values (?,?,?,?,?,?,?,?,?)";
		
		int res = runner.update(sql, user.getUid(), user.getUsername(), user.getPassword(), user.getName(),
				user.getEmail(), new java.sql.Date(user.getBirthday().getTime()), user.getSex(), user.getState(), 
				user.getCode());
		
		System.out.println(res);
	}
	
	@Test
	public void testDelUser() throws Exception{
		String sql = "delete from user where uid=?";
		int res = runner.update(sql,"8257dabfee314f67abdc1f59c292fd02");
		System.out.println(res);
	}
	
	@Test
	public void testUpdateUser() throws Exception {
		String sql = "update user set sex= ?";
		int res = runner.update(sql, "1");
		System.out.println(res);
	}
	
	//查询一个对象，集合，聚合函数
	@Test
	public void testQueryUserById() throws Exception{
		String sql = "select * from user where uid=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class),"80bb5b1b51404eb1bfe84ee73f56aca7");
		System.out.println(user);
	}
	
	@Test
	public void testQueryUserByUserNameAndPassword() throws Exception{
		String sql = "select * from user where username=? and password=?";
		User user = runner.query(sql, new BeanHandler<User>(User.class),"wangwu",MD5Utils.genMD5("123456"));
		System.out.println(user);
	}

	@Test
	public void testQueryUserList() throws Exception{
		String sql = "select * from user";
		List<User> userList = runner.query(sql, new BeanListHandler<User>(User.class));
		for (int i = 0;i<userList.size();i++) {
			System.out.println(userList.get(i));
		}
	}
	
	@Test
	public void testQueryCount() throws Exception{
		String sql = "select count(*) from user";
		long count = (long) runner.query(sql, new ScalarHandler());
		System.out.println(count);
	}
}
