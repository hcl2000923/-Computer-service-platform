<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.Font"%>
<%@page import="java.awt.Color"%>
<%@page import="java.util.Random"%>
<%@page import="java.awt.Graphics"%>
<%@page import="java.io.OutputStream"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%-- 验证码操作<img src="Code.jsp"> --%>
<%
	try {
		//设置无缓存
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		//定义图片大小信息
		int width=110;
		int height=20;
		BufferedImage image=new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		OutputStream os=response.getOutputStream();
		Graphics g=image.getGraphics();
		Random random=new Random();
		//设置背景颜色
		g.setColor(new Color(251,244,166));
		//设置填充指定矩形
		g.fillRect(0, 0, width, height);
		//设置字体
		g.setFont(new Font("Times New Roman",Font.BOLD,18));
		//设置文字颜色
		g.setColor(new Color(198,39,60));
		//设置运算符
		String[] s= {"+","-"};
		String sRand="";
		//设置运算因子
		int num1=random.nextInt(100);
		int num2=random.nextInt(100);
		int index=random.nextInt(2);
		String rand=s[index];
		//设置运算结果
		int end=0;
		//得到运算表达式
		sRand=num1+rand+num2;
		//绘制表达式
		g.drawString(sRand, 13, 16);
		if(rand.equals("+")) {
			end=num1+num2;
		}else if(rand.equals("-")) {
			end=num1-num2;
		}
		session.setAttribute("rand", end);
		g.dispose();
		ImageIO.write(image,"JPEG",os);
		os.close();
		os.flush();
		os=null;
		response.flushBuffer();
		out.clear();
		out=pageContext.pushBody();
	}catch(Exception e) {
		System.out.print(e.getMessage());
		e.printStackTrace();
	}
%>