package com.mall.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

public class MailUtils {
	
	private static final String MAIL_FROM_ADDRESS = "bo18219116787@163.com";
	private static final String AUTH_USER_NAME = "bo18219116787";
	private static final String AUTH_CODE = "java883502";
	
	
	/**
	 * 发送内容为普通文本的邮件
	 * @param mail 接受者地址
	 * @param msg 发送文本
	 */
	public static String sendMail(String receiveAddress, String msg){
		
		String res = "success";
		try {
			//设置会话相关的属性
			Properties pro = new Properties();
			pro.setProperty("mail.transport.protocol", "SMTP");
			pro.setProperty("mail.host", "smtp.163.com");
			pro.setProperty("mail.smtp.auth", "true");
			
			//验证器
			Authenticator auth = new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(AUTH_USER_NAME, AUTH_CODE);
				}
			};
			
			//获取会话
			Session session = Session.getInstance(pro, auth);
			session.setDebug(true);
			
			//创建发送信息对象
			MimeMessage mimeMessage = new MimeMessage(session);
			//设置发送人
			mimeMessage.setFrom(new InternetAddress(MAIL_FROM_ADDRESS));
			//设置发送方式及接受者
			mimeMessage.setRecipient(RecipientType.TO, new InternetAddress(receiveAddress));
			//设置发送的标题
			mimeMessage.setSubject("mall用户账号激活邮件");
			//设置发送内容
			mimeMessage.setContent(msg, "text/html;charset=utf-8");
			
			//发送邮件
			Transport.send(mimeMessage);
		} catch (Exception e) {
			res = "fail";
			e.printStackTrace();
		}
		return res;
	}
}
