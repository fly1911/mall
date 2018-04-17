package com.mall.test;

import org.junit.Test;

import com.mall.util.MailUtils;

public class TestMailUtils {
	
	@Test
	public void testSendMail(){
		String res = MailUtils.sendMail("864843117@qq.com", "test send mail!");
		System.out.println(res);
	}

}
