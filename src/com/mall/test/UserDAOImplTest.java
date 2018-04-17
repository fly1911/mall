package com.mall.test;

import java.util.Date;

import org.junit.Test;

import com.mall.dao.UserDAO;
import com.mall.dao.impl.UserDAOImpl;
import com.mall.pojo.User;
import com.mall.util.MD5Utils;
import com.mall.util.UUIDUtils;

public class UserDAOImplTest {

	@Test
	public void testAdd() throws Exception {
		User user = new User();
		user.setUid(UUIDUtils.ranUUID());
		user.setUsername("admin");
		user.setPassword(MD5Utils.genMD5("admin"));
		user.setName("manager");
		user.setBirthday(new Date());
		user.setCode(UUIDUtils.ranUUID());
		user.setEmail("admin@163.com");
		user.setSex("1");
		user.setState(0);
		user.setType("2");
		
		UserDAO userDAOImpl = new UserDAOImpl();
		userDAOImpl.add(user);
	}
}
