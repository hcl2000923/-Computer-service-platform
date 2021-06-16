//package com.yc.shopfile.controller;
//
//import java.io.IOException;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//
//import com.alipay.api.request.AlipayTradeCloseRequest;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.request.AlipayTradeRefundRequest;
//import com.alipay.api.response.AlipayTradeCloseResponse;
//import com.alipay.api.response.AlipayTradeRefundResponse;
//import com.alipay.config.AlipayConfig;
//
//@WebServlet("/ali.action")
//public class aliServlet extends BaseServlet {
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		// ��ó�ʼ����AlipayClient
//		String op = request.getParameter("op");
//		if (op.equals("pay")) {
//			try {
//				doPay(request, response);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//		} else if (op.equals("outtime")) {
//			try {
//				doOutTime(request, response);
//			} catch (AlipayApiException e) {
//				e.printStackTrace();
//			}
//		} else if (op.equals("refund")) {
//			try {
//				doRefund(request, response);
//			} catch (AlipayApiException e) {
//				e.printStackTrace();
//			}
//		} else if (op.equals("doset")) {
//			doSet(request, response);
//		}
//	}
//
//	private void doSet(HttpServletRequest request, HttpServletResponse response) {
//		System.out.println("�洢���ݿ�");
//
//	}
//
//	private void doRefund(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//				AlipayConfig.sign_type);
//
//		// �����������
//		AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
//
//		// �̻������ţ��̻���վ����ϵͳ��Ψһ������
//		String out_trade_no = "2020050422001495840501201616";
//		// ֧�������׺�
//		String trade_no = "";
//		// ���ѡһ����
//		// ��Ҫ�˿�Ľ��ý��ܴ��ڶ���������
//		String refund_amount = "0.01";
//		// �˿��ԭ��˵��
//		String refund_reason = "";
//		// ��ʶһ���˿�����ͬһ�ʽ��׶���˿���Ҫ��֤Ψһ�����貿���˿��˲����ش�
//		String out_request_no = "1";
//
//		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\","
//				+ "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\"" + refund_reason + "\","
//				+ "\"out_request_no\":\"" + out_request_no + "\"}");
//		AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
//		if (alipayResponse.isSuccess()) {
//			System.out.println("���óɹ�");
//		} else {
//			System.out.println("����ʧ��");
//		}
//	}
//
//	private void doOutTime(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
//
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//				AlipayConfig.sign_type);
//		AlipayTradeCloseRequest request1 = new AlipayTradeCloseRequest();
//		request1.setBizContent("{" + "    \"trade_no\":\"2013112611001004680073956707\","
//				+ "    \"out_trade_no\":\"HZ0120131127001\"," + "    \"operator_id\":\"YX01\"" + "  }");
//		AlipayTradeCloseResponse response1 = alipayClient.execute(request1);
//		if (response1.isSuccess()) {
//			System.out.println("���óɹ�");
//		} else {
//			System.out.println("����ʧ��");
//		}
//	}
//
//	private void doPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//				AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//				AlipayConfig.sign_type);
//		// �����������
//		AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//		alipayRequest.setReturnUrl(AlipayConfig.return_url);
//		alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//		String op1 = request.getParameter("account1");
//
//		// �̻������ţ��̻���վ����ϵͳ��Ψһ�����ţ�����
//		String out_trade_no = new String(request.getParameter("ono").getBytes("ISO-8859-1"), "UTF-8");
//		// ���������
//		String total_amount = new String(request.getParameter("account1").getBytes("ISO-8859-1"), "UTF-8");
//
//		System.out.println(op1);
//
//		// �������ƣ�����
//		String subject = "֧��������";
//		// ��Ʒ�������ɿ�
//		String body = "��Ʒ����";
//
//		alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
//				+ "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
//				+ "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//
//		// ����
//		String result;
//		try {
//			result = alipayClient.pageExecute(alipayRequest).getBody();
//			response.setContentType("text/html;charset=" + AlipayConfig.charset);
//			response.getWriter().write(result);// ֱ�ӽ������ı�html�����ҳ��
//			response.getWriter().flush();
//			response.getWriter().close();
//		} catch (AlipayApiException e) {
//			e.printStackTrace();
//			response.getWriter().write("�����쳣����");
//			response.getWriter().flush();
//			response.getWriter().close();
//		}
//	}
//}
