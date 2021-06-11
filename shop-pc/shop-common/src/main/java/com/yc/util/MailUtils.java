package com.yc.util;


import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 发邮件工具类
 */
public class MailUtils {
    private static final String USER = "2236347582@qq.com"; // 发件人称号，同邮箱地址
    private static final String PASSWORD = "yzeynkobtsbkebbc"; // 如果是qq邮箱可以使户端授权码，或者登录密码

    /**
     * @param to    收件人邮箱
     * @param text  邮件正文
     * @param title 标题
     */
    /* 发送验证信息的邮件 */
    public static boolean sendMail(String to, String text, String title) {
        try {
            Properties prop = new Properties();
            prop.setProperty("mail.host", "smtp.qq.com");
            //设置QQ邮件服务器
            prop.setProperty("mail.transport.protocol", "smtp"); // 邮件发送协议
            prop.setProperty("mail.smtp.auth", "true"); // 需要验证用户名密码
            // 关于QQ邮箱，还要设置SSL加密，加上以下代码即可
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            prop.put("mail.smtp.ssl.enable", "true");
            prop.put("mail.smtp.ssl.socketFactory", sf);
            //使用JavaMail发送邮件的5个步骤
            //创建定义整个应用程序所需的环境信息的 Session 对象
            Session session = Session.getDefaultInstance(prop, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、授权码
                    return new PasswordAuthentication(USER, PASSWORD);
                }
            });
            //开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
            session.setDebug(true);
            //2、通过session得到transport对象
            Transport ts = session.getTransport();
            //3、使用邮箱的用户名和授权码连上邮件服务器
            ts.connect("smtp.qq.com", USER, PASSWORD);
            //4、创建邮件
            //创建邮件对象
            MimeMessage message = new MimeMessage(session);
            //指明邮件的发件人
            message.setFrom(new InternetAddress(USER));
            //指明邮件的收件人，现在发件人和收件人是一样的，那就是自己给自己发
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));
            //邮件的标题
            message.setSubject(title);
            //邮件的文本内容
            message.setContent(text, "text/html;charset=UTF-8");
            //5、发送邮件
            ts.sendMessage(message, message.getAllRecipients());
            ts.close();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) throws Exception { // 做测试用
//        MailUtils.sendMail("2236347582@qq.com", "订单号：", "新订单请及时查收");
//        System.out.println("发送成功");
        new Thread(() -> {
            MailUtils.sendMail("2236347582@qq.com", "验证码为：", "易易城账号找回密码验证码");
            System.out.println("发送");
        }).start();
    }
}