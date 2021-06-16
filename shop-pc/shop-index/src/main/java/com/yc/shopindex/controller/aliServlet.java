//package com.yc.shopindex.controller;
//
//import com.alipay.api.AlipayApiException;
//import com.alipay.api.AlipayClient;
//import com.alipay.api.DefaultAlipayClient;
//import com.alipay.api.request.AlipayTradeCloseRequest;
//import com.alipay.api.request.AlipayTradePagePayRequest;
//import com.alipay.api.request.AlipayTradeRefundRequest;
//import com.alipay.api.response.AlipayTradeCloseResponse;
//import com.alipay.api.response.AlipayTradeRefundResponse;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet("/ali.action")
//public class aliServlet {
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        // 获得初始化的AlipayClient
//        String op = request.getParameter("op");
//        if (op.equals("pay")) {
//            try {
//                doPay(request, response);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        } else if (op.equals("outtime")) {
//            try {
//                doOutTime(request, response);
//            } catch (AlipayApiException e) {
//                e.printStackTrace();
//            }
//        } else if (op.equals("refund")) {
//            try {
//                doRefund(request, response);
//            } catch (AlipayApiException e) {
//                e.printStackTrace();
//            }
//        } else if (op.equals("doset")) {
//            doSet(request, response);
//        }
//    }
//
//    private void doSet(HttpServletRequest request, HttpServletResponse response) {
//        System.out.println("存储数据库");
//
//    }
//
//    private void doRefund(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//                AlipayConfig.sign_type);
//
//        // 设置请求参数
//        AlipayTradeRefundRequest alipayRequest = new AlipayTradeRefundRequest();
//
//        // 商户订单号，商户网站订单系统中唯一订单号
//        String out_trade_no = "2020050422001495840501201616";
//        // 支付宝交易号
//        String trade_no = "";
//        // 请二选一设置
//        // 需要退款的金额，该金额不能大于订单金额，必填
//        String refund_amount = "0.01";
//        // 退款的原因说明
//        String refund_reason = "";
//        // 标识一次退款请求，同一笔交易多次退款需要保证唯一，如需部分退款，则此参数必传
//        String out_request_no = "1";
//
//        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"trade_no\":\"" + trade_no + "\","
//                + "\"refund_amount\":\"" + refund_amount + "\"," + "\"refund_reason\":\"" + refund_reason + "\","
//                + "\"out_request_no\":\"" + out_request_no + "\"}");
//        AlipayTradeRefundResponse alipayResponse = alipayClient.execute(alipayRequest);
//        if (alipayResponse.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//    }
//
//    private void doOutTime(HttpServletRequest request, HttpServletResponse response) throws AlipayApiException {
//
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//                AlipayConfig.sign_type);
//        AlipayTradeCloseRequest request1 = new AlipayTradeCloseRequest();
//        request1.setBizContent("{" + "    \"trade_no\":\"2013112611001004680073956707\","
//                + "    \"out_trade_no\":\"HZ0120131127001\"," + "    \"operator_id\":\"YX01\"" + "  }");
//        AlipayTradeCloseResponse response1 = alipayClient.execute(request1);
//        if (response1.isSuccess()) {
//            System.out.println("调用成功");
//        } else {
//            System.out.println("调用失败");
//        }
//    }
//
//    private void doPay(HttpServletRequest request, HttpServletResponse response) throws Exception {
//        AlipayClient alipayClient = new DefaultAlipayClient(AlipayConfig.gatewayUrl, AlipayConfig.app_id,
//                AlipayConfig.merchant_private_key, "json", AlipayConfig.charset, AlipayConfig.alipay_public_key,
//                AlipayConfig.sign_type);
//        // 设置请求参数
//        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
//        alipayRequest.setReturnUrl(AlipayConfig.return_url);
//        alipayRequest.setNotifyUrl(AlipayConfig.notify_url);
//        String op1 = request.getParameter("account1");
//
//        // 商户订单号，商户网站订单系统中唯一订单号，必填
//        String out_trade_no = new String(request.getParameter("ono").getBytes("ISO-8859-1"), "UTF-8");
//        // 付款金额，必填
//        String total_amount = new String(request.getParameter("account1").getBytes("ISO-8859-1"), "UTF-8");
//
//        System.out.println(op1);
//
//        // 订单名称，必填
//        String subject = "支付宝测试";
//        // 商品描述，可空
//        String body = "商品描述";
//
//        alipayRequest.setBizContent("{\"out_trade_no\":\"" + out_trade_no + "\"," + "\"total_amount\":\"" + total_amount
//                + "\"," + "\"subject\":\"" + subject + "\"," + "\"body\":\"" + body + "\","
//                + "\"product_code\":\"FAST_INSTANT_TRADE_PAY\"}");
//
//        // 请求
//        String result;
//        try {
//            result = alipayClient.pageExecute(alipayRequest).getBody();
//            response.setContentType("text/html;charset=" + AlipayConfig.charset);
//            response.getWriter().write(result);// 直接将完整的表单html输出到页面
//            response.getWriter().flush();
//            response.getWriter().close();
//        } catch (AlipayApiException e) {
//            e.printStackTrace();
//            response.getWriter().write("捕获异常出错");
//            response.getWriter().flush();
//            response.getWriter().close();
//        }
//    }
//}
//
