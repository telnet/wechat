package com.qltv.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.qltv.utils.SignUtil;

/**
 * Servlet implementation class WeixinServlet
 */
@WebServlet("/wx.do")
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=utf-8");
		//微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp，nonce参数
		String signature = request.getParameter("signature");
		//时间戳
		String timestamp = request.getParameter("timestamp");
		//随机数
		String nonce = request.getParameter("nonce");
		//随机字符串
		String echostr = request.getParameter("echostr");
		
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			response.getOutputStream().println(echostr);
			System.out.println("测试连接成功！！！");
			
			System.out.println("signature--->"+signature);
			System.out.println("timestamp--->"+timestamp);
			System.out.println("nonce--->"+nonce);
			System.out.println("echostr--->"+echostr);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		//接收消息、处理消息
		String reqMsg = "<xml>"
				+ "<ToUserName><![CDATA[oyORnuP8q7ou2gfYjqLzSIWZf0rs]]></ToUserName>"
				+ "<FromUserName><![CDATA[gh_10f6c3c3ac5a]]></FromUserName>"
				+ "<CreateTime>1411034505</CreateTime>"
				+ "<MsgType><![CDATA[text]]></MsgType>"
				+ "<Content><![CDATA[Welcome to join us!]]></Content>"
				+ "<FuncFlag>0</FuncFlag>"
				+ "</xml>";
		//响应消息
		PrintWriter out = response.getWriter();
		out.print(reqMsg);
		out.close();
	}

}
